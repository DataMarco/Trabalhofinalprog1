package com.vicenzo.kiosk.model.atendimento;

import com.vicenzo.kiosk.model.pedido.ResumoPedido;

public class Atendimento {
    private final int numero;
    private StatusAtendimento status;
    private final ResumoPedido resumo;

    public Atendimento(int numero, StatusAtendimento status, ResumoPedido resumo) {
        this.numero = numero;
        this.status = status;
        this.resumo = resumo;
    }

    public int getNumero() { return numero; }
    public StatusAtendimento getStatus() { return status; }
    public void setStatus(StatusAtendimento status) { this.status = status; }
    public ResumoPedido getResumo() { return resumo; }
}
