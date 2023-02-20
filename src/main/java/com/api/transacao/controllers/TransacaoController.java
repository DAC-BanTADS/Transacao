package com.api.transacao.controllers;

import com.api.transacao.dtos.TransacaoDto;
import com.api.transacao.models.TransacaoModel;
import com.api.transacao.services.TransacaoService;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transacao")
public class TransacaoController {
    final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTransacao(@RequestBody @Valid TransacaoDto transacaoDto){
        var transacaoModel = new TransacaoModel();
        BeanUtils.copyProperties(transacaoDto, transacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.save(transacaoModel));
    }

    @GetMapping("/{idCliente}/{dataInicial}/{dataFinal}")
    public ResponseEntity<?> getTransacaoById(
            @PathVariable(value = "idCliente") UUID idCliente,
            @PathVariable(value = "dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @PathVariable(value = "dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dataFinal
            ){
        List<TransacaoModel> transacaoModelList =
                transacaoService.findAll(idCliente, dataInicial, dataFinal);
        if (transacaoModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma transacão encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transacaoModelList);
    }
}