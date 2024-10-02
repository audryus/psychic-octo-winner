package avaliacao.ekan.usecase;

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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBeneficiarioUC {
	
	private final BeneficiarioService beneficiarioService;
	private final DocumentoService documentoService;
	
	@Transactional
	public BeneficiarioVO create(RequestBeneficiarioVO req) {
		var beneficiario = beneficiarioService.create(
				getBeneficiario(req));
		
		var documentos = getDocumentos(beneficiario, req);
		documentoService.create(documentos);
		
		return generateResponse(beneficiario, documentos);
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

	private List<Documento> getDocumentos(Beneficiario bene, RequestBeneficiarioVO req) {
		return req.documentos()
		.stream()
		.map(d -> getDocumento(d, bene))
		.toList();
	}

	private Documento getDocumento(
			RequestDocumentoVO d, 
			Beneficiario bene) {
		return Documento.builder()
				.beneficiario(bene.getId())
				.descricao(d.descricao())
				.tipoDocumento(d.tipoDocumento())
				.build();
	}

	private Beneficiario getBeneficiario(RequestBeneficiarioVO req) {
		return Beneficiario.builder()
				.nome(req.nome())
				.telefone(req.telefone())
				.dataNascimento(req.dataNascimento())
				.build();
	}

	private DocumentoVO toDocumentoVO(Documento doc) {
		return new DocumentoVO(
				doc.getId(), 
				doc.getTipoDocumento(), 
				doc.getDescricao());
	}

	
	
}
