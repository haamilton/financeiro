/**
 * @author Hamilton dos Santos Junior
 * @date 23/04/2012
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

import br.com.hsj.financeiro.entidade.CentroCusto;
import br.com.hsj.financeiro.entidade.TipoCentroCusto;
import br.com.hsj.financeiro.exception.BusinessException;
import br.com.hsj.financeiro.servico.CentroCustoServico;
import br.com.hsj.financeiro.util.ComboUtils;

/**
 * @author Hamilton dos Santos Junior
 * @date 23/04/2012
 *
 */
@ManagedBean(name="centroCustoController")
@ViewScoped
public class CentroCustoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6461673510138663244L;

	private static Logger logger = Logger.getLogger(CentroCustoController.class);
	
	@ManagedProperty(value="#{centroCustoServico}")
	private CentroCustoServico centroCustoServico;
	
	private CentroCusto centroCusto;
	
	private String descricao;
	
	private TipoCentroCusto tipoPesquisa;
	
	private List<SelectItem> itens;
	
	private List<CentroCusto> listaCentroCusto;

	@PostConstruct
	public void init() {
		itens = ComboUtils.montarComboTipoCentroCusto();
		
		buscar();
	}
	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.controller.BaseController#salvar()
	 */
	@Override
	public void salvar() {
		logger.info("Iniciando o método salvar");
		
		FacesMessage msg = null;
		try {
			if (centroCusto == null) {
				centroCusto = new CentroCusto();
			}
			
			centroCusto.setDescricao(descricao);
			centroCusto.setTipoCentroCusto(tipoPesquisa);
			
			centroCustoServico.salvar(centroCusto);
			
			centroCusto = null;
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Registro salvo com sucesso");
		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro", e.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		buscar();

		logger.info("Finalizando o método salvar");
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.controller.BaseController#buscar()
	 */
	@Override
	public void buscar() {
		logger.info("Iniciando o método buscar");
		
		FacesMessage msg = null;
		try {
			listaCentroCusto = centroCustoServico.buscar();
		} catch (BusinessException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na consulta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		logger.info("Finalizando o método buscar");
	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.controller.BaseController#limpar()
	 */
	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}

	public List<CentroCusto> getListaCentroCusto() {
		return listaCentroCusto;
	}
	
	
	public void setListaCentroCusto(List<CentroCusto> listaCentroCusto) {
		this.listaCentroCusto = listaCentroCusto;
	}

	public List<SelectItem> getItens() {
		return itens;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoCentroCusto getTipoPesquisa() {
		return tipoPesquisa;
	}
	
	public void setTipoPesquisa(TipoCentroCusto tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
	
	public void setCentroCustoServico(CentroCustoServico centroCustoServico) {
		this.centroCustoServico = centroCustoServico;
	}
	
}
