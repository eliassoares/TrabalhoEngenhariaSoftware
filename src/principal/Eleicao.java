package principal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Eleicao {

	private List<Candidato> listaDePresidentes;
	private List<Candidato> listaDeSenadores;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	private List<Integer> eleitoresQueVotaram = new LinkedList<>();
	private Votos votos;

	public Eleicao (List<Candidato> listaDePresidentes, List<Candidato> listaDeSenadores, List<Eleitor> listaDeEleitores) {
		this.listaDePresidentes = listaDePresidentes;
		this.listaDeSenadores = listaDeSenadores;
		this.listaDeEleitores = listaDeEleitores;
		this.votos = new Votos();
	}

	

	public List<Integer> getEleitoresQueVotaram() {
		return eleitoresQueVotaram;
	}
	
	

	public Votos getVotos() {
		return votos;
	}

}
