package avaliacao.ekan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import avaliacao.ekan.controller.request.RequestBeneficiarioVO;
import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.usecase.CreateBeneficiarioUC;
import avaliacao.ekan.usecase.DeleteBeneficiarioUC;
import avaliacao.ekan.usecase.GetBeneficiarioUC;
import avaliacao.ekan.usecase.UpdateBeneficiarioUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Beneficiario", description = "APIs de Gerenciamento de Beneficiarios")
@RestController
@RequestMapping("beneficiarios")
@RequiredArgsConstructor
public class BeneficiarioController {

	private final GetBeneficiarioUC getter;
	private final DeleteBeneficiarioUC delete;
	private final CreateBeneficiarioUC creator;
	private final UpdateBeneficiarioUC updator;
	
	@Operation(
		      summary = "Lista de Beneficiarios",
		      description = "Lista todos os beneficiarios e seus documentos."
		      )
	@ApiResponse(responseCode = "200")
	@GetMapping
	@PreAuthorize("hasRole('READ')")
	public List<BeneficiarioVO> list() {
		return getter.getAll();
	}
	
	@Operation(
		      summary = "Cria o Beneficiario",
		      description = "Cria um novo beneficiairio com os documentos informados no payload."
		      )
	@ApiResponse(responseCode = "201")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasRole('WRITE')")
	public BeneficiarioVO create(
			@RequestBody RequestBeneficiarioVO request) {
		return creator.create(request);
	}
	
	@Operation(
		      summary = "Atualiza o Beneficiario pelo Id",
		      description = "Atualiza os dados e os documentos do Beneficiario. Caso um documento novo (sem ID) seja enviado, este será incluido."
			)
    @ApiResponse(responseCode = "202", content = { @Content(schema = @Schema(implementation = BeneficiarioVO.class), mediaType = "application/json") })
    @ApiResponse(responseCode = "404", description = "ID informado não existe.", content = { @Content( schema = @Schema(type = "string", defaultValue = "ERR_BENEFICIARIO_NAO_EXISTE") ) })
	@PutMapping("{beneficiario}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('WRITE')")
	public BeneficiarioVO update(
			@Parameter(description = "ID do Beneficiario") @PathVariable("beneficiario") Long beneficiario,
			@RequestBody RequestBeneficiarioVO request) {
		return updator.update(beneficiario, request);
	}
	
	@Operation(
		      summary = "Deleta o Beneficiario pelo Id",
		      description = "Deleta (hard) os documentos e o beneficiario."
			)
	@ApiResponse(responseCode = "200")
	@DeleteMapping("{beneficiario}")
	@ResponseStatus(code = HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(
			@Parameter(description = "ID do Beneficiario") @PathVariable("beneficiario") Long beneficiario) {
		delete.remove(beneficiario);
	}
}
