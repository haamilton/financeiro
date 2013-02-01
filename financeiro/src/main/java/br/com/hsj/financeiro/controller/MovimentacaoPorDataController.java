package br.com.hsj.financeiro.controller;

import java.util.Date;
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
 * Managed Bean que manipula a tela de movimentações com consulta por data
 * @author Hamilton dos Santos Junior 
 *
 */

@ManagedBean(name="movimentacaoPorDataController")
@ViewScoped
public class MovimentacaoPorDataController extends BaseMovimentacaoController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6250534275425163358L;

	private static Logger logger = Logger.getLogger(MovimentacaoController.class);

	private Movimentacao saldoDisponivel;
	
	private List<Movimentacao> listaMovimentacoes;
	
	@ManagedProperty(value="#{movimentacaoServico}")
	private MovimentacaoServico movimentacaoServico;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	@Override
	public void buscar() {
		logger.info("Iniciando o método buscar");
		
		FacesMessage msg = null;
		
		try {
			listaMovimentacoes = movimentacaoServico.buscarMovimentacoesPorData(dataInicial, dataFinal);
			
			saldoDisponivel = movimentacaoServico.calcularSaldoDisponivel(listaMovimentacoes);
		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		logger.info("Finalizando o método buscar");
		
	}
	
	@Override
	public void inicializacao() {
		
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

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Movimentacao getSaldoDisponivel() {
		return saldoDisponivel;
	}


}
