package avaliacao.ekan.domain.beneficiario;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
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

	@Cacheable(value = "beneficiario")
	public Optional<Beneficiario> getByID(Long beneficiario) {
		return repo.findById(beneficiario);
	}

	@CacheEvict(allEntries = true, 
			value = {"beneficiarios"})
	public void deleteByID(Long beneficiario) {
		repo.deleteById(beneficiario);
	}
	
}
