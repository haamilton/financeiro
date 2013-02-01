package br.com.hsj.financeiro.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.entidade.TipoDespesa;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.DespesaServico;
import br.com.hsj.financeiro.util.ComboUtils;
import br.com.hsj.financeiro.util.ManagedBeanUtil;

/**
 * Método responsável por manipular as informações da tela de despesas 
 * @author Hamilton dos Santos Junior
 *
 */
@ManagedBean(name="despesaController")
@ViewScoped
public class DespesaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3052646928484592380L;
	
	private static Logger logger = Logger.getLogger(DespesaController.class);

	private List<SelectItem> itens;
	
	private Despesa despesa;
	
	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	
	private boolean apresentarComboParcelas = false;
	
	@ManagedProperty(value="#{despesaServico}")
	private DespesaServico despesaServico;
	
	private TreeNode root; 
	
	@PostConstruct
	public void init() {
		itens = ComboUtils.montarComboTipoDespesa();
		
		despesa = new Despesa();
		despesa.setTipoDespesa(TipoDespesa.SIMPLES);
		
		buscar();
	}
	
	public String novo() {
		despesa = new Despesa();
		despesa.setTipoDespesa(TipoDespesa.SIMPLES);
		return "/sec/despesa/despesas.xhtml?faces-redirect=true";
	}
	
	@Override
	public void salvar() {
		logger.info("Iniciando o método salvar");
		
		FacesMessage msg = null;
		try {
			despesaServico.salvar(despesa);
			
			despesa = new Despesa();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro salvo com sucesso");
		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		logger.info("Finalizando o método salvar");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);

		buscar();
	}

	@Override
	public void buscar() {
		logger.info("Iniciando o método buscar");
		
		FacesMessage msg = null;
		try {
			listaDespesas = despesaServico.buscarTodasDespesas();
		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		root = new DefaultTreeNode("root", null);
		ManagedBeanUtil.montarTreeDespesa(listaDespesas, root);
		
		logger.info("Finalizando o método buscar");
		
	}
	
	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}

	public void habilitarCamposParcelas(AjaxBehaviorEvent _ev) {
		apresentarComboParcelas = despesa.getTipoDespesa().equals(TipoDespesa.PARCELADA);
	}
	
	public void gerarParcelas() {
		int qtde = despesa.getQtdeParcelas();
		
		BigDecimal[] valorParcela = null;
		
		if (despesa.getValor() != null) {
			// utlizado o método divideAndRemainder para divisões onde os valores não são exatos
			valorParcela = despesa.getValor().divideAndRemainder(new BigDecimal(qtde));
		}
		
		despesa.setParcelas(new ArrayList<Despesa>());
		
		Calendar cal = GregorianCalendar.getInstance();
		
		for (int i = 0; i < qtde; i++) {
			Despesa parcela = new Despesa();
			parcela.setNumeroParcela(i + 1);
			
			parcela.setValor(valorParcela[0]);
			
			if (i == 0) {
				parcela.setValor(valorParcela[0].add(valorParcela[1]));
			}
			parcela.setDataVencimento(cal.getTime());
			parcela.setDespesaPai(despesa);
			parcela.setTipoDespesa(TipoDespesa.PARCELADA);
			
			despesa.getParcelas().add(parcela);
			
			cal.add(Calendar.MONTH, 1);
		}
	}
	
	
	public boolean isApresentarComboParcelas() {
		return apresentarComboParcelas;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<SelectItem> getItens() {
		return itens;
	}

	public void setItens(List<SelectItem> itens) {
		this.itens = itens;
	}

	public void setDespesaServico(DespesaServico despesaServico) {
		this.despesaServico = despesaServico;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
