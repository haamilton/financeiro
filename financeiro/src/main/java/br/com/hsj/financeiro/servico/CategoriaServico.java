package br.com.hsj.financeiro.servico;

import java.util.List;

import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.BusinessException;

/**
 * Interface responsável pela implementação das regras de negócio referentes a {@link Categoria} e {@link SubCategoria}
 * @author Hamilton dos Santos Junior
 * @date 30/11/2011
 *
 */
public interface CategoriaServico extends BaseServico<Categoria> {

	public List<Categoria> buscar() throws BusinessException;
	
	/**
	 * Método utilizado para buscar todas as categorias cadastradas com as respectivas subcategorias
	 * @return Lista de Categorias
	 * @throws BusinessException
	 */
	public List<Categoria> buscarTodasCategoriasComSubCategorias() throws BusinessException;
	
	/**
	 * Método que busca todas as subcategorias por tipo de movimentacao
	 * @param _tipoMovimentacao
	 * @return
	 * @throws BusinessException
	 */
	public List<Categoria> buscarCategoriasPorTipoMovimentacao(TipoMovimentacao _tipoMovimentacao) throws BusinessException; 
}
