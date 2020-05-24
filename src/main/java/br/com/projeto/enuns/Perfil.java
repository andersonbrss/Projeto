package br.com.projeto.enuns;

public enum Perfil {

	GERENTE("Gerente"),
	ANALISTA("Analista"),
	ADM("ADM");
	
	private String descricao;
	
	private Perfil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
