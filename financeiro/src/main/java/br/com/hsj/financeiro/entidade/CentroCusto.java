/**
 * @author Hamilton dos Santos Junior
 * @date 20/04/2012
 *
 */
package br.com.hsj.financeiro.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Classe resposnável por armazenar as informações de centro de custo
 * 
 * @author Hamilton dos Santos Junior
 * @date 20/04/2012
 *
 */
@Entity
@Table(name="centroscustos")
@SequenceGenerator(	name="seq_centrocusto", 
					sequenceName="centrocusto_id_seq", 
					initialValue=1)
public class CentroCusto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1532716223330744342L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_centrocusto")
	@Column(name="centrocusto_id")
	private Long id;
	
	@Column(name="descricao", nullable = true, length = 100)
	private String descricao;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_centro_custo", nullable = false)
	private TipoCentroCusto tipoCentroCusto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCentroCusto getTipoCentroCusto() {
		return tipoCentroCusto;
	}

	public void setTipoCentroCusto(TipoCentroCusto tipoCentroCusto) {
		this.tipoCentroCusto = tipoCentroCusto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroCusto other = (CentroCusto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
