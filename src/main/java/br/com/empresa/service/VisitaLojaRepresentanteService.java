package br.com.empresa.service;

import java.util.List;

import br.com.empresa.dto.VisitaLojaRepresentanteDTO;

public interface VisitaLojaRepresentanteService {
	public List<VisitaLojaRepresentanteDTO> buscarVisitasQueDevemSerFeitas();
}
