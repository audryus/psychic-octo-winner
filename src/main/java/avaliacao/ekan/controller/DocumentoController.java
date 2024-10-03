package avaliacao.ekan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.usecase.GetDocumentoUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Documento", description = "APIs de Gerenciamento de Documentos")
@RestController
@RequiredArgsConstructor
public class DocumentoController {

	private final GetDocumentoUC getter;
	
	@Operation(
		      summary = "Lista os Documentos do Beneficiario",
		      description = "Lista de Documentos de Beneficiario informado.")
	@ApiResponse(responseCode = "200")
	@GetMapping("beneficiarios/{beneficiario}/documentos")
	public List<DocumentoVO> listAllBeneficiario(
			@Parameter(description = "ID do Beneficiario") @PathVariable("beneficiario") Long beneficiario) {
		return getter.getAllByBeneficiario(beneficiario);
	}
	
}
