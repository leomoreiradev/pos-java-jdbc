package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar (Userposjava userposjava) {
		String sql = "insert into userposjava(id, nome, email) values(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, userposjava.getId());
			preparedStatement.setString(2, userposjava.getNome());
			preparedStatement.setString(3, userposjava.getEmail());
			preparedStatement.execute();
			connection.commit(); //confirma explicitamente a execuçao do script
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
