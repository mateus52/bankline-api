package com.dio.group.bankline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.group.bankline.api.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

}
