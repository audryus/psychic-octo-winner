package avaliacao.ekan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.domain.beneficiario.Beneficiario;
import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;

@SpringBootTest
@ActiveProfiles("test")
class GetBeneficiarioUCTests {

	@Autowired GetBeneficiarioUC uc;
	@MockBean BeneficiarioService beneficiarioService;
	@MockBean DocumentoService documentoService;
	
	@Test void getAll_semBeneficiarios() {
		when(beneficiarioService.getAll())
			.thenReturn(new ArrayList<Beneficiario>());
		List<BeneficiarioVO> all = uc.getAll();
		assertNotNull(all);
	}

	@Test void getAll_semDocumentos() {
		when(beneficiarioService.getAll())
		.thenReturn(Arrays.asList(
				Beneficiario.builder()
				.id(1l)
				.build(),
				Beneficiario.builder()
				.id(2l)
				.build()));
		
		when(documentoService.getAllByBeneficiario(any()))
		.thenReturn(new ArrayList<Documento>());
		
		List<BeneficiarioVO> all = uc.getAll();
		assertNotNull(all);
	}

	@Test void getAll() {
		when(beneficiarioService.getAll())
		.thenReturn(Arrays.asList(
				Beneficiario.builder()
				.id(1l)
				.build(),
				Beneficiario.builder()
				.id(2l)
				.build()));
		
		when(documentoService.getAllByBeneficiario(1l))
		.thenReturn(Arrays.asList(
				Documento.builder().build(),
				Documento.builder().build()));
		
		List<BeneficiarioVO> all = uc.getAll();
		assertNotNull(all);
		assertEquals(2, all.size());
		BeneficiarioVO beneficiarioVO = all.get(0);
		assertNotNull(beneficiarioVO);
		assertNotNull(beneficiarioVO.documentos());
		assertEquals(2, beneficiarioVO.documentos().size());
	}
	
}
