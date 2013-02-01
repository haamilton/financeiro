/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
@Entity
@Table(name="PERFIS")
@SequenceGenerator(	name="SEQ_PERFIL", 
					sequenceName="PERFIL_ID_SEQ", 
					initialValue=1)
public class Perfil extends Base implements Serializable, GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326325419294951250L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PERFIL")
	@Column(name="PERFIL_ID")
	private Long id;
	
	@Column(name="DESCRICAO", nullable=false, length=50)
	private String descricao;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = @JoinColumn(name = "PERFIL_ID"), inverseJoinColumns = @JoinColumn(name = "USUARIO_ID"))
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Transient
	@Override
	public String getAuthority() {
		return descricao;
	}

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
