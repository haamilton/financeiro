package br.com.hsj.financeiro.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hsj.financeiro.dao.CentroCustoDAO;
import br.com.hsj.financeiro.entidade.CentroCusto;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.exception.DAOException;
import br.com.hsj.financeiro.servico.CentroCustoServico;

/**
 * 
 * @author Hamilton dos Santos Junior
 * @date 11/05/2012
 *
 */
@Service(value="centroCustoServico")
public class CentroCustoServicoImpl extends BaseServicoImpl<CentroCusto> implements CentroCustoServico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210511400421304876L;

	@Autowired
	private CentroCustoDAO centroCustoDAO;

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#salvar(java.lang.Object)
	 */
	@Transactional(rollbackFor = DAOException.class)
	@Override
	public void salvar(CentroCusto _entity) throws BusinessException {
		centroCustoDAO.merge(_entity);
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#buscar()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CentroCusto> buscar() throws BusinessException {
		return centroCustoDAO.findAll();
	}

	public void setCentroCustoDAO(CentroCustoDAO centroCustoDAO) {
		this.centroCustoDAO = centroCustoDAO;
	}

}
