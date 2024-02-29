/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            var conexao = new conectaDAO();
            conn = conexao.connectDB();

            String sql = "INSERT INTO produtos (nome, valor) VALUES (?,?);";
            PreparedStatement consulta = conn.prepareStatement(sql);
            consulta.setString(1, produto.getNome());
            consulta.setInt(2, produto.getValor());

            consulta.execute();

            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar regitro no banco de dados");

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {
            var conexao = new conectaDAO();
            conn = conexao.connectDB();

            String sql = "SELECT * FROM produtos";

            PreparedStatement consulta = conn.prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                lista.add(p);
            }

            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao listar os regitros do banco de dados");
            System.out.println(e);

        }

        return lista;
    }

    public void venderProduto(int id) {

        try {
            var conexao = new conectaDAO();
            conn = conexao.connectDB();

            String sql = "UPDATE produtos SET status = ? WHERE id=?;";
            PreparedStatement consulta = conn.prepareStatement(sql);

            consulta.setString(1, "Vendido");
            consulta.setInt(2, id);

            consulta.execute();

            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao tentar vender o produto selecionado");

        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {
            var conexao = new conectaDAO();
            conn = conexao.connectDB();

            String sql = "SELECT * FROM produtos WHERE status= 'Vendido';";

            PreparedStatement consulta = conn.prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                lista.add(p);
            }

            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao listar os regitros do banco de dados");
            System.out.println(e);

        }
        return lista;
    }
}
