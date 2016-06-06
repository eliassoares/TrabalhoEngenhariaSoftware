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
	private List<Candidato> listaDeCandidatos = new LinkedList<>();
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

	public List<Eleitor> getListaDeEleitores() {
		return listaDeEleitores;
	}
	
	public void setEleicao(Date inicio, Date fim) {
		this.eleicao = new Eleicao(inicio, fim, this.listaDeCandidatos);
	}
	
	public void cadrastraEleitores(String nome, Integer tituloDeEleitor) {
		this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
	}
	
	public void cadrastaCandidatos(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {
		this.listaDeCandidatos.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
	}
	
	private boolean isFuncionarioValido(Integer matricula, Integer senha) {
		Integer senhaPadrao = 1234567;
		Integer matriculaPadrao = 2011049053;
		return (matricula.intValue() == matriculaPadrao.intValue()) && (senha.intValue() == senhaPadrao.intValue());
	}
}
