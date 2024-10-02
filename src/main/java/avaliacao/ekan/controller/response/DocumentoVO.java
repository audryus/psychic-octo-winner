package avaliacao.ekan.controller.response;

import avaliacao.ekan.domain.beneficiario.documento.TipoDocumento;

public record DocumentoVO (
		Long id,
		TipoDocumento tipoDocumento,
		String descricao
		) {

}
