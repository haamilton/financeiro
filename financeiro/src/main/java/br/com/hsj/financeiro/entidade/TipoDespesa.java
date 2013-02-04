package br.com.hsj.financeiro.entidade;

public enum TipoDespesa {

	SIMPLES("Simples"), MENSAL("Mensal"), PARCELADA("Parcelada");
	
	private String descricao;
	
	TipoDespesa(String _descricao) {
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
