/**
 * @author Hamilton dos Santos Junior
 * @date 28/10/2011
 *
 */
package br.com.hsj.financeiro.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.hsj.financeiro.controller.vo.NodeCategoria;
import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.CategoriaServico;
import br.com.hsj.financeiro.util.ComboUtils;

/**
 * 
 * ManagedBean que controla o cadastro de Categorias e SubCategorias
 * 
 * @author Hamilton dos Santos Junior
 * @date 28/10/2011
 *
 */
@ManagedBean(name="categoriaController")
@ViewScoped
public class CategoriaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030276392776415240L;

	private static Logger logger = Logger.getLogger(CategoriaController.class);
	
	private Categoria categoria;
	
	private Categoria subCategoria;
	
	private List<Categoria> listaCategorias = null;
	
	@ManagedProperty(value="#{categoriaServico}")
	private CategoriaServico categoriaServico;
	
	private List<SelectItem> itens;
	
	private String oncompletesalvarSubCategoria;
	
	private TreeNode root; 
	
	private TreeNode selectedNode;
	
	@PostConstruct
	public void init() {
		categoria = new Categoria();
		subCategoria = new Categoria();
		
		itens = ComboUtils.montarComboTipoMovimentacao();
		
		buscar();
	}
	
	/**
	 * Método utilizado por recuperar 
	 * @param _categoriaSelecionada
	 */
	public void adicionarSubCategoria(NodeCategoria _nodeSelecionada) {
		logger.info("Iniciando para adicionar uma subCategoria");

		logger.info("Categoria selecionada: " + _nodeSelecionada.getCategoria().getDescricao());
		
		oncompletesalvarSubCategoria = "";
		
		subCategoria = new Categoria();
		subCategoria.setCategoriaPai(_nodeSelecionada.getCategoria());
		
		logger.info("Iniciando o método salvar");
	}
	
	/**
	 * Método utilizado para edição de uma subCategoria
	 * @param _categoriaSelecionada
	 */
	public void editarCategoria(NodeCategoria _nodeSelecionado) {
		categoria = _nodeSelecionado.getCategoria();
		
	}
	
	public void limpar() {
		categoria = new Categoria();
	}
	
	/**
	 * Método utilizado para salvar uma categoria
	 */
	@Override
	public void salvar() {
		logger.info("Iniciando o método salvar");
		
		FacesMessage msg = null;
		try {
			categoriaServico.salvar(categoria);
			
			categoria = new Categoria();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro salvo com sucesso");
		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage());
		}
		
		logger.info("Finalizando o método salvar");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		buscar();
	}

	/**
	 * Método utilizado para salvar uma categoria
	 */
	public void salvarSubCategoria() {
		logger.info("Iniciando o método salvar");
		
		FacesMessage msg = null;
		try {
			Categoria pai = subCategoria.getCategoriaPai();
			
			if (pai.getSubCategorias() != null) {
				pai.getSubCategorias().add(subCategoria);
			}
			
			categoriaServico.salvar(pai);
			
			subCategoria = new Categoria();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro salvo com sucesso");
		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage());
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
			listaCategorias = categoriaServico.buscarTodasCategoriasComSubCategorias();
		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		root = new DefaultTreeNode("root", null);
		montarTree(listaCategorias, root);
		
		logger.info("Finalizando o método buscar");
	}

	/**
	 * Método que monta a tree view
	 * @param _categoria
	 * @param _root
	 */
	private void montarTree(List<Categoria> _listaCategorias, TreeNode _root) {
		if (listaCategorias != null) {
			for (Categoria categoria : _listaCategorias) {
				TreeNode cat = new DefaultTreeNode(new NodeCategoria(categoria), _root);
				cat.setExpanded(true);
				
				if (categoria.getSubCategorias() != null) {
					montarTree(categoria.getSubCategorias(), cat);
				}
			}
		}
	}


	/************************************************
	 * GETTERS AND SETTERS
	 ************************************************/

	
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	public Categoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Categoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<SelectItem> getItens() {
		return itens;
	}

	public String getOncompletesalvarSubCategoria() {
		return oncompletesalvarSubCategoria;
	}

	public void setOncompletesalvarSubCategoria(String oncompletesalvarSubCategoria) {
		this.oncompletesalvarSubCategoria = oncompletesalvarSubCategoria;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

}
