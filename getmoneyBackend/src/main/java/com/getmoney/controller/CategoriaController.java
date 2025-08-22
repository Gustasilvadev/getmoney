package com.getmoney.controller;

import com.getmoney.entity.Categoria;
import com.getmoney.entity.Usuario;
import com.getmoney.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/listar/{categoriaId}")
    public ResponseEntity<Categoria>listarCategoriaId(@PathVariable Integer categoriaId){
        try{
            Categoria categoria = categoriaService.buscarCategoriaId(categoriaId);
            return ResponseEntity.ok(categoria);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);

    }

    @PutMapping("/editar/{categoriaId}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Integer categoriaId,
                                                 @RequestBody Categoria categoria) {
        try {
            Categoria categoriaAtualizada = categoriaService.editarCategoria(categoriaId, categoria);
            return ResponseEntity.ok(categoriaAtualizada); // 200 OK com a usuario atualizado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar a usuario
        }
    }

    @DeleteMapping("/deletar/{categoriaId}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer categoriaId) {
        categoriaService.deletarCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }


}
