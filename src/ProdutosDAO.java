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

        return listagem;
    }

}
