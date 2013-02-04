package br.com.hsj.financeiro.dao;

import java.util.Date;
import java.util.List;

import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.exception.DAOException;

public interface MovimentacaoDAO extends GenericDAO<Long, Movimentacao> {

	
	/**
	 * Método que busca todas as movimentações até a data passada como parametro
	 * @param _dataFim
	 * @return
	 * @throws DAOException
	 */
	public List<Movimentacao> buscarAteAData(final Date _dataFim) throws DAOException;
	
	/**
	 * Método que recebe uma data de início e uma data de fim e retorna as movimentações do período
	 * Se for passado somente a data de início, irá trazer as movimentações com data maior que a passada por paramento
	 * @param _dataInicio
	 * @param _dataFim
	 * @return
	 * @throws DAOException
	 */
	public List<Movimentacao> buscarPorData(final Date _dataInicio, final Date _dataFim) throws DAOException;
	
}
