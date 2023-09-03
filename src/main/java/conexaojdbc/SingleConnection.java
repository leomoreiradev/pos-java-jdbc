package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "root";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");//indicando qual o driver do banco (postgres, mysql e etc) nesse casso pe postgres
				connection = DriverManager.getConnection(url, user, password);//passando as configs do banco para o DriverManager
				connection.setAutoCommit(false);//com esse metodo em false, na hora de executar script precisa confirmar de forma explicita com o commit()
				System.out.println("Conectou com sucesso");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
