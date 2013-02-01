package br.com.hsj.financeiro.controller.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.DespesaServico;
import br.com.hsj.financeiro.util.ManagedBeanUtil;

/**
 * Classe utilitária para a pesquisa de despesas utilizada na popup de despesas
 * 
 * @author Hamilton dos Santos Junior
 * 20/10/2012
 *
 */
@Service("popUpPesquisaDespesaController")
public class PopUpPesquisaDespesaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6577529036855491332L;

	private static Logger logger = Logger.getLogger(PopUpPesquisaDespesaController.class);
	
	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	
	@Autowired
	private DespesaServico despesaServico;
	
	private TreeNode root; 
	
	public PopUpPesquisaDespesaController() {
		
	}
	
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

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setDespesaServico(DespesaServico despesaServico) {
		this.despesaServico = despesaServico;
	}
	
	
}
