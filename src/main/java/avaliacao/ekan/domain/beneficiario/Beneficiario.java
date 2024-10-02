package avaliacao.ekan.domain.beneficiario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Beneficiario")
public class Beneficiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "DT_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "TS_INCLUSAO", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

	@Column(name = "TS_ATUALIZACAO", insertable = false, updatable = true)
	private LocalDateTime dataAtualizacao;
	
}
