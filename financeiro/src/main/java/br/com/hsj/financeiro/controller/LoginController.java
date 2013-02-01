/**
 * @author Hamilton dos Santos Junior
 * @date 03/04/2012
 *
 */
package br.com.hsj.financeiro.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import br.com.hsj.financeiro.util.FacesUtils;

/**
 * @author Hamilton dos Santos Junior
 * @date 03/04/2012
 * 
 */
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6757624352446381170L;

	private String login;
	private String senha;

	public String logar() throws IOException, ServletException {
		if (StringUtils.isBlank(login) || StringUtils.isBlank(senha)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Digite login e senha"));
			return null;
		}
			
		RequestDispatcher dispatcher = FacesUtils.getServletRequest()
				.getRequestDispatcher(
						"/j_spring_security_check?j_username=" + login
								+ "&j_password=" + br.com.hsj.financeiro.util.StringUtils.encriptar(senha));
		dispatcher.forward(FacesUtils.getServletRequest(),
				FacesUtils.getServletResponse());
		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}
	

	public String logout() throws ServletException, IOException {
		RequestDispatcher dispatcher = FacesUtils.getServletRequest()
				.getRequestDispatcher(
						"/j_spring_security_logout");
		dispatcher.forward(FacesUtils.getServletRequest(),
				FacesUtils.getServletResponse());
		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hsj.financeiro.controller.BaseController#salvar()
	 */
	@Override
	public void salvar() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hsj.financeiro.controller.BaseController#buscar()
	 */
	@Override
	public void buscar() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.controller.BaseController#limpar()
	 */
	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
