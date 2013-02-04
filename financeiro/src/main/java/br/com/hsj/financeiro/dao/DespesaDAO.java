package br.com.hsj.financeiro.dao;

import java.util.List;

import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.exception.DAOException;

/**
 * Interface responsável pela implementação de acesso a banco de dados referentes a {@link Despesa}
 * 
 * @author Hamilton dos Santos Junior
 *
 */
public interface DespesaDAO extends GenericDAO<Long, Despesa> {

	
	public List<Despesa> buscarTodasDespesas() throws DAOException;
}
