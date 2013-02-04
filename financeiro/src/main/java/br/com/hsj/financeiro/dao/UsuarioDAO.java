/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.dao;

import br.com.hsj.financeiro.entidade.Usuario;
import br.com.hsj.financeiro.exception.DAOException;


/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
public interface UsuarioDAO extends GenericDAO<Long, Usuario> {

	/**
	 * Método que retorna o Usuário buscando por login
	 * @param _login
	 * @return
	 * @throws DAOException
	 */
	public Usuario buscarPorLogin(String _login) throws DAOException;

}
