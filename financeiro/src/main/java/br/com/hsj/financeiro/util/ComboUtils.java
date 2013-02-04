/**
 * @author Hamilton dos Santos Junior
 * @date 23/11/2011
 *
 */
package br.com.hsj.financeiro.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.hsj.financeiro.entidade.TipoCentroCusto;
import br.com.hsj.financeiro.entidade.TipoDespesa;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;

/**
 * Classe utilitária responsável por montar as combobox utilizadas pelo sistema
 * 
 * @author Hamilton dos Santos Junior
 * @date 23/11/2011
 *
 */
public class ComboUtils {

	/**
	 * Método que percorre o Enum de {@link TipoMovimentacao} e cria uma lista de SelectItem 
	 * @return
	 */
	public static List<SelectItem> montarComboTipoMovimentacao() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for (TipoMovimentacao tipo : TipoMovimentacao.values()) {
			itens.add(new SelectItem(tipo, tipo.getDescricao()));
		}
		
		return itens;
		
	}

	/**
	 * Método que percorre o Enum de {@link TipoCentroCusto} e cria uma lista de SelectItem 
	 * @return
	 */
	public static List<SelectItem> montarComboTipoCentroCusto() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for (TipoCentroCusto tipo : TipoCentroCusto.values()) {
			itens.add(new SelectItem(tipo, tipo.getDescricao()));
		}
		
		return itens;
		
	}

	/**
	 * Método que percorre o Enum de {@link TipoDespesa} e cria uma lista de SelectItem 
	 * @return
	 */
	public static List<SelectItem> montarComboTipoDespesa() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		
		for (TipoDespesa tipo : TipoDespesa.values()) {
			itens.add(new SelectItem(tipo, tipo.getDescricao()));
		}
		
		return itens;
		
	}
	
}
