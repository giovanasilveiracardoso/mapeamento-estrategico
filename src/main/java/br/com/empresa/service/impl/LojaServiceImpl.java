package br.com.empresa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Loja;
import br.com.empresa.repository.LojaRepository;
import br.com.empresa.service.LojaService;

@Service
public class LojaServiceImpl implements LojaService {

	@Autowired
	private LojaRepository lojaRepository;

	@Override
	public List<Loja> buscarTodos() {
		return lojaRepository.findAll();
	}

}
