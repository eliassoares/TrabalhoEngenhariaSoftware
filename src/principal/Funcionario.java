package principal;

public class Funcionario extends APessoa {

	private Integer matricula;
	private Integer senha;

	public Funcionario(String nome, Integer tituloDeEleitor, Integer matricula,
			Integer senha) {

		super(nome, tituloDeEleitor);

		this.matricula = matricula;
		this.senha = senha;

	}

	public Integer getMatricula() {
		return matricula;
	}

	public Integer getSenha() {
		return senha;
	}

}
