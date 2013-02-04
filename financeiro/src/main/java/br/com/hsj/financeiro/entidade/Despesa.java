package br.com.hsj.financeiro.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Classe resposnável por armazenar as informações de despesas
 * @author Hamilton dos Santos Junior
 *
 */
@Entity
@Table(name="despesas")
@SequenceGenerator(	name="seq_despesa", 
					sequenceName="despesa_id_seq", 
					initialValue=1)
public class Despesa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7255207149976920277L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_despesa")
	@Column(name="despesa_id")
	private Long id;
	
	/**
	 * Data de vencimento
	 */
	@Column(name="descricao", nullable = true)
	private String descricao;
	
	/**
	 * Data de vencimento
	 */
	@Column(name="data_vencimento", nullable = true)
	private Date dataVencimento;
	
	/**
	 * Valor da despesa
	 */
	@Column(name="valor", nullable = true)
	private BigDecimal valor;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_despesa", nullable = false)
	private TipoDespesa tipoDespesa;
	
	/**
	 * Atributo que armazena qual é o número da parcela
	 * É usado somente para despesas parceladas
	 */
	@Column(name="numero_parcela", nullable = true)
	private Integer numeroParcela;

	/**
	 * Atributo que armazena qual é o número da parcela
	 * É usado somente para despesas parceladas
	 */
	@Column(name="qtde_parcelas")
	private Integer qtdeParcelas;
	
	/**
	 * Atributo utilizado para armazenar a informação de qual é o pai da despesa
	 * utilizado para despesas parceladas
	 */
	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="despesa_pai_id")
	private Despesa despesaPai;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,  mappedBy = "despesaPai", cascade = CascadeType.ALL)
	private List<Despesa> parcelas = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Despesa getDespesaPai() {
		return despesaPai;
	}

	public void setDespesaPai(Despesa despesaPai) {
		this.despesaPai = despesaPai;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	
	public List<Despesa> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Despesa> parcelas) {
		this.parcelas = parcelas;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
