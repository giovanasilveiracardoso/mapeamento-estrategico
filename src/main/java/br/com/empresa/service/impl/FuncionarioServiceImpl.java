package br.com.empresa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Funcionario;
import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public List<Funcionario> buscarTodos() {
		return funcionarioRepository.findAll();
	}

}
