package br.com.everton.backend.beans;

import java.math.BigDecimal;

public class Veiculo {

    private long id;
    private String dataDeCadastro;
    private String placa;
    private int renavam;
    private String modelo;
    private String opcionais;
    private BigDecimal valorDeVenda;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getRenavam() {
        return renavam;
    }

    public void setRenavam(int renavam) {
        this.renavam = renavam;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(String opcionais) {
        this.opcionais = opcionais;
    }

    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(BigDecimal valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Veiculo other = (Veiculo) obj;
        if (id != other.id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Teste [id=" + id + ", dataDeCadastro=" + dataDeCadastro + ", placa=" + placa + ", RENAVAM=" + renavam + ", modelo=" + modelo + ", opcionais=" + opcionais + ", valorDeVenda=" + valorDeVenda + "]";
    }

}
