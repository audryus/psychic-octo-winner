package avaliacao.ekan.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.domain.beneficiario.Beneficiario;
import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBeneficiarioUC {
	private final BeneficiarioService beneficiarioService;
	private final DocumentoService documentoService;
	
	public List<BeneficiarioVO> getAll() {
		return beneficiarioService.getAll()
		.stream()
		.map(this::toVO)
		.toList();
	}

	private BeneficiarioVO toVO(Beneficiario bene) {
		var documentos = documentoService.getAllByBeneficiario(bene.getId())
				.stream()
				.map(this::toDocumentoVO)
				.toList();
		
		
		return new BeneficiarioVO(
				bene.getId(), 
				bene.getNome(), 
				bene.getDataNascimento(),
				documentos);
	}
	
	private DocumentoVO toDocumentoVO(Documento doc) {
		return new DocumentoVO(doc.getId(), 
				doc.getTipoDocumento(), 
				doc.getDescricao());
	}

}
