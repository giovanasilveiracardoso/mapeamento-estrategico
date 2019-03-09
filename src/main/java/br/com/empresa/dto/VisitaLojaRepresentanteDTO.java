package br.com.empresa.dto;

import java.util.Map;

public class VisitaLojaRepresentanteDTO {

	private String nomeRepresentante;
	private Map<String, String> lojasDistancias;
	

	public VisitaLojaRepresentanteDTO(String nomeRepresentante, Map<String, String> lojasDistancias) {
		this.nomeRepresentante = nomeRepresentante;
		this.lojasDistancias = lojasDistancias;
	}

	public VisitaLojaRepresentanteDTO() {
	}

	public String getNomeRepresentante() {
		return nomeRepresentante;
	}

	public void setNomeRepresentante(String nomeRepresentante) {
		this.nomeRepresentante = nomeRepresentante;
	}

	public Map<String, String> getLojasDistancias() {
		return lojasDistancias;
	}

	public void setLojasDistancias(Map<String, String> lojasDistancias) {
		this.lojasDistancias = lojasDistancias;
	}

}
