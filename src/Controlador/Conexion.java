package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class Conexion {
	private static final String url = "jdbc:mysql://192.168.1.3:3306/empresa_ad?serverTimezone=" + TimeZone.getDefault().getID();
	private static Connection conexion = null;
	static final String kusuario = "jj";
	static final String kcontrasenya = "j9Xuunp4g9OdSSDq";

	public static Connection getConnection() throws SQLException,Exception {

		if (conexion == null) {
			
			Properties pc = new Properties();
			pc.put("passwd", kcontrasenya);
			pc.put("user", kusuario);

			conexion = DriverManager.getConnection(url, pc.getProperty("user"), pc.getProperty("passwd"));

		}

		return conexion;

	}

	public static void cerrar() {

		if (conexion != null) {
			try {
				conexion.close();

			} catch (SQLException ex) {
				System.err.println("Error " + ex.getMessage());
			} catch (Exception ex) {
				System.err.println("Error " + ex.getMessage());
			}
		}

	}

}
