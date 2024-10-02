package avaliacao.ekan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import avaliacao.ekan.controller.response.DocumentoVO;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;

@SpringBootTest
@ActiveProfiles("test")
class GetDocumentoUCTests {
	
	@Autowired GetDocumentoUC uc;
	@MockBean DocumentoService documentoService;
	
	@Test void getAllByBeneficiario_semRegistro() {
		when(documentoService.getAllByBeneficiario(1l))
		.thenReturn(new ArrayList<Documento>());
		List<DocumentoVO> all = uc.getAllByBeneficiario(1L);
		assertNotNull(all);
	}

	@Test void getAllByBeneficiario() {
		when(documentoService.getAllByBeneficiario(1l))
		.thenReturn(Arrays.asList(
				Documento.builder().build(), 
				Documento.builder().build(), 
				Documento.builder().build()));
		
		List<DocumentoVO> all = uc.getAllByBeneficiario(1L);
		assertNotNull(all);
		assertEquals(3, all.size());
	}

}
