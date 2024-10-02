package avaliacao.ekan.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import avaliacao.ekan.controller.request.RequestBeneficiarioVO;
import avaliacao.ekan.controller.request.RequestDocumentoVO;
import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.domain.beneficiario.Beneficiario;
import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;
import avaliacao.ekan.exceptions.BeneficiarioNaoEncontradoExcpetion;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBeneficiarioUC {
	
	private final BeneficiarioService beneficiarioService;
	private final DocumentoService documentoService;
	
	@Transactional
	public BeneficiarioVO update(
			Long beneficiario, 
			RequestBeneficiarioVO req) {
		var benefi = beneficiarioService.getById(beneficiario)
				.orElseThrow(BeneficiarioNaoEncontradoExcpetion::new);
		
		atualizaBeneficiario(benefi, req);
		
		var docs = atualizaDocumentos(
				beneficiario, 
				req.documentos());
		
		beneficiarioService.save(benefi);
		
		return generateResponse(benefi, docs);
	}

	private void atualizaBeneficiario(
			Beneficiario benefi, 
			RequestBeneficiarioVO req) {
		benefi.setNome(req.nome());
		benefi.setDataAtualizacao(LocalDateTime.now());
		benefi.setDataNascimento(req.dataNascimento());
		benefi.setTelefone(req.telefone());
	}

	private List<Documento> atualizaDocumentos(
			Long beneficiario,
			List<RequestDocumentoVO> documentos) {
		return documentoService.saveAll(
			documentos.stream()
			.map(d -> Documento.builder()
					.id(d.id())
					.beneficiario(beneficiario)
					.dataAtualizacao(LocalDateTime.now())
					.dataInclusao(LocalDateTime.now())
					.descricao(d.descricao())
					.tipoDocumento(d.tipoDocumento())
					.build())
			.toList());
	}

	private BeneficiarioVO generateResponse(
			Beneficiario bene, 
			List<Documento> docs) {
		
		return new BeneficiarioVO(
				bene.getId(), 
				bene.getNome(), 
				bene.getDataNascimento(), 
				docs.stream()
				.map(this::toDocumentoVO)
				.toList());
	}

	private DocumentoVO toDocumentoVO(Documento doc) {
		return new DocumentoVO(
				doc.getId(), 
				doc.getTipoDocumento(), 
				doc.getDescricao());
	}


}
