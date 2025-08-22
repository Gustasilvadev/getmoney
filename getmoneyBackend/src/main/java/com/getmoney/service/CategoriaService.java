package com.getmoney.service;

import com.getmoney.entity.Categoria;
import com.getmoney.entity.Usuario;
import com.getmoney.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Categoria buscarCategoriaId(Integer categoriaId) {
        return categoriaRepository.findById(categoriaId).orElseThrow(() -> new EntityNotFoundException("Categoria com ID " + categoriaId + " não encontrado"));
    }

    public Categoria criarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    public Categoria editarCategoria(Integer categoriaId, Categoria categoria){
        return categoriaRepository.findById(categoriaId)
                .map(categoriaExistente -> {

                    if (!categoriaExistente.getNome().equals(categoria.getNome())) {
                        categoriaExistente.setNome(categoria.getNome());
                    }
                    if (!categoriaExistente.getTipo().equals(categoria.getTipo())) {
                        categoriaExistente.setTipo(categoria.getTipo());
                    }
                    return categoriaRepository.save(categoriaExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado com id " + categoriaId));
    }

    public void deletarCategoria(Integer categoriaId){
        boolean categoriaExistente = categoriaRepository.existsById(categoriaId);
        if(categoriaExistente){
            categoriaRepository.deleteById(categoriaId);
        }
    }
}
