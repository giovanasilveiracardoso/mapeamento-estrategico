package br.com.empresa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.dto.VisitaLojaRepresentanteDTO;
import br.com.empresa.entity.Funcionario;
import br.com.empresa.entity.Loja;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.service.LojaService;
import br.com.empresa.service.VisitaLojaRepresentanteService;
import br.com.empresa.service.util.UtilGeolocation;

@Service
public class VisitaLojaRepresentanteServiceImpl implements VisitaLojaRepresentanteService {

	@Autowired
	private LojaService lojaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public List<VisitaLojaRepresentanteDTO> buscarVisitasQueDevemSerFeitas() {
		final List<Loja> lojas = lojaService.buscarTodos();
		final List<Funcionario> funcionarios = funcionarioService.buscarTodos();
		final List<VisitaLojaRepresentanteDTO> visitaLojaRepresentanteDTOs = new ArrayList<VisitaLojaRepresentanteDTO>();

		for (Funcionario funcionario : funcionarios) {
			Map<String, String> lojasDistancias = this.getLojasDistancias(lojas, funcionario);

			final VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO = this.criarVisitaLojaRepresentanteDTO(funcionario, lojasDistancias);
			visitaLojaRepresentanteDTOs.add(visitaLojaRepresentanteDTO);
		}

		return visitaLojaRepresentanteDTOs;
	}

	private Map<String, String> getLojasDistancias(final List<Loja> lojas, Funcionario funcionario) {
		Map<String, String> lojasDistancias = new HashMap<String, String>();
		List<Loja> lojasRepresentante = new ArrayList<Loja>();

		for (Loja loja : lojas) {
			Double distancia = UtilGeolocation.haversine(funcionario.getLatitude(), funcionario.getLongitude(), loja.getLatitude(),
					loja.getLongitude());

			if (distancia <= 2.0) {
				lojasDistancias.put(loja.getNome(), UtilGeolocation.formatarDistancia(distancia));
				lojasRepresentante.add(loja);
			}
		}

		lojas.removeAll(lojasRepresentante);

		return lojasDistancias;
	}

	private VisitaLojaRepresentanteDTO criarVisitaLojaRepresentanteDTO(Funcionario funcionario, Map<String, String> lojasDistancias) {
		final VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO = new VisitaLojaRepresentanteDTO();
		visitaLojaRepresentanteDTO.setNomeRepresentante(funcionario.getNome());
		visitaLojaRepresentanteDTO.setLojasDistancias(lojasDistancias);

		return visitaLojaRepresentanteDTO;
	}

}
