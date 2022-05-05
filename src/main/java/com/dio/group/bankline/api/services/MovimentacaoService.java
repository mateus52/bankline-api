package com.dio.group.bankline.api.services;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.group.bankline.api.dto.NovaMovimentacao;
import com.dio.group.bankline.api.model.Conta;
import com.dio.group.bankline.api.model.Correntista;
import com.dio.group.bankline.api.model.Movimentacao;
import com.dio.group.bankline.api.model.MovimentacaoTipo;
import com.dio.group.bankline.api.repository.CorrentistaRepository;
import com.dio.group.bankline.api.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private CorrentistaRepository correntistaRepository;

	public void save(NovaMovimentacao novaMovimentacao) {
		Movimentacao movimentacao = new Movimentacao();
			
		Double Valor = novaMovimentacao.getTipo() == MovimentacaoTipo.RECEITA ? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1;
		
		movimentacao.setDataHora(LocalDateTime.now());
		movimentacao.setDescrição(novaMovimentacao.getDescricao());
		movimentacao.setValor(Valor);
		movimentacao.setTipo(novaMovimentacao.getTipo());
		movimentacao.setIdConta(novaMovimentacao.getIdConta());
		
		Correntista correntista = correntistaRepository.findById(novaMovimentacao.getIdConta()).orElse(null);
		if(correntista != null) {
			correntista.getConta().setSaldo(correntista.getConta().getSaldo() + Valor);
			correntistaRepository.save(correntista);
		}
		
		repository.save(movimentacao);
	}
}
