package com.example.barbeariaprj2.model;

import java.util.Date;

public class Agendamento {

    private Integer id;
    private String idUsuario;
    private String data;
    private String horario;
    private String status;
    //private String servico;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Agendamento() {
    }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*
    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }
    */
}
