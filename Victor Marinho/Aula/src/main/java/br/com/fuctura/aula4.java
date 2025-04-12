package br.com.fuctura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aula4 {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "123456";

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String comandoSQL = "SELECT * FROM cliente";

			PreparedStatement pstm = conn.prepareStatement(comandoSQL);

			ResultSet resultadoConsulta = pstm.executeQuery();

			while (resultadoConsulta.next()) {

				int codigo = resultadoConsulta.getInt(1);
				String nome = resultadoConsulta.getString(2);
				String cpf = resultadoConsulta.getString(3);

				System.out.println("Codigo: " + codigo);
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);

				System.out.println("Encontrou dados!");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
