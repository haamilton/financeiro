package br.com.hsj.financeiro.servico.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hsj.financeiro.dao.CategoriaDAO;
import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.exception.DAOException;
import br.com.hsj.financeiro.servico.CategoriaServico;


@Service(value="categoriaServico")
public class CategoriaServicoImpl extends BaseServicoImpl<Categoria> implements CategoriaServico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2780540680743294638L;

	private static Logger logger = Logger.getLogger(CategoriaServicoImpl.class);
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	/**
	 * Método utilizado para salvar uma movimentacao
	 */
	@Transactional(rollbackFor = DAOException.class)
	@Override
	public void salvar(Categoria _entity) throws BusinessException {
		categoriaDAO.merge(_entity);
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#buscar()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> buscar() throws BusinessException {
		return categoriaDAO.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.CategoriaServico#buscarTodasCategoriasComSubCategorias()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> buscarTodasCategoriasComSubCategorias()
			throws BusinessException {
		try {
			return categoriaDAO.buscarTodasCategoriasComSubCategorias();
		} catch (DAOException e) {
			logger.error("Ocorreu um erro ao buscar todas as categorias", e);
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.CategoriaServico#buscarSubCategoriasPorTipoMovimentacao(br.com.hsj.financeiro.entidade.TipoMovimentacao)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> buscarCategoriasPorTipoMovimentacao(TipoMovimentacao _tipoMovimentacao) throws BusinessException {
		if (_tipoMovimentacao == null) { throw new NullPointerException("_tipoMovimentacao null"); }
		
		try {
			return categoriaDAO.buscarCategoriasPorTipoMovimentacao(_tipoMovimentacao);
		} catch (DAOException e) {
			logger.error("Ocorreu um erro ao recuperar as subcategorias", e);
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
