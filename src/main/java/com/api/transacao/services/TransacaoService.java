package com.api.transacao.services;

import com.api.transacao.models.TransacaoModel;
import com.api.transacao.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {
    final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public TransacaoModel save(TransacaoModel transacaoModel) {
        return transacaoRepository.save(transacaoModel);
    }

    public Optional<Object[]> findAll(UUID idCliente, Date dataInicial, Date dataFinal) {
        return transacaoRepository.findAll(idCliente, dataInicial, dataFinal);
    }
}