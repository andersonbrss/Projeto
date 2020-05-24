package br.com.projeto.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.projeto.enuns.Perfil;

@Entity
@Table( name = "tb_operador" )
@NamedNativeQueries({
	@NamedNativeQuery( name = "Operador.consultarTodos",
			query = "SELECT * FROM tb_operador op where op.perfil != 'ADM' ORDER BY op.nome")
})
public class Operador implements Serializable{
	private static final long serialVersionUID = 4321617561618549618L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( length = 100 )
	@NotBlank( message = "O campo nome deve ser informado." )
	@Size( max = 100, message = "O campo nome deve ter no maximo 100 caracteres." )
	private String nome;
	
	@Column( length = 100 )
	@NotBlank( message = "O campo login deve ser informado." )
	@Size( max = 15, message = "O campo login deve ter no maximo 15 caracteres." )
	private String login;
	
	@NotBlank
	private String senha;
	
	@Enumerated( EnumType.STRING )
	@NotNull( message = "O campo perfil deve ser informado" )
	private Perfil perfil;
	
	@NotNull
	@Column(name = "data_cadastro")
	private LocalDateTime data = LocalDateTime.now();
	
	public Operador() {}
	
	public Operador(
			@NotBlank(message = "O campo nome deve ser informado.") @Size(max = 100, message = "O campo nome deve ter no maximo 100 caracteres.") String nome,
			@NotBlank(message = "O campo login deve ser informado.") @Size(max = 15, message = "O campo login deve ter no maximo 15 caracteres.") String login,
			@NotBlank String senha, @NotNull(message = "O campo perfil deve ser informado") Perfil perfil) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public LocalDateTime getData() {
		return data;
	}
	
}
