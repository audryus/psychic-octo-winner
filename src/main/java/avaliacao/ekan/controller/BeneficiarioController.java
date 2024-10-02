package avaliacao.ekan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("beneficiarios")
@RequiredArgsConstructor
public class BeneficiarioController {

	private final GetBeneficiarioUC getter;
	private final DeleteBeneficiarioUC delete;
	private final CreateBeneficiarioUC creator;
	private final UpdateBeneficiarioUC updator;
	
	@GetMapping
	public List<BeneficiarioVO> getAll() {
		return getter.getAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BeneficiarioVO create(
			@RequestBody RequestBeneficiarioVO request) {
		return creator.create(request);
	}

	@PutMapping("{beneficiario}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public BeneficiarioVO update(
			@PathVariable("beneficiario") Long beneficiario,
			@RequestBody RequestBeneficiarioVO request) {
		return updator.update(beneficiario, request);
	}
	
	@DeleteMapping("{beneficiario}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(
			@PathVariable("beneficiario") Long beneficiario) {
		delete.remove(beneficiario);
	}
}
