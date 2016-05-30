package principal;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Eleicao {

	private Date inicio;
	private Date fim;
	private List<Candidato> listaDeCandidatos = new LinkedList<>();
	private List<Integer> eleitoresQueVotaram = new LinkedList<>();
	private Votos votos;

	public Eleicao(Date inicio, Date fim, List<Candidato> listaDeCandidatos) {

		this.inicio = inicio;
		this.fim = fim;
		this.listaDeCandidatos = listaDeCandidatos;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}

	public List<Candidato> getListaDeCandidatos() {
		return listaDeCandidatos;
	}

	public List<Integer> getEleitoresQueVotaram() {
		return eleitoresQueVotaram;
	}

	public Votos getVotos() {
		return votos;
	}

}
