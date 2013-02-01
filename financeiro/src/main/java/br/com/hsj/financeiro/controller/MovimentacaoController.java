package br.com.hsj.financeiro.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.MovimentacaoServico;

/**
 * Managed bean que manipula as informações das movimentações do mês atual
 * 
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
@ManagedBean(name="movimentacaoController")
@ViewScoped
public class MovimentacaoController extends BaseMovimentacaoController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127463673218687499L;
	
	private static Logger logger = Logger.getLogger(MovimentacaoController.class);
	
	private Movimentacao saldoDisponivel;
	
	private List<Movimentacao> listaMovimentacoes;
	
	@ManagedProperty(value="#{movimentacaoServico}")
	private MovimentacaoServico movimentacaoServico;

	/**
	 * Método utilziado para buscar as movimentacoes
	 */
	public void buscar() {
		logger.info("Iniciando o método buscar");
		
		FacesMessage msg = null;
		
		try {
			listaMovimentacoes = movimentacaoServico.buscarMovimentacoesUltimos30Dias();
			
			saldoDisponivel = movimentacaoServico.calcularSaldoDisponivel(listaMovimentacoes);
		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		logger.info("Finalizando o método buscar");
	}
	
	@Override
	public void inicializacao() {
		buscar();
	}


	/************************************************
	 * GETTERS AND SETTERS
	 ************************************************/
	
	public void setMovimentacaoServico(MovimentacaoServico movimentacaoServico) {
		this.movimentacaoServico = movimentacaoServico;
	}

	public List<Movimentacao> getListaMovimentacoes() {
		return listaMovimentacoes;
	}

	public void setListaMovimentacoes(List<Movimentacao> listaMovimentacoes) {
		this.listaMovimentacoes = listaMovimentacoes;
	}

	public Movimentacao getSaldoDisponivel() {
		return saldoDisponivel;
	}

}
