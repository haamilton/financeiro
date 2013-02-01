package br.com.hsj.financeiro.servico.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hsj.financeiro.dao.DespesaDAO;
import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.exception.DAOException;
import br.com.hsj.financeiro.servico.DespesaServico;

/**
 * 
 * @author Hamilton dos Santos Junior
 *
 */
/**
 * @author Hamilton dos Santos Junior
 *
 */
@Service(value="despesaServico")
public class DespesaServicoImpl extends BaseServicoImpl<Despesa> implements DespesaServico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210511400421304876L;

	private static Logger logger = Logger.getLogger(DespesaServicoImpl.class);
	
	@Autowired
	private DespesaDAO despesaDAO;

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#salvar(java.lang.Object)
	 */
	@Transactional(rollbackFor = DAOException.class)
	@Override
	public void salvar(Despesa _entity) throws BusinessException {
		despesaDAO.merge(_entity);
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#buscar()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Despesa> buscar() throws BusinessException {
		return despesaDAO.findAll();
	}

	public void setDespesaDAO(DespesaDAO despesaDAO) {
		this.despesaDAO = despesaDAO;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Despesa> buscarTodasDespesas() throws BusinessException {
		try {
			return despesaDAO.buscarTodasDespesas();
		} catch (DAOException e) {
			logger.error("Ocorreu um erro ao buscar todas as despesas", e);
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	

}
