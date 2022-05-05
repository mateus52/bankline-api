package com.dio.group.bankline.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.group.bankline.api.dto.NovaMovimentacao;
import com.dio.group.bankline.api.model.Movimentacao;
import com.dio.group.bankline.api.repository.MovimentacaoRepository;
import com.dio.group.bankline.api.services.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private MovimentacaoService service;
	
	@GetMapping
	public List<Movimentacao> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public void save(@RequestBody NovaMovimentacao novaMovimentacao) {
		service.save(novaMovimentacao);
	}

}
