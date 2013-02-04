package br.com.hsj.financeiro.servico;

import java.util.Date;
import java.util.List;

import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.exception.BusinessException;

public interface MovimentacaoServico extends BaseServico<Movimentacao> {

	/**
	 * Método que busca as últimas 30 movimentações cadastradas
	 * @return
	 * @throws BusinessException 
	 */
	public List<Movimentacao> buscarMovimentacoesUltimos30Dias() throws BusinessException;
	
	/**
	 * Método que recebe uma lista de movimentacoes e faz o cálculo do saldo disponível
	 * @param _listaMovimentacoes
	 * @return
	 * @throws BusinessException
	 */
	public Movimentacao calcularSaldoDisponivel(List<Movimentacao> _listaMovimentacoes) throws BusinessException;
	
	/**
	 * Método que recebe uma data inicial e final e busca todas as movimentações por data
	 * @param _inicio
	 * @param _fim
	 * @return
	 * @throws BusinessException
	 */
	public List<Movimentacao> buscarMovimentacoesPorData(Date _inicio, Date _fim) throws BusinessException;
	
}