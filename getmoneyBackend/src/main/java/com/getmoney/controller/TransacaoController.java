package com.getmoney.controller;

import com.getmoney.dto.request.TransacaoRequestDTO;
import com.getmoney.entity.Transacao;
import com.getmoney.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Transacao>> listarTransacoes(){
        return ResponseEntity.ok(transacaoService.listarTransacoes());
    }

    @PostMapping("/criar")
    public ResponseEntity<Transacao> criarTransacao(@RequestBody TransacaoRequestDTO requestDTO) {
        // Service converte DTO --> Entidade e salva no banco
        Transacao transacaoSalva = transacaoService.criarTransacao(requestDTO);
        // Devolve a entidade salva
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoSalva);
    }
}
