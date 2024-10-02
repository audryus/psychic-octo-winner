package avaliacao.ekan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import avaliacao.ekan.controller.request.RequestBeneficiarioVO;
import avaliacao.ekan.controller.request.RequestDocumentoVO;
import avaliacao.ekan.domain.beneficiario.Beneficiario;
import avaliacao.ekan.domain.beneficiario.BeneficiarioRepo;
import avaliacao.ekan.domain.beneficiario.documento.Documento;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoRepo;
import avaliacao.ekan.domain.beneficiario.documento.TipoDocumento;

@SpringBootTest
@ActiveProfiles("test")
class CreateBeneficiarioUCTests {

	@Autowired CreateBeneficiarioUC uc;
	@MockBean BeneficiarioRepo beneficiarioRepo;
	@MockBean DocumentoRepo documentoRepo;
	
	@Test void create() {
		when(beneficiarioRepo.save(any()))
		.thenAnswer(new Answer<Beneficiario>() {

			@Override
			public Beneficiario answer(InvocationOnMock invocation) throws Throwable {
				Beneficiario bene = invocation.getArgument(0);
				assertNotNull(bene);
				assertNotNull(bene.getNome());
				assertNotNull(bene.getTelefone());
				assertNotNull(bene.getDataNascimento());
				bene.setId(1l);
				return bene;
			}
		});
		
		when(documentoRepo.saveAll(any()))
		.thenAnswer(new Answer<List<Documento>>() {

			@Override
			public List<Documento> answer(InvocationOnMock invocation) throws Throwable {
				List<Documento> docs = invocation.getArgument(0);
				assertNotNull(docs);
				assertEquals(2, docs.size());
				
				docs.forEach(doc-> {
					assertNotNull(doc.getBeneficiario());
					assertNotNull(doc.getDescricao());
					assertNotNull(doc.getTipoDocumento());
					doc.setId(1l);
				});
				
				return docs;
			}
		});
		
		
		try {
			var request = new RequestBeneficiarioVO(
					null, 
					"nome", 
					"telefone", 
					LocalDate.now(), 
					Arrays.asList(
							new RequestDocumentoVO(null, TipoDocumento.CNH, "123456"),
							new RequestDocumentoVO(null, TipoDocumento.PASSAPORTE, "XX12345")));
			var response = uc.create(request);
			assertNotNull(response);
			assertNotNull(response.id());
			assertNotNull(response.documentos());
			assertEquals(2, response.documentos().size());
			response.documentos().forEach(d -> {
				assertNotNull(d.id());
			});
			
		} catch (Exception e) {
			fail(e);
		}
		
	}
}
