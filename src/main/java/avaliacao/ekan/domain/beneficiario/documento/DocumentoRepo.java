package avaliacao.ekan.domain.beneficiario.documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepo 
extends JpaRepository<Documento, Long>{
	
	List<Documento> findAllByBeneficiario(Long beneficiario);

	void deleteAllByBeneficiario(Long beneficiario);

}
