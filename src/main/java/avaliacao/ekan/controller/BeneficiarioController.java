package avaliacao.ekan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.usecase.GetBeneficiarioUC;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("beneficiarios")
@RequiredArgsConstructor
public class BeneficiarioController {

	private final GetBeneficiarioUC getter;
	
	@GetMapping
	public List<BeneficiarioVO> getAll() {
		return getter.getAll();
	}
	
}
