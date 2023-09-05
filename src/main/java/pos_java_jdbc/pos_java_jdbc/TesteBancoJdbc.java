package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initSalvar() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		
		userposjava.setNome("tim");
		userposjava.setEmail("tim@gmail.com");
		
		
		userPosDAO.salvar(userposjava);
	}
	
	@Test
	public void initLista() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> lista = dao.listar();
			for (Userposjava userposjava : lista) {
				System.out.println(userposjava);
				System.out.println("-------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
	
		UserPosDAO dao = new UserPosDAO();
		
		try {
			Userposjava userposjava = dao.buscar(5L);
			System.out.println(userposjava);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initAtualizar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			Userposjava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("nome mudado com o metodo atualizar");
			dao.atualizar(objetoBanco);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(5L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
