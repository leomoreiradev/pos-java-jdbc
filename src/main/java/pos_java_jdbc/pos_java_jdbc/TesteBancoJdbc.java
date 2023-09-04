package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		userposjava.setId(6L);
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
}
