/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hsj.financeiro.dao.UsuarioDAO;
import br.com.hsj.financeiro.entidade.Usuario;
import br.com.hsj.financeiro.exception.DAOException;

/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
@Repository("usuarioDAO")
public class UsuarioDAOImpl extends GenericDAOImpl<Long, Usuario> implements UsuarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068769564866798297L;

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.dao.UsuarioDAO#buscarPorLogin(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public Usuario buscarPorLogin(String _login) throws DAOException {
		
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", _login));
		criteria.setFetchMode("perfis", FetchMode.JOIN);
		
		return (Usuario) criteria.uniqueResult();
	}

}
