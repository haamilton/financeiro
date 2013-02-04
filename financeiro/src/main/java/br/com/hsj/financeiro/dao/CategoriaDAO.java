package br.com.hsj.financeiro.dao;

import java.util.List;

import br.com.hsj.financeiro.entidade.Categoria;
import br.com.hsj.financeiro.entidade.TipoMovimentacao;
import br.com.hsj.financeiro.exception.DAOException;

/**
 * Interface responsável pela implementação de acesso a banco de dados referentes a {@link Categoria} e {@link SubCategoria}
 * @author Hamilton dos Santos Junior
 * @date 30/11/2011
 *
 */
public interface CategoriaDAO extends GenericDAO<Long, Categoria> {

	/**
	 * Método utilizado para buscar todas as categorias cadastradas com as respectivas subcategorias
	 * @return Lista de Categorias
	 * @throws DAOException
	 */
	public List<Categoria> buscarTodasCategoriasComSubCategorias() throws DAOException;
	
	/**
	 * Método que irá buscar as categorias por tipo de movimentação
	 * @param _tipoMovimentacao
	 * @return
	 * @throws DAOException
	 */
	public List<Categoria> buscarCategoriasPorTipoMovimentacao(TipoMovimentacao _tipoMovimentacao) throws DAOException;
	
}
