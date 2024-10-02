package avaliacao.ekan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.usecase.GetDocumentoUC;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DocumentoController {

	private final GetDocumentoUC getter;
	
	@GetMapping("beneficiarios/{beneficiario}/documentos")
	public List<DocumentoVO> listAllBeneficiario(
			@PathVariable("beneficiario") Long beneficiario) {
		return getter.getAllByBeneficiario(beneficiario);
	}
	
}
