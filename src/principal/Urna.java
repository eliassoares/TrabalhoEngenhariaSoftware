package principal;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class Urna {

	private Integer numeroDeSerie;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	// É importante separar os tipos de candidatos para saber se quando iniciar a eleição, se há no mínimo um de cada.
	private List<Candidato> listaDePresidentes = new LinkedList<>();
	private List<Candidato> listaDeSenadores = new LinkedList<>();
	private Funcionario funcionario;
	private Eleicao eleicao;
	
	public Urna(Integer numeroDeSerie, String nomeFuncionario, Integer tituloDeEleitor, Integer matricula, Integer senha) {
		
		while(!this.isFuncionarioValido(matricula, senha)) {
			JOptionPane.showMessageDialog(null, "A senha ou a matrícula foi digitada incorretamente!");
			matricula = Integer.valueOf(JOptionPane.showInputDialog("Digite a matrícula novamente!"));
			senha = Integer.valueOf(JOptionPane.showInputDialog("Digite a senha novamente!"));
		}
		JOptionPane.showMessageDialog(null, "Funcionário logado com sucesso!");
		
		this.funcionario = new Funcionario(nomeFuncionario, tituloDeEleitor, matricula, senha);
		this.numeroDeSerie = numeroDeSerie;
	}

	public Integer getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(Integer numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public Integer getQuantidadePresidentes() {
		return this.listaDePresidentes.size();
	}
	
	public Integer getQuantidadeSenadores() {
		return this.listaDeSenadores.size();
	}
	
	public void cadrastraEleitores(String nome, Integer tituloDeEleitor) {
		this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
		JOptionPane.showMessageDialog(null, "Eleitor cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void cadastraCandidatos(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {
		
		if(tipoDeCandidato.toLowerCase().equals("presidente")) {
			this.listaDePresidentes.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			JOptionPane.showMessageDialog(null, "Candidato cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
		} else if(tipoDeCandidato.toLowerCase().equals("senador")) {
			this.listaDeSenadores.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			JOptionPane.showMessageDialog(null, "Candidato cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Tipo de candidato inserido não existe!", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isFuncionarioValido(Integer matricula, Integer senha) {
		Integer senhaPadrao = 1234567;
		Integer matriculaPadrao = 2011049053;
		return (matricula.intValue() == matriculaPadrao.intValue()) && (senha.intValue() == senhaPadrao.intValue());
	}
}
