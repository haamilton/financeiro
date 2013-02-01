/**
 * @author Hamilton dos Santos Junior
 * @date 24/02/2012
 *
 */
package br.com.hsj.financeiro.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

/**
 * @author Hamilton dos Santos Junior
 * @date 08/10/2012
 *
 */
@SuppressWarnings("serial")
public abstract class Base implements Serializable {

	@Column(name = "DATA_CRIACAO", nullable=false)
	protected Date dataCriacao;

	@Column(name = "USUARIO_CRIACAO", nullable=false)
	protected String usuarioCriacao;

	@Column(name = "DATA_ATUALIZACAO", nullable=true)
	protected Date dataAtualizacao;
	
	@Column(name = "USUARIO_ATUALIZACAO", nullable=false)
	protected String usuarioAtualizacao;
	
	public abstract void setId(Long _id);
	
	public abstract Long getId();
	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
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
		Base other = (Base) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
