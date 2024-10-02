package avaliacao.ekan.domain.beneficiario.documento;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Documento")
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "BENEFICIARIO")
	private Long beneficiario;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumento;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "TS_INCLUSAO", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

	@Column(name = "TS_ATUALIZACAO", insertable = false, updatable = true)
	private LocalDateTime dataAtualizacao;
	

}
