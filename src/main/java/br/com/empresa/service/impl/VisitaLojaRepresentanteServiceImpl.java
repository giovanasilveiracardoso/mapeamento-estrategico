package br.com.empresa.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

@Service
public class VisitaLojaRepresentanteServiceImpl implements VisitaLojaRepresentanteService {

	private final static Double RAIO_TOTAL_DA_TERRA = 6371.0;
	private final static Double PI_EM_RADIANOS = 0.017453292519943295;

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
			Double distancia = this.haversine(funcionario.getLatitude(), funcionario.getLongitude(), loja.getLatitude(),
					loja.getLongitude());

			if (distancia <= 2.0) {
				lojasDistancias.put(loja.getNome(), distanciaFormatada(distancia));
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

	public Double haversine(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
		Double somatorioInterno = 0.5 - Math.cos((latitude2 - latitude1) * PI_EM_RADIANOS) / 2
				+ Math.cos(latitude1 * PI_EM_RADIANOS) * Math.cos(latitude2 * PI_EM_RADIANOS)
						* (1 - Math.cos((longitude2 - longitude1) * PI_EM_RADIANOS)) / 2;

		return (RAIO_TOTAL_DA_TERRA * 2) * Math.asin(Math.sqrt(somatorioInterno));
	}

	public static String distanciaFormatada(final Double distancia) {
		if (distancia > 1.0) {
			BigDecimal distanciaEmBigDecimal = new BigDecimal(distancia);
			distanciaEmBigDecimal = distanciaEmBigDecimal.setScale(0, RoundingMode.HALF_UP);
			return distanciaEmBigDecimal.toString() + " Km";
		} else if (distancia != 0.0) {
			return distancia.toString().substring(2, 5) + " Metros";
		} else {
			return "0 Metros";
		}
	}

}
