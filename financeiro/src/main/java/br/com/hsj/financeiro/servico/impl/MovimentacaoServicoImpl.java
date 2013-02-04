package br.com.hsj.financeiro.servico.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hsj.financeiro.dao.MovimentacaoDAO;
import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.exception.DAOException;
import br.com.hsj.financeiro.servico.MovimentacaoServico;
import br.com.hsj.financeiro.util.FacesUtils;
import br.com.hsj.financeiro.util.MensagensUtils;

@Service(value="movimentacaoServico")
public class MovimentacaoServicoImpl extends BaseServicoImpl<Movimentacao> implements MovimentacaoServico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2780540680743294638L;

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	/**
	 * Método utilizado para salvar uma movimentacao
	 */
	@Transactional(rollbackFor = DAOException.class)
	@Override
	public void salvar(Movimentacao _entity) throws BusinessException {
		_entity.setUsuario(FacesUtils.recuperarUsuarioLogado());
		
		if (TipoMovimentacao.DEBITO.equals(_entity.getTipoMovimentacao())) {
			_entity.setValorPagamento(_entity.getValorPagamento().negate());
		}
		
		movimentacaoDAO.merge(_entity);
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.BaseServico#buscar()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Movimentacao> buscar() throws BusinessException {
		return movimentacaoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movimentacao> buscarMovimentacoesUltimos30Dias() throws BusinessException {

		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		cal.add(Calendar.DATE, -30);
		
		try {
			List<Movimentacao> movimentacoes = movimentacaoDAO.buscarPorData(cal.getTime(), null);
			
			List<Movimentacao> movAnteriores = movimentacaoDAO.buscarAteAData(cal.getTime());
			
			Movimentacao saldoAnterior = calcularSaldoAnterior(movAnteriores, cal.getTime());
			
			movimentacoes.add(0, saldoAnterior);
			
			return movimentacoes;
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Método que recebe uma lista de movimentações 
	 * @param _movAnteriores
	 * @param _data
	 * @return
	 */
	private Movimentacao calcularSaldoAnterior(final List<Movimentacao> _movAnteriores, final Date _data) {
		Movimentacao movimentacao = new Movimentacao();
		BigDecimal valor = calculaMovimentacoes(_movAnteriores);
		
		movimentacao.setValorPagamento(valor);
		movimentacao.setDescricao(MensagensUtils.getInstance().getMensagem("mensagem.saldo.anterior", null));
		movimentacao.setDataPagamento(_data);
		movimentacao.setTipoMovimentacao(TipoMovimentacao.CREDITO);

		if (valor.intValue() < 0) {
			movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		}
		
		return movimentacao;
	}

	/**
	 * Método que percorre a lista de movimentações fazendo a soma
	 * @param _movAnteriores
	 * @return
	 */
	private BigDecimal calculaMovimentacoes(final List<Movimentacao> _movAnteriores) {
		BigDecimal valor = new BigDecimal(0);
		
		for (Movimentacao mov : _movAnteriores) {
			// soma
			valor = valor.add(mov.getValorPagamento());
		}
		return valor;
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.servico.MovimentacaoServico#calcularSaldoDisponivel(java.util.List)
	 */
	@Override
	@Transactional(readOnly = true)
	public Movimentacao calcularSaldoDisponivel(List<Movimentacao> _listaMovimentacoes) throws BusinessException {
		Movimentacao movimentacao = new Movimentacao();
		BigDecimal valor = calculaMovimentacoes(_listaMovimentacoes);
		
		movimentacao.setValorPagamento(valor);
		movimentacao.setTipoMovimentacao(TipoMovimentacao.CREDITO);

		if (valor.intValue() < 0) {
			movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		}
		
		return movimentacao;
	}

	/**
	 * @see MovimentacaoServico#buscarMovimentacoesPorData(Date, Date)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Movimentacao> buscarMovimentacoesPorData(Date _inicio, Date _fim) throws BusinessException {
		try {
			return movimentacaoDAO.buscarPorData(_inicio, _fim);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

}
