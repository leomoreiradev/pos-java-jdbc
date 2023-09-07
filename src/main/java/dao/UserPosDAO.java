package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
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
	
	
	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.setString(2, telefone.getTipo());
			preparedStatement.setLong(3, telefone.getUsuario());
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				BeanUserFone beanUserFone = new BeanUserFone();
				beanUserFone.setEmail(resultSet.getString("email"));
				beanUserFone.setNome(resultSet.getString("nome"));
				beanUserFone.setNumero(resultSet.getString("numero"));
				
				beanUserFones.add(beanUserFone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return beanUserFones;
		
		
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
