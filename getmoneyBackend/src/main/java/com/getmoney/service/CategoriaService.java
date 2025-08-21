package com.getmoney.service;

import com.getmoney.entity.Categoria;
import com.getmoney.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
        return  this.categoriaRepository.findAll();
    }

    public Categoria criarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }
}
