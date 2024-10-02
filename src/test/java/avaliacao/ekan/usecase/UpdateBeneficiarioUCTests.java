package avaliacao.ekan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import avaliacao.ekan.controller.request.RequestBeneficiarioVO;
import avaliacao.ekan.controller.request.RequestDocumentoVO;
import avaliacao.ekan.controller.response.BeneficiarioVO;
import avaliacao.ekan.domain.beneficiario.Beneficiario;
import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;
import avaliacao.ekan.domain.beneficiario.documento.TipoDocumento;
import avaliacao.ekan.exceptions.BeneficiarioNaoEncontradoExcpetion;

@SpringBootTest
@ActiveProfiles("test")
class UpdateBeneficiarioUCTests {

	@Autowired UpdateBeneficiarioUC uc;
	@MockBean BeneficiarioService beneficiarioService;
	@MockBean DocumentoService documentoService;
	
	@Test void update_beneficiarioNaoExiste() {
		RequestBeneficiarioVO reqst = new RequestBeneficiarioVO(null, null, null, null, null);
		
		when(beneficiarioService.getById(1l))
			.thenReturn(Optional.empty());
		
		assertThrows(BeneficiarioNaoEncontradoExcpetion.class, 
				() -> uc.update(1l, reqst));
	}

	@Test void update() {
		var request = new RequestBeneficiarioVO(
				null, 
				"nome", 
				"telefone", 
				LocalDate.now(), 
				Arrays.asList(
						new RequestDocumentoVO(null, TipoDocumento.CNH, "123456"),
						new RequestDocumentoVO(null, TipoDocumento.PASSAPORTE, "XX12345")));
		
		when(documentoService.saveAll(any()))
		.thenAnswer(new Answer<List<Documento>>() {

			@Override
			public List<Documento> answer(InvocationOnMock invocation) throws Throwable {
				List<Documento> docs = invocation.getArgument(0);
				assertNotNull(docs);
				assertEquals(2, docs.size());
				return docs;
			}
		});
		
		
		when(beneficiarioService.getById(1l))
			.thenReturn(Optional.of(Beneficiario.builder()
					.build()));
		try {
			BeneficiarioVO update = uc.update(1l, request);
			assertNotNull(update);
			assertEquals("nome", update.nome());
			assertNotNull(update.documentos());
			assertEquals(2, update.documentos().size());
		} catch (Exception e) {
			fail(e);
		}
	}
	
}
