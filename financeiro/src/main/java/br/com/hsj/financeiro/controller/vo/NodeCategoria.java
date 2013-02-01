/**
 * @author Hamilton dos Santos Junior
 * @date 08/12/2011
 *
 */
package br.com.hsj.financeiro.controller.vo;

import java.io.Serializable;

import br.com.hsj.financeiro.entidade.Categoria;

/**
 * 
 * Classe utilitária criada para apresentar as informações de Categoria e SubCategoria
 * na Treetable 
 * 
 * @author Hamilton dos Santos Junior
 * @date 08/12/2011
 *
 */
public class NodeCategoria implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4316666995847016406L;
	private Categoria categoria;

	/**
	 * 
	 * @param _descricao
	 * @param _tipo
	 * @param _tipoMovimentacao
	 * @param _categoria
	 * @param _subCategoria
	 */
	public NodeCategoria(Categoria _categoria) {
		super();
		this.categoria = _categoria;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
