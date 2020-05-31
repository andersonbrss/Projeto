package br.com.projeto.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.projeto.enuns.TipoTelefone;

@Entity
@Table( name = "tb_telefone")
@NamedNativeQueries({
	@NamedNativeQuery( name = "Telefone.consultarTodos",
			query = "SELECT * FROM tb_telefone tel ORDER BY tel.tipoTelefone"),
	@NamedNativeQuery( name = "Telefone.consultarPorIdPessoa",
			query = "SELECT * FROM tb_telefone tel WHERE tel.pessoa_id =:idpessoa ORDER BY tel.tipoTelefone",
		      resultClass = Telefone.class),
	@NamedNativeQuery( name = "Telefone.deletePorIdPessoa",
			query = "DELETE FROM tb_telefone WHERE pessoa_id =:idpessoa")
})
public class Telefone implements Serializable {
	private static final long serialVersionUID = 8373881596136175083L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank( message = "O campo ddd deve ser informado." )
	@Column( length = 3)
	private String ddd;
	
	@NotBlank( message = "O campo numero deve ser informado." )
	@Column( length = 10 )
	@Size( min = 8, max = 10, message = "O tamanho maximo para o numero e 10 caracteres." )
	private String numero;
	
	@Enumerated( EnumType.STRING )
	@NotNull( message = "O campo tipo telefone deve ser informado." )
	private TipoTelefone tipoTelefone;
	
	@NotBlank( message = "O login do operador não pode ser nulo" )
	@Column( name = "login_operador" )
	private String loginOperador;
	
	@ManyToOne
	@JoinColumn( name = "pessoa_id" )
	private Pessoa pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getLoginOperador() {
		return loginOperador;
	}

	public void setLoginOperador(String loginOperador) {
		this.loginOperador = loginOperador;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
