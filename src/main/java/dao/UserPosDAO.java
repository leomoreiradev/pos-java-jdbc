package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar (Userposjava userposjava) {
		String sql = "insert into userposjava(nome, email) values(?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setString(1, userposjava.getNome());
			preparedStatement.setString(2, userposjava.getEmail());
			preparedStatement.execute();
			connection.commit(); //confirma explicitamente a execuçao do script
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Userposjava> listar() throws SQLException{
		List<Userposjava> list = new ArrayList<Userposjava>();
		String sql = "select * from userposjava";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
			list.add(userposjava);
		}
		
		return list;
			
	}
	
	public Userposjava buscar(Long id) throws SQLException{
		Userposjava userposjava = new Userposjava();
		
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
		}
		
		return userposjava;
			
	}
	
	public void atualizar(Userposjava userposjava) {
		
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userposjava.getNome());
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		

	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
