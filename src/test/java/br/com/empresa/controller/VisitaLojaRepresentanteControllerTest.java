package br.com.empresa.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.empresa.ControllerTestConfiguration;
import br.com.empresa.dto.VisitaLojaRepresentanteDTO;
import br.com.empresa.entity.Funcionario;
import br.com.empresa.entity.Loja;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.service.LojaService;

public class VisitaLojaRepresentanteControllerTest extends ControllerTestConfiguration{

	@MockBean
	private LojaService lojaService;
	
	@MockBean
	private FuncionarioService funcionarioService;

	@Autowired
	private ObjectMapper objMapper;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getVisitasSomenteUmRepresentanteIraVisitar() throws Exception {
		final List<Loja> lojas = new ArrayList<>();
		Loja loja1 = new Loja("Farmacia Normal", -27.586236,-48.524782);
		lojas.add(loja1);
		Loja loja2 = new Loja("Big Iguatemi",-27.590762,-48.514423);
		lojas.add(loja2);
		
		final List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionario1 = new Funcionario("Nunes Sanfona", -27.589184, -48.520238);
		funcionarios.add(funcionario1);
		Funcionario funcionario2 = new Funcionario("Vanessa Daniele", -27.591547, -48.579781);
		funcionarios.add(funcionario2);
		
		Map<String, String> lojasDistancias = new HashMap<String, String>();
		lojasDistancias.put(loja2.getNome(), "599 Metros");
		lojasDistancias.put(loja1.getNome(), "554 Metros");

		VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO1 = new VisitaLojaRepresentanteDTO(funcionario1.getNome(), lojasDistancias);
		VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO2 = new VisitaLojaRepresentanteDTO(funcionario2.getNome(), new HashMap<String, String>());
		
		List<VisitaLojaRepresentanteDTO> visitaLojaRepresentanteDTOs = new ArrayList<VisitaLojaRepresentanteDTO>();
		visitaLojaRepresentanteDTOs.add(visitaLojaRepresentanteDTO1);
		visitaLojaRepresentanteDTOs.add(visitaLojaRepresentanteDTO2);
		
		given(lojaService.buscarTodos()).willReturn(lojas);
		given(funcionarioService.buscarTodos()).willReturn(funcionarios);
		ResultActions resultActions = this.mvc.perform(get("/visita").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andExpect(content().string(objMapper.writeValueAsString(visitaLojaRepresentanteDTOs)));
	}
	
	@Test
	public void getVisitasDoisRepresentantesIramVisitar() throws Exception {
		final List<Loja> lojas = new ArrayList<>();
		Loja loja1 = new Loja("Confeitaria Bernadete", -27.5798099,-48.5404435);
		lojas.add(loja1);
		Loja loja2 = new Loja("Big Iguatemi",-27.590762,-48.514423);
		lojas.add(loja2);
		
		final List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionario1 = new Funcionario("Vanessa Daniele", -27.580485, -48.537214);
		funcionarios.add(funcionario1);
		Funcionario funcionario2 = new Funcionario("Nunes Sanfona", -27.589184, -48.520238);
		funcionarios.add(funcionario2);
		
		Map<String, String> lojasDistancias1 = new HashMap<String, String>();
		lojasDistancias1.put(loja1.getNome(), "327 Metros");
		VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO1 = new VisitaLojaRepresentanteDTO(funcionario1.getNome(), lojasDistancias1);
		
		Map<String, String> lojasDistancias2 = new HashMap<String, String>();
		lojasDistancias2.put(loja2.getNome(), "599 Metros");
		VisitaLojaRepresentanteDTO visitaLojaRepresentanteDTO2 = new VisitaLojaRepresentanteDTO(funcionario2.getNome(), lojasDistancias2);
		
		List<VisitaLojaRepresentanteDTO> visitaLojaRepresentanteDTOs = new ArrayList<VisitaLojaRepresentanteDTO>();
		visitaLojaRepresentanteDTOs.add(visitaLojaRepresentanteDTO1);
		visitaLojaRepresentanteDTOs.add(visitaLojaRepresentanteDTO2);
		
		given(lojaService.buscarTodos()).willReturn(lojas);
		given(funcionarioService.buscarTodos()).willReturn(funcionarios);
		ResultActions resultActions = this.mvc.perform(get("/visita").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andExpect(content().string(objMapper.writeValueAsString(visitaLojaRepresentanteDTOs)));
	}
	
	@Test
	public void getVisitasSemDados() throws Exception {
		final List<Loja> lojas = new ArrayList<>();
		final List<Funcionario> funcionarios = new ArrayList<>();
		
		given(lojaService.buscarTodos()).willReturn(lojas);
		given(funcionarioService.buscarTodos()).willReturn(funcionarios);
		ResultActions resultActions = this.mvc.perform(get("/visita").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andExpect(content().string("[ ]"));
	}
}
