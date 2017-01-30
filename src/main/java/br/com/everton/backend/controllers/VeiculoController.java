package br.com.everton.backend.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.everton.backend.beans.Veiculo;
import br.com.everton.backend.dao.implementacao.VeiculoImplementacaoDAO;

@Path("veiculos")
public class VeiculoController {

    VeiculoImplementacaoDAO veiculoDao = new VeiculoImplementacaoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Veiculo> listVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            List<Object> lista = veiculoDao.listar();

            for (Object object : lista) {
                Veiculo veiculo = (Veiculo) object;
                veiculos.add(veiculo);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar os veículos");
            ex.printStackTrace();
        }

        return veiculos;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Veiculo getVeiculo(@PathParam("id") long id) {
        Veiculo veiculo = null;
        try {
            veiculo = veiculoDao.selecionar(id);
        } catch (Exception e) {
            System.out.println("Erro ao selecionar o veículo");
            e.printStackTrace();
        }
        return veiculo;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Veiculo veiculo) {
        try {
            veiculoDao.inserir(veiculo);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o veículo");
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Veiculo veiculo) {
        try {
            veiculoDao.alterar(veiculo);
        } catch (SQLException e) {
            System.out.println("Erro ao alterar o veículo");
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            veiculoDao.deletar(id);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente");
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).build();
    }
}
