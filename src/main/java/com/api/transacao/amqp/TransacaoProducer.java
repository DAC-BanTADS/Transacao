package com.api.transacao.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.amqp.core.Queue;

public class TransacaoProducer {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("transacao")
    private Queue queue;

    public void send(TransacaoTransfer transacaoTransfer) {
        this.template.convertAndSend(this.queue.getName(), transacaoTransfer);
    }
}