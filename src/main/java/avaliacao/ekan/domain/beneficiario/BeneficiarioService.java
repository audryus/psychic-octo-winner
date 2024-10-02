package avaliacao.ekan.domain.beneficiario;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeneficiarioService {
	
	private final BeneficiarioRepo repo;
	
	@Cacheable(value = "beneficiarios")
	public List<Beneficiario> getAll() {
		return repo.findAll();
	}
	
	
}
