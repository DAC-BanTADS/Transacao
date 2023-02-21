package com.api.transacao.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Objects;

@RabbitListener(queues = "transacao")
public class TransacaoReceiver {
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private TransacaoProducer transacaoProducer;
    @Autowired
    private TransacaoHelper transacaoHelper;

    @RabbitHandler
    public TransacaoTransfer receive(@Payload TransacaoTransfer transacaoTransfer) {
        if (transacaoTransfer.getAction().equals("save-transacao")) {
            if (Objects.isNull(transacaoTransfer.getTransacaoDto().getIdCliente())) {
                transacaoTransfer.setAction("failed-transacao");
                transacaoTransfer.setMessage(("Nenhum dado de Transacao foi passado."));
                return transacaoTransfer;
            }

            ResponseEntity<Object> response = transacaoHelper.saveTransacao(transacaoTransfer.getTransacaoDto());

            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                transacaoTransfer.setAction("success-transacao");
                return transacaoTransfer;
            }

            transacaoTransfer.setAction("failed-transacao");
            if (Objects.isNull(response.getBody())) {
                transacaoTransfer.setMessage(Objects.requireNonNull(response.getBody()).toString());
            } else {
                transacaoTransfer.setMessage("Houve uma falha na transação.");
            }
            return transacaoTransfer;
        }

        transacaoTransfer.setAction("failed-transacao");
        transacaoTransfer.setMessage("Ação informada não existe.");
        return transacaoTransfer;
    }
}