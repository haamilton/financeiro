/**
 * @author Hamilton dos Santos Junior
 * @date 23/04/2012
 *
 */
package br.com.hsj.financeiro.entidade;

/**
 * @author Hamilton dos Santos Junior
 * @date 23/04/2012
 *
 */
public enum TipoCentroCusto {

	CONTA_CORRENTE("Conta corrente"), CONTA_POUPANCA("Conta poupança"), CARTAO_CREDITO("Cartão de crédito");
	
	private String descricao;
	
	TipoCentroCusto(String _descricao) {
		descricao = _descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return descricao;
	}
	
}
