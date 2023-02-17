package com.api.transacao.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TRANSACAO")
public class TransacaoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = false)
    private double valorTransacao;
    @Column(nullable = false)
    private UUID idCliente;
    @Column(nullable = false)
    private String tipoTransacao;
    @Column(nullable = false)
    private UUID idClienteDestinatario;
    @Column(nullable = false)
    private double saldo;
    @Column()
    private String color;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public UUID getIdClienteDestinatario() {
        return idClienteDestinatario;
    }

    public void setIdClienteDestinatario(UUID idClienteDestinatario) {
        this.idClienteDestinatario = idClienteDestinatario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
