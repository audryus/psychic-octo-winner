package avaliacao.ekan.controller.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record BeneficiarioVO (
		Long id,
		String nome,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate dataNascimento,
		List<DocumentoVO> documentos
		){

}
