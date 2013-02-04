package br.com.hsj.financeiro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.hsj.financeiro.dao.CategoriaDAO;
import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.DAOException;

@Repository("categoriaDAO")
public class CategoriaDAOImpl extends GenericDAOImpl<Long, Categoria> implements CategoriaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4588218203550651241L;

	@SuppressWarnings("unchecked")
	public List<Categoria> buscarTodasCategoriasComSubCategorias() throws DAOException {
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Categoria.class);
		
		criteria.add(Restrictions.isNull("categoriaPai"));
		
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.dao.CategoriaDAO#buscarSubCategoriasPorTipoMovimentacao(br.com.hsj.financeiro.entidade.TipoMovimentacao)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> buscarCategoriasPorTipoMovimentacao(TipoMovimentacao _tipoMovimentacao) throws DAOException {
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Categoria.class);
		
		criteria.add(Restrictions.eq("tipoMovimentacao", _tipoMovimentacao));
		
		return criteria.list();
	}
	
	
}
