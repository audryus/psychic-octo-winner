package avaliacao.ekan.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import avaliacao.ekan.domain.beneficiario.documento.TipoDocumento;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestDocumentoVO (
		Long id,
		TipoDocumento tipoDocumento,
		String descricao
		) {

}
