package br.com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.dto.VisitaLojaRepresentanteDTO;
import br.com.empresa.service.VisitaLojaRepresentanteService;

@RestController
@RequestMapping("/visita")
public class VisitaLojaRepresentanteController {
	
	@Autowired
	private VisitaLojaRepresentanteService visitaLojaRepresentanteService;

	@GetMapping
	public List<VisitaLojaRepresentanteDTO> listar() {
		return visitaLojaRepresentanteService.buscarVisitasQueDevemSerFeitas();
	}

}