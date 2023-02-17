package com.api.transacao.amqp;

import com.api.transacao.dtos.TransacaoDto;
import com.api.transacao.models.TransacaoModel;
import com.api.transacao.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransacaoHelper {
    final TransacaoService transacaoService;

    public TransacaoHelper(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public ResponseEntity<Object> saveTransacao(@Valid TransacaoDto transacaoDto){
        var transacaoModel = new TransacaoModel();
        BeanUtils.copyProperties(transacaoDto, transacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.save(transacaoModel));
    }
}