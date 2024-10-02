package avaliacao.ekan.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetDocumentoUC {
	
	private final DocumentoService documentoService;
	
	public List<DocumentoVO> getAllByBeneficiario(Long beneficiario) {
		return documentoService.getAllByBeneficiario(beneficiario)
		.stream()
		.map(this::toDocumentoVO)
		.toList();
	}
	
	private DocumentoVO toDocumentoVO(Documento doc) {
		return new DocumentoVO(doc.getId(), 
				doc.getTipoDocumento(), 
				doc.getDescricao(),
				doc.getDataInclusao(),
				doc.getDataAtualizacao());
	}
}
