package br.com.projeto.enuns;

public enum TipoTelefone {

	CELULAR("Celular"), FIXO("Fixo"), COMERCIAL("Comercial");
	
	private String descricao;
	
	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
