package com.getmoney.service;

import com.getmoney.dto.request.UsuarioRequestDTO;
import com.getmoney.dto.response.UsuarioResponseDTO;
import com.getmoney.entity.Usuario;
import com.getmoney.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    /*
    public Usuario editarUsuario(Integer id, UsuarioRequestDTO requestDTO) {
    }

     */

}
