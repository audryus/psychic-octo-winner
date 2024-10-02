package avaliacao.ekan.domain.beneficiario.documento;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentoService {
	
	private final DocumentoRepo repo;

	@Cacheable(value = "documentos_beneficiario")
	public List<Documento> getAllByBeneficiario(
			Long beneficiario) {
		return repo.findAllByBeneficiario(beneficiario);
	}

}
