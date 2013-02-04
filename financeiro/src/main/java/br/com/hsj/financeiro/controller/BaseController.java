/**
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
package br.com.hsj.financeiro.controller;

import java.io.Serializable;

/**
 * @author Hamilton dos Santos Junior
 * @date 07/10/2011
 *
 */
@SuppressWarnings("serial")
public abstract class BaseController implements Serializable {


	public abstract void salvar();

	public abstract void buscar();

	public abstract void limpar();
	
	

}
