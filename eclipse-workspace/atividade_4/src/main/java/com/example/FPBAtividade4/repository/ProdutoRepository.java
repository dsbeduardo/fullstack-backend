package com.example.atividade_4.repository;

import com.example.atividade_4.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContaining(String nome);
    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> findProdutosComPrecoMaiorQue(@Param("preco") double preco);
}
