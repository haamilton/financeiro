/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.hsj.financeiro.dao.CentroCustoDAO;
import br.com.hsj.financeiro.entidade.CentroCusto;

/**
 * 
 * @author Hamilton dos Santos Junior
 * @date 11/05/2012
 *
 */
@Repository("centroCustoDAO")
public class CentroCustoDAOImpl extends GenericDAOImpl<Long, CentroCusto> implements CentroCustoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068769564866798297L;

}
