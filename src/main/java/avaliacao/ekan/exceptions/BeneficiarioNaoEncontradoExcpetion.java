package avaliacao.ekan.exceptions;

import org.springframework.http.HttpStatus;

public class BeneficiarioNaoEncontradoExcpetion extends ApiException {

	private static final long serialVersionUID = 1L;

	public BeneficiarioNaoEncontradoExcpetion() {
		super("ERR_BENEFICIARIO_NAO_EXISTE", 
				HttpStatus.NOT_FOUND);
	}

}
