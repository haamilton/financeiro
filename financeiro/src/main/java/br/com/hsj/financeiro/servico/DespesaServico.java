package br.com.hsj.financeiro.servico;

import java.util.List;

import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.exception.BusinessException;

/**
 * 
 * @author Hamilton dos Santos Junior
 *
 */
public interface DespesaServico extends BaseServico<Despesa> {

	/**
	 * Método utilizado para buscar todas as despesas cadastradas
	 * @return Lista de despesas
	 * @throws BusinessException
	 */
	public List<Despesa> buscarTodasDespesas() throws BusinessException;

	
}
