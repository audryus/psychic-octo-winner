package avaliacao.ekan.controller.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import avaliacao.ekan.domain.beneficiario.documento.TipoDocumento;

@JsonInclude(value = Include.NON_NULL)
public record DocumentoVO (
		Long id,
		TipoDocumento tipoDocumento,
		String descricao,
		LocalDateTime dataInclusao,
		LocalDateTime dataAtualizacao
		) {
	
	public DocumentoVO(Long id,
			TipoDocumento tipoDocumento,
			String descricao) {
		this(id, tipoDocumento, descricao, null, null);
	}

}
