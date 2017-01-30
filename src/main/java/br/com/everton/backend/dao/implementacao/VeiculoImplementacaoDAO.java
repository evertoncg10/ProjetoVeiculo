package br.com.everton.backend.dao.implementacao;

import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import br.com.everton.backend.beans.Veiculo;
import br.com.everton.backend.dao.InterfaceDAO;
import br.com.everton.backend.dao.conexao.Conexao;
import br.com.everton.backend.utils.Utils;

public class VeiculoImplementacaoDAO implements InterfaceDAO {

    @Override
    public List<Object> listar() throws SQLException {
        List<Object> listaVeiculos = new ArrayList<Object>();
        Veiculo veiculo = null;
        try (PreparedStatement pst = Conexao.getConnection().prepareStatement("select * from tb_veiculo")) {
            //Executar consulta e processar o resultado
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getLong("id"));

                    Timestamp timestamp = rs.getTimestamp("data_cadastro");
                    veiculo.setDataDeCadastro(Utils.formataData(timestamp.toLocalDateTime()));

                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setOpcionais(trataOpcionais(rs.getString("opcionais")));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setRenavam(rs.getInt("renavam"));
                    veiculo.setValorDeVenda(rs.getBigDecimal("valor_venda").setScale(2, RoundingMode.HALF_EVEN));
                    Object obj = (Object) veiculo;
                    listaVeiculos.add(obj);
                }
            }
        } finally {
            Conexao.fecharConexao();
        }
        return listaVeiculos;
    }

    @Override
    public Veiculo selecionar(long id) throws SQLException {
        Veiculo veiculo = null;
        try (PreparedStatement pst = Conexao.getConnection().prepareStatement("select * from tb_veiculo where id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getLong("id"));

                    Timestamp timestamp = rs.getTimestamp("data_cadastro");
                    veiculo.setDataDeCadastro(Utils.formataData(timestamp.toLocalDateTime()));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setOpcionais(trataOpcionais(rs.getString("opcionais")));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setRenavam(rs.getInt("renavam"));
                    veiculo.setValorDeVenda(rs.getBigDecimal("valor_venda").setScale(2, RoundingMode.HALF_EVEN));
                }
            }
        } finally {
            Conexao.fecharConexao();
        }
        return veiculo;
    }

    @Override
    public void inserir(Object obj) throws SQLException {
        try (PreparedStatement pst = Conexao.getConnection().prepareStatement(queryInserir())) {
            Veiculo veiculo = (Veiculo) obj;
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            pst.setTimestamp(1, timestamp); // data_cadastro  
            pst.setString(2, veiculo.getPlaca()); // placa   
            pst.setInt(3, veiculo.getRenavam()); // renavam     
            pst.setString(4, veiculo.getModelo()); // modelo
            pst.setString(5, trataOpcionais(veiculo.getOpcionais())); // opcionais   
            pst.setBigDecimal(6, veiculo.getValorDeVenda().setScale(2, RoundingMode.HALF_EVEN)); // valor_venda

            pst.execute();
        } finally {
            Conexao.fecharConexao();
        }
    }

    @Override
    public void alterar(Object obj) throws SQLException {

        try (PreparedStatement pst = Conexao.getConnection().prepareStatement(queryAtualizar())) {
            Veiculo veiculo = (Veiculo) obj;
            pst.setString(1, veiculo.getPlaca()); // placa   
            pst.setInt(2, veiculo.getRenavam()); // renavam     
            pst.setString(3, veiculo.getModelo()); // modelo  
            pst.setString(4, trataOpcionais(veiculo.getOpcionais())); // opcionais   
            pst.setBigDecimal(5, veiculo.getValorDeVenda().setScale(2, RoundingMode.HALF_EVEN)); // valor_venda
            pst.setLong(6, veiculo.getId()); //id

            pst.executeUpdate();
        } finally {
            Conexao.fecharConexao();
        }
    }

    @Override
    public void deletar(long id) throws SQLException {
        try (PreparedStatement pst = Conexao.getConnection().prepareStatement("delete from tb_veiculo where id = ?")) {
            pst.setLong(1, id);
            pst.execute();
        } finally {
            Conexao.fecharConexao();
        }

    }

    private String trataOpcionais(String opcionais) {
        String[] list;
        list = opcionais.split(Pattern.quote(" "));
        StringBuffer strBuffer = new StringBuffer();
        for (String string : list) {
            switch (string) {
                case "ArCondicionado":
                    strBuffer.append("Ar Condicionado;");
                    break;

                case "VidrosEletricos":
                    strBuffer.append("Vidros Elétricos;");
                    break;

                case "AirBag":
                    strBuffer.append("Air Bag;");
                    break;

                default:
                    break;
            }
        }
        return strBuffer.toString();
    }

    private String queryInserir() {
        StringBuffer strBf = new StringBuffer();

        strBf.append("insert into tb_veiculo ");
        strBf.append("(data_cadastro,placa,renavam,modelo,opcionais,valor_venda) ");
        strBf.append("values ");
        strBf.append("(?,?,?,?,?,?)");

        return strBf.toString();

    }

    private String queryAtualizar() {
        //update tb_cliente set nome = ? where id = ?
        StringBuffer strBf = new StringBuffer();

        strBf.append("update tb_veiculo set ");
        strBf.append("placa=?,");
        strBf.append("renavam=?,");
        strBf.append("modelo=?,");
        strBf.append("opcionais=?,");
        strBf.append("valor_venda=? ");
        strBf.append("where id =?");

        return strBf.toString();

    }

}
