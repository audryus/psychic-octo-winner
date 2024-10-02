package avaliacao.ekan.domain.beneficiario.documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepo 
extends JpaRepository<Documento, Long>{

}
