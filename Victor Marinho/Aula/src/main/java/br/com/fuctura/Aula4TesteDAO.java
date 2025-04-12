package br.com.fuctura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.dao.ClienteDAO;
import br.com.fuctura.entidade.ClienteEntidade;

public class Aula4TesteDAO {

	public static void main(String[] args) {

		try {

			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "123456";

			Connection conn = DriverManager.getConnection(url, user, password);

			ClienteDAO clienteDAO = new ClienteDAO();

			ClienteEntidade entidade = new ClienteEntidade();
			entidade.setCpf("123456789");
			entidade.setNome("Victor");

			clienteDAO.inserir(conn, entidade);

			List<ClienteEntidade> Lista = clienteDAO.consultarTodos(conn);

			for (ClienteEntidade e : Lista) {
				System.out.println("Codigo: " + e.getCodigo());
				System.out.println("CPF: " + e.getCpf());
				System.out.println("NOME: " + e.getNome());
			}

			System.out.println("Registro inserido com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
