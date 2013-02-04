/**
 * @author Hamilton dos Santos Junior
 * @date 13/10/2011
 *
 */
package br.com.hsj.financeiro.entidade;

import java.io.Serializable;
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
 * Classe responsável por armazenar as informações de Categorias.
 * 
 * Ex. Categoria: Moradia, Carro

 * @author Hamilton dos Santos Junior
 * @date 13/10/2011
 *
 */
@Entity
@Table(name="categorias")
@SequenceGenerator(	name="seq_categoria", 
					sequenceName="categoria_id_seq", 
					initialValue=1)
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7260635674934768278L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_categoria")
	@Column(name="categoria_id")
	private Long id;

	@Column(name="descricao", nullable = true, length = 100, unique = true)
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_movimentacao", nullable = false)
	private TipoMovimentacao tipoMovimentacao;
	
	/**
	 * Atributo utilizado para armazenar a informação de qual é o pai da categoria
	 * 
	 */
	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="categoria_pai_id")
	private Categoria categoriaPai;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "categoriaPai", cascade = CascadeType.ALL)
	private List<Categoria> subCategorias = null;

	
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
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<Categoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		Categoria other = (Categoria) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	
}
