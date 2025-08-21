package com.getmoney.controller;

import com.getmoney.entity.Categoria;
import com.getmoney.service.CategoriaService;
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

    @PostMapping("/criar")
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);

    }
}
