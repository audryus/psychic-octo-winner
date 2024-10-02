package avaliacao.ekan.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import avaliacao.ekan.domain.beneficiario.BeneficiarioService;
import avaliacao.ekan.domain.beneficiario.documento.DocumentoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteBeneficiarioUC {
	private final DocumentoService documentoService;
	private final BeneficiarioService beneficiarioService;
	
	@Transactional
	public void remove(Long beneficiario) {
		documentoService.deleteAllByBeneficiario(beneficiario);
		beneficiarioService.deleteById(beneficiario);
	}

}
