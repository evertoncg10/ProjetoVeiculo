package br.com.everton.backend.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.everton.backend.beans.Veiculo;

public interface InterfaceDAO {

    public List<Object> listar() throws SQLException;

    public Veiculo selecionar(long id) throws SQLException;

    public void inserir(Object objeto) throws SQLException;

    public void alterar(Object objeto) throws SQLException;

    public void deletar(long id) throws SQLException;

}
