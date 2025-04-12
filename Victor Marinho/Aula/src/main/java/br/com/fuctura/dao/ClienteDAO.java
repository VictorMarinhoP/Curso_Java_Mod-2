package br.com.fuctura.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.entidade.ClienteEntidade;

public class ClienteDAO {

	// cadastrar um cliente

	public void inserir(Connection conn, ClienteEntidade cliente) throws SQLException {

		// inserir cliente
		String comandoSQLInserirCliente = "INSERT INTO cliente (nome, cpf) values (?, ?)";

		PreparedStatement pstm = conn.prepareStatement(comandoSQLInserirCliente, Statement.RETURN_GENERATED_KEYS);

		pstm.setString(1, cliente.getNome());
		pstm.setString(2, cliente.getCpf());

		int affectedRows = pstm.executeUpdate();

		Integer codigoCliente = null;

		if (affectedRows > 0) {
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				codigoCliente = rs.getInt(1);
			}
		}

	}

	// consultar todos os clientes
	public List<ClienteEntidade> consultarTodos(Connection conn) {

		try {

			String comandoSQL = "SELECT * FROM cliente";

			PreparedStatement pstm = conn.prepareStatement(comandoSQL);

			ResultSet resultadoConsulta = pstm.executeQuery();

			List<ClienteEntidade> clientes = new ArrayList<ClienteEntidade>();

			while (resultadoConsulta.next()) {

				int codigo = resultadoConsulta.getInt("codigo");
				String nome = resultadoConsulta.getString("nome");
				String cpf = resultadoConsulta.getString("cpf");

				ClienteEntidade c = new ClienteEntidade();
				c.setCodigo(codigo);
				c.setNome(nome);
				c.setCpf(cpf);

				clientes.add(c);

				return clientes;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void excluir(Connection conn, ClienteEntidade clienteEntidade) throws SQLException {
		// inserir cliente
		String comandoSQLExcluirCliente = "DELETE FROM cliente WHERE cpf ?";

		PreparedStatement pstm = conn.prepareStatement(comandoSQLExcluirCliente);

		pstm.setString(2, clienteEntidade.getCpf());

		pstm.execute();

	}

}
