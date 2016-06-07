package principal;

import java.util.LinkedList;
import java.util.List;

public class Eleicao {

	private List<Candidato> listaDePresidentes;
	private List<Candidato> listaDeSenadores;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	private List<Integer> eleitoresQueVotaram = new LinkedList<>();
	private List<Voto> votos = new LinkedList<>();

	public Eleicao(List<Candidato> listaDePresidentes,
			List<Candidato> listaDeSenadores, List<Eleitor> listaDeEleitores) {
		
		this.listaDePresidentes = listaDePresidentes;
		this.listaDeSenadores = listaDeSenadores;
		this.listaDeEleitores = listaDeEleitores;
	}

	public List<Integer> getEleitoresQueVotaram() {
		return eleitoresQueVotaram;
	}

	public List<Voto> getVotos() {
		return votos;
	}

}
