package br.com.hsj.financeiro.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.hsj.financeiro.entidade.Usuario;
import br.com.hsj.financeiro.util.FacesUtils;

/**
 * ManagedBean criado para controlar as informações da sessão do usuário
 * @author Hamilton dos Santos Junior
 * 09/11/2012
 *
 */
@ManagedBean(name="usuarioController", eager=true)
@SessionScoped
public class UsuarioController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5467093339449617415L;
	
	private Usuario usuarioLogado =	null;
	
	@PostConstruct
	public void init() {
		usuarioLogado = FacesUtils.recuperarUsuarioLogado();
	}
	

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}


	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
