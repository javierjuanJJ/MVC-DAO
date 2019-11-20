package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.Articulos;
import Modelo.Grupos;
import javafx.application.Platform;

public class ArticulosDAO implements GenericoDAO<Articulos> {
	
	private static final String sql_select_by_PK = "SELECT * FROM empresa_ad.articulos WHERE id=?;";
	private static final String sql_select_by_PK_grupos = "SELECT * FROM empresa_ad.grupos WHERE id=?;";
	private static final String sql_select_all = "SELECT * FROM empresa_ad.articulos;";
	private static final String sql_select_all_grupos = "SELECT * FROM empresa_ad.grupos;";
	private static final String sql_UPDATE = "UPDATE `empresa_ad`.`articulos` SET `nombre`=?, `precio`=?, `codigo`=?, `grupo`=? WHERE `id`=?;";
	private static final String sql_INSERT = "INSERT INTO `empresa_ad`.`articulos` (`nombre`, `precio`, `codigo`, `grupo`) VALUES (?, ?, ?, ?);";
	private static final String sql_DELETE = "DELETE FROM `empresa_ad`.`articulos` WHERE `id`=?;";
	private static final String sql_INSERT_GRUPO = "INSERT INTO `empresa_ad`.`grupos` (`descripcion`) VALUES (?);";
	public static PreparedStatement preparedstatement = null;
	
	public ArticulosDAO() {
		try {
			Conexion.getConnection();
			
		} catch (Exception e) {
			
			Platform.exit();
		}
	}
	
	public Articulos findByPK(int id) throws Exception {
		Articulos articulo_recibido = null;

		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_by_PK);
		preparedstatement.setInt(1, id);
		resultset = preparedstatement.executeQuery();
		resultset.first();

		articulo_recibido = new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
				resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo"));

		return articulo_recibido;
	}

	public Grupos findByPK_grupos(int id) throws Exception {
		Grupos articulo_recibido = null;
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection()
				.prepareStatement(sql_select_by_PK_grupos);
		preparedstatement.setInt(1, id);
		resultset = preparedstatement.executeQuery();
		resultset.first();
		articulo_recibido = new Grupos(resultset.getInt("id"), resultset.getString("descripcion"));

		return articulo_recibido;
	}

	public List<Articulos> findAll() throws Exception {
		List<Articulos> articulos_recibidos = null;
		articulos_recibidos = new ArrayList<Articulos>();
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all);
		resultset = preparedstatement.executeQuery();

		while (resultset.next()) {
			articulos_recibidos.add(new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
					resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo")));
		}

		return articulos_recibidos;
	}

	public List<Grupos> findAll_grupos() throws Exception {
		List<Grupos> grupos_recibidos = new ArrayList<Grupos>();

		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all_grupos);
		resultset = preparedstatement.executeQuery();

		while (resultset.next()) {
			grupos_recibidos.add(new Grupos(resultset.getInt("id"), resultset.getString("descripcion")));
		}

		return grupos_recibidos;
	}

	public List<Articulos> findBySQL(String sqlselect) throws Exception {
		return null;
	}

	public boolean insert(Articulos t) throws Exception {
		int salida = 0;
		boolean resultado = false;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setDouble(2, t.getPrecio());
		preparedstatement.setString(3, t.getCodigo());
		preparedstatement.setInt(4, t.getGrupo());
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;
	}


	public boolean update(Articulos t) throws Exception {
		int salida = 0;
		boolean resultado = false;

		preparedstatement = Conexion.getConnection().prepareStatement(sql_UPDATE);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setDouble(2, t.getPrecio());
		preparedstatement.setString(3, t.getCodigo());
		preparedstatement.setInt(4, t.getGrupo());
		preparedstatement.setInt(5, t.getId());

		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;

	}

	public boolean delete(int id) throws Exception {
		int salida = 0;
		boolean resultado = false;

		preparedstatement = Conexion.getConnection().prepareStatement(sql_DELETE);
		preparedstatement.setInt(1, id);
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}
		return resultado;

	}

	public boolean insert(Grupos t) throws Exception {

		int salida = 0;
		boolean resultado = false;

		preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT_GRUPO);
		preparedstatement.setString(1, t.getDescripcion());
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;
	}
	
}
