package avaliacao.ekan.controller.request;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestBeneficiarioVO(
		Long id,
		String nome,
		String telefone,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate dataNascimento,
		List<RequestDocumentoVO> documentos) {

}
