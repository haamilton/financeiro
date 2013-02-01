/**
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
package br.com.hsj.financeiro.servico;

import java.util.List;

import br.com.hsj.financeiro.exception.BusinessException;

/**
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
public interface BaseServico<E> {

	public void salvar(E _entity) throws BusinessException;
	
	public List<E> buscar() throws BusinessException;
	
}
