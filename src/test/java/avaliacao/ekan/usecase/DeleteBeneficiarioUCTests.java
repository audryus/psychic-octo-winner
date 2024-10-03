package avaliacao.ekan.usecase;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;

@SpringBootTest
@ActiveProfiles("test")
class DeleteBeneficiarioUCTests {
	
	@Autowired DeleteBeneficiarioUC uc;
	
	@MockBean DocumentoService documentoService;
	@MockBean BeneficiarioService beneficiarioService;
	
	@Test void remove() {
		try {
			uc.remove(1l);
		} catch (Exception e) {
			fail(e);
		}
	}
	
}
