package br.com.projeto.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.projeto.enuns.TipoPessoa;

@Entity
@Table( name = "tb_pessoa")
@NamedNativeQueries({
	@NamedNativeQuery( name = "Pessoa.consultarTodos",
			query = "SELECT * FROM tb_pessoa p ORDER BY p.nome")
})
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 300151065173278250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank( message = "O campo nome deve ser informado." )
	@Column( length = 100 )
	private String nome;
	
	@NotBlank( message = "O campo do documento deve ser informado.")
	private String documento; 
	
	@NotNull( message = "O campo data de nascimento deve ser informado.")
	@Column( name = "data_nascimento" )
	private LocalDate dataNascimento = LocalDate.now();
	
	@Column( length = 100 )
	private String nome_mae;
	
	@Column( length = 100 )
	private String nome_pai;
	
	@NotNull
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@NotBlank
	@Column( name = "login_operador" )
	private String loginOperador;
	
	@Enumerated( EnumType.STRING )
	@NotNull( message = "O campo tipo pessoa deve ser informado." )
	private TipoPessoa tipoPessoa;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "pessoa" )
	private Set< Telefone > telefones = new HashSet<>();
	
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	public String getNome_mae() {
		return nome_mae;
	}

	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}

	public String getNome_pai() {
		return nome_pai;
	}

	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getLoginOperador() {
		return loginOperador;
	}

	public void setLoginOperador(String loginOperador) {
		this.loginOperador = loginOperador;
	}
	
}
