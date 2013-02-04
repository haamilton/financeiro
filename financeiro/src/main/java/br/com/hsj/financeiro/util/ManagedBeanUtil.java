package br.com.hsj.financeiro.util;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.hsj.financeiro.entidade.Despesa;

/**
 * 
 * @author Hamilton dos Santos Junior
 * 20/10/2012
 *
 */
public class ManagedBeanUtil {

	
	/**
	 * Método que monta a Tree Table de despesas
	 * @param _listaDespesas
	 * @param _root
	 */
	public static void montarTreeDespesa(List<Despesa> _listaDespesas, TreeNode _root) {
		if (_listaDespesas != null) {
			for (Despesa desp : _listaDespesas) {
				TreeNode cat = new DefaultTreeNode(desp, _root);
				
				if (desp.getParcelas() != null) {
					cat.setExpanded(true);
					montarTreeDespesa(desp.getParcelas(), cat);
				}
			}
		}
	}
}
