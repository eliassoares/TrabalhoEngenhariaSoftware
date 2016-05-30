package principal;

import java.util.LinkedList;
import java.util.List;

public class Urna {

	private Integer numeroDeSerie;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();

	public Urna(Integer numeroDeSerie) {

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

}
