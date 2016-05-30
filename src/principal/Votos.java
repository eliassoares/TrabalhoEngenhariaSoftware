package principal;

import java.util.HashMap;
import java.util.Map;

public class Votos {

	private Map<Candidato, Integer> votosPorCandidato = new HashMap<>();
	private String resultado;
	private Map<Candidato, Float> percentuaisPresidente = new HashMap<>();
	private Map<Candidato, Float> percentuaisSenador = new HashMap<>();
	private Integer nulos;
	private Integer brancos;

	public Votos() {

	}

	public Map<Candidato, Integer> getVotosPorCandidato() {
		return votosPorCandidato;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Map<Candidato, Float> getPercentuaisPresidente() {
		return percentuaisPresidente;
	}

	public Map<Candidato, Float> getPercentuaisSenador() {
		return percentuaisSenador;
	}

	public Integer getNulos() {
		return nulos;
	}

	public void setNulos(Integer nulos) {
		this.nulos = nulos;
	}

	public Integer getBrancos() {
		return brancos;
	}

	public void setBrancos(Integer brancos) {
		this.brancos = brancos;
	}

}
