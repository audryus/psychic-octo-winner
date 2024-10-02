package avaliacao.ekan.domain.beneficiario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepo 
	extends JpaRepository<Beneficiario, Long> {
}
