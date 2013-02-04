/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hsj.financeiro.dao.UsuarioDAO;
import br.com.hsj.financeiro.entidade.Usuario;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.exception.DAOException;
import br.com.hsj.financeiro.servico.UsuarioServico;

/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
@Service(value="usuarioServico")
public class UsuarioServicoImpl extends BaseServicoImpl<Usuario> implements UsuarioServico, UserDetailsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210511400421304876L;

	@Autowired
	private UsuarioDAO usuarioDAO;

	
	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#salvar(java.lang.Object)
	 */
	@Override
	public void salvar(Usuario _entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#buscar()
	 */
	@Override
	public List<Usuario> buscar() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String _username)
			throws UsernameNotFoundException {
		try {
			return usuarioDAO.buscarPorLogin(_username);
		} catch (DAOException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
