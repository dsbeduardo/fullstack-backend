package com.example.atividade_4.service;

import com.example.atividade_4.model.Produto;
import com.example.atividade_4.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    public Produto obterProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}