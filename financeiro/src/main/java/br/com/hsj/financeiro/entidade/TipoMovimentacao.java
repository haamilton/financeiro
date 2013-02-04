package br.com.hsj.financeiro.entidade;

/**
 * Classe enum para manipular os Tipos de Movimentacao
 * @author G0015950
 *
 */
public enum TipoMovimentacao {

	DEBITO("Débito","D"), CREDITO("Crédito", "C");
	
	private String descricao;
	private String abreviatura;
	
	TipoMovimentacao(String _descricao, String _abreviatura) {
		descricao = _descricao;
		abreviatura = _abreviatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getAbreviatura() {
		return abreviatura;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return descricao;
	}
}
