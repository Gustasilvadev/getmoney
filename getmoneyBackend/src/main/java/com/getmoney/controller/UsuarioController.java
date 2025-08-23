package com.getmoney.controller;

import com.getmoney.dto.request.LoginRequestDTO;
import com.getmoney.entity.Usuario;
import com.getmoney.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")
@Tag(name="Usuario", description = "Api de gerenciamento de usuarios")

public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    /**
     * Cria um novo usuario no sistema
     *
     */
    @PostMapping("/criarUsuario")
    @Operation(summary = "Criar novo usuário", description = "Endpoint para criar um novo registro de usuário")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    /**
     * Autentica um usuário com base nas credenciais fornecidas
     *
     */
    @PostMapping("/autenticarUsuario")
    @Operation(summary = "Autentica o usuário", description = "Endpoint para autenticar o usuário")
    public ResponseEntity<Boolean> autenticarUsuario(@RequestBody LoginRequestDTO loginRequest) {
        Boolean autenticado = usuarioService.autenticarUsuario(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(autenticado);
    }




    @GetMapping("/listar")
    @Operation(summary="Listar usuários", description="Endpoint para listar todos os usuários")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }


    @PutMapping("/editarPorUsuarioId/{usuarioId}")
    @Operation(summary="Editar usuário pelo id do usuário", description="Endpoint para editar pelo id do usuário")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer usuarioId,
                                                 @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.editarPorUsuarioId(usuarioId, usuario);
            return ResponseEntity.ok(usuarioAtualizado); // 200 OK com a usuario atualizado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar a usuario
        }
    }

}

