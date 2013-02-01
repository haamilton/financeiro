/**
 * @author Hamilton dos Santos Junior
 * @date 29/11/2011
 *
 */
package br.com.hsj.financeiro.util;

import java.util.Map;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.hsj.financeiro.entidade.Usuario;

/**
 * @author Hamilton dos Santos Junior
 * @date 29/11/2011
 * 
 */
public class FacesUtils {

	/**
	 * Método que retorna o Request
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	/**
	 * Método que retorna o ServletContext
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}

	public static void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	private static void exibirMensagem(FacesMessage.Severity severity,
			String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, "", mensagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	@SuppressWarnings("rawtypes")
	public static Map getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap();
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}
	
	public static Object getManagedBean(final String beanName) {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    Object bean;
	    
	    try {
	        ELContext elContext = fc.getELContext();
	        bean = elContext.getELResolver().getValue(elContext, null, beanName);
	    } catch (RuntimeException e) {
	        throw new FacesException(e.getMessage(), e);
	    }

	    if (bean == null) {
	        throw new FacesException("Managed bean with name '" + beanName
	            + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
	    }

	    return bean;
	}

	/**
	 * Método utilizado para recuperar o usuário logado do contexto do Spring
	 * @return
	 */
	public static Usuario recuperarUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
