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
	}

	public boolean hasEleitor(Integer tituloEleitor) {
		for (Iterator iterator = this.listaDeEleitores.iterator(); iterator.hasNext();) {
			Eleitor eleitor = (Eleitor) iterator.next();
			if(eleitor.getTituloDeEleitor().intValue() == tituloEleitor.intValue()) {
				return true;
			}
			
		}
		return false;
	}

	public List<Integer> getEleitoresQueVotaram() {
		return eleitoresQueVotaram;
	}
	
	public String getEleitorName(Integer titulo) {
		for (Iterator iterator = this.listaDeEleitores.iterator(); iterator.hasNext();) {
			Eleitor eleitor = (Eleitor) iterator.next();
			if(eleitor.getTituloDeEleitor().intValue() == titulo.intValue()) {
				return eleitor.getNome();
			}
		}
		return null;
	}

	public Votos getVotos() {
		return votos;
	}

}
