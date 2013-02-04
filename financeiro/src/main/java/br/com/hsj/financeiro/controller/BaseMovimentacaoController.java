package br.com.hsj.financeiro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.entidade.CentroCusto;
import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.CategoriaServico;
import br.com.hsj.financeiro.servico.CentroCustoServico;
import br.com.hsj.financeiro.servico.DespesaServico;
import br.com.hsj.financeiro.servico.MovimentacaoServico;
import br.com.hsj.financeiro.util.ComboUtils;

/**
 * Managed bean que manipula as informações das movimentações do mês atual
 * 
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
public abstract class BaseMovimentacaoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127463673218687499L;
	
	private static Logger logger = Logger.getLogger(BaseMovimentacaoController.class);
	
	private Movimentacao movimentacao;
	
	private Movimentacao saldoDisponivel;
	
	private List<Movimentacao> listaMovimentacoes;
	
	private List<SelectItem> itens;
	
	private List<SelectItem> itensCategorias;

	private List<SelectItem> itensCentroCusto;
	
	private List<Despesa> listaDespesas;

	@ManagedProperty(value="#{movimentacaoServico}")
	private MovimentacaoServico movServico;

	@ManagedProperty(value="#{categoriaServico}")
	private CategoriaServico categoriaServico;

	@ManagedProperty(value="#{centroCustoServico}")
	private CentroCustoServico centroCustoServico;

	@ManagedProperty(value="#{despesaServico}")
	private DespesaServico despesaServico;
	
	@PostConstruct
	public void init() {
		inicializarMovimentacao();
		
		itens = ComboUtils.montarComboTipoMovimentacao();
		
		buscarCategorias();
		
		buscarCentroCusto();
		
		inicializacao();
	}
	
	public abstract void inicializacao();

	/**
	 * Método que inicializa o objeto {@link Movimentacao}
	 */
	private void inicializarMovimentacao() {
		movimentacao = new Movimentacao();
		movimentacao.setCategoria(new Categoria());
		movimentacao.setCentroCusto(new CentroCusto());
		
		// adicionando a movimentação de Débito como padrão
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
	}
	
	/**
	 * Método que busca as subcategorias com o tipo de movimentação selecionado 
	 */
	private void buscarCategorias() {
		List<Categoria> lista = null;
		
		try {
			lista = categoriaServico.buscarCategoriasPorTipoMovimentacao(movimentacao.getTipoMovimentacao());
		} catch (BusinessException e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar as Categorias", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (lista != null) {
			itensCategorias = new ArrayList<SelectItem>();
			
			itensCategorias.add(new SelectItem(new Long(0), "Selecione"));
			for (Categoria sub : lista) {
				itensCategorias.add(new SelectItem(sub.getId(), sub.getDescricao()));
			}
		}
	}

	/**
	 * Método que busca os centros de custo 
	 */
	private void buscarCentroCusto() {
		List<CentroCusto> lista = null;
		
		try {
			lista = centroCustoServico.buscar();
		} catch (BusinessException e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar os Centros de Custos", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		if (lista != null) {
			itensCentroCusto = new ArrayList<SelectItem>();
			
			itensCentroCusto.add(new SelectItem(new Long(0), "Selecione"));
			for (CentroCusto sub : lista) {
				itensCentroCusto.add(new SelectItem(sub.getId(), sub.getDescricao()));
			}
		}
	}

	
	/**
	 * Método utilizado para salvar uma movimentação
	 */
	public void salvar() {
		logger.info("Iniciando o método salvar");
		
		FacesMessage msg = null;
		
		try {
			movServico.salvar(movimentacao);
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro salvo com sucesso");
			
			inicializarMovimentacao();
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
	
	public void limparPopUpDespesa() {
		listaDespesas = null;
	}
	
	public void selecionarDespesa(Despesa _despesa) {
		movimentacao.setDespesa(_despesa);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Despesa selecionada"));
	}
	
	/**
	 * Método utilizado para buscar as despesas
	 */
	public void buscarDespesas() {
		logger.info("Buscando as despesas");
		
		FacesMessage msg = null;
		try {
			listaDespesas = despesaServico.buscarTodasDespesas();
		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	/**
	 * Método utilziado para buscar as movimentacoes
	 */
	public abstract void buscar();

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.controller.BaseController#limpar()
	 */
	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Método que recebe uma movimentação por parametro e 
	 * se for de Débito retorna <code>true</code>
	 * @param _movimentacao
	 * @return
	 */
	public boolean verificarDebito(Movimentacao _movimentacao) {
		boolean debito = false;
		
		if (TipoMovimentacao.DEBITO.equals(_movimentacao.getTipoMovimentacao())) {
			debito = true;
		}
		
		return debito;
	}
	
	/************************************************
	 * GETTERS AND SETTERS
	 ************************************************/
	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovServico(MovimentacaoServico movServico) {
		this.movServico = movServico;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<SelectItem> getItens() {
		return itens;
	}

	public List<Movimentacao> getListaMovimentacoes() {
		return listaMovimentacoes;
	}

	public List<SelectItem> getItensCentroCusto() {
		return itensCentroCusto;
	}

	public void setListaMovimentacoes(List<Movimentacao> listaMovimentacoes) {
		this.listaMovimentacoes = listaMovimentacoes;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<SelectItem> getItensCategorias() {
		return itensCategorias;
	}

	public Movimentacao getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setCentroCustoServico(CentroCustoServico centroCustoServico) {
		this.centroCustoServico = centroCustoServico;
	}

	public void setDespesaServico(DespesaServico despesaServico) {
		this.despesaServico = despesaServico;
	}

	public List<Despesa> getListaDespesas() {
		return listaDespesas;
	}

}
