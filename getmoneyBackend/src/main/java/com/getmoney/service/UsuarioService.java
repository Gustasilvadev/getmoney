package com.getmoney.service;

import com.getmoney.dto.request.UsuarioRequestDTO;
import com.getmoney.dto.response.UsuarioResponseDTO;
import com.getmoney.entity.Usuario;
import com.getmoney.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Verifica se o email já está cadastrado
     * Codifica a senha antes de salvar
     * Define data de criação e status padrão
     */
    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuario.setDataCriacao(LocalDate.now());
        usuario.setStatus(1);

        return usuarioRepository.save(usuario);
    }

    /**
     * Busca o usuário pelo email no banco de dados
     * Compara a senha fornecida com o hash armazenado no banco de dados
     */
    public Boolean autenticarUsuario(String email, String senha) {
        System.out.println("Tentando autenticar: " + email);

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuário NÃO encontrado: " + email);
            return false;
        }

        Usuario usuario = usuarioOpt.get();

        boolean senhaConfere = passwordEncoder.matches(senha, usuario.getSenha());
        System.out.println("Senha confere: " + senhaConfere);

        return senhaConfere;
    }





    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.findAll();
    }


    public Usuario editarPorUsuarioId(Integer id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    // Atualiza apenas se os campos forem diferentes
                    if (!usuarioExistente.getNome().equals(usuarioAtualizado.getNome())) {
                        usuarioExistente.setNome(usuarioAtualizado.getNome());
                    }

                    if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail())) {
                        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    }
                    if (!usuarioExistente.getSenha().equals(usuarioAtualizado.getSenha())) {
                        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
                    }

                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id " + id));
    }


}
