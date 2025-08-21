package com.getmoney.controller;

import com.getmoney.entity.Usuario;
import com.getmoney.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<Usuario> listarUsuarioId(@PathVariable  Integer usuarioId){
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
            return ResponseEntity.ok(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/editar/{usuarioId}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer usuarioId,
                                                 @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.editarUsuario(usuarioId, usuario);
            return ResponseEntity.ok(usuarioAtualizado); // 200 OK com a usuario atualizado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar a usuario
        }
    }

    @DeleteMapping("/deletar/{usuarioId}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer usuarioId) {
        usuarioService.deletar(usuarioId);
        return ResponseEntity.noContent().build();
    }
}

