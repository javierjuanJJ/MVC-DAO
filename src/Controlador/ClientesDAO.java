package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.Clientes;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ClientesDAO implements ClienteDAO {

	private static Connection conexion = null;
	protected static final String sql_select_by_PK = "SELECT * FROM empresa_ad.clientes WHERE id=?;";
	protected static final String sql_select_all = "SELECT * FROM empresa_ad.clientes;";
	protected static final String sql_UPDATE = "UPDATE `empresa_ad`.`clientes` SET `nombre`=?, `direccion`=? WHERE `id`=?;";
	protected static final String sql_INSERT = "INSERT INTO `empresa_ad`.`clientes` (`nombre`, `direccion`) VALUES (?, ?);";
	protected static final String sql_DELETE = "DELETE FROM `empresa_ad`.`clientes` WHERE `id`=?;";
	protected static PreparedStatement preparedstatement = null;

	public Clientes findByPK(int id) throws Exception {

		Clientes cliente_recibido = null;
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_by_PK);
		preparedstatement.setInt(1, id);
		resultset = preparedstatement.executeQuery();
		resultset.first();

		cliente_recibido = new Clientes(resultset.getInt("id"), resultset.getString("nombre"),
				resultset.getString("direccion"));

		return cliente_recibido;
	}

	public List<Clientes> findAll() throws Exception {
		List<Clientes> clientes_recibidos = null;
		clientes_recibidos = new ArrayList<Clientes>();
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all);
		resultset = preparedstatement.executeQuery();

		while (resultset.next()) {
			clientes_recibidos.add(new Clientes(resultset.getInt("id"), resultset.getString("nombre"),
					resultset.getString("direccion")));
		}
		return clientes_recibidos;
	}

	public List<Clientes> findBySQL(String sqlselect) throws Exception {
		return null;
	}

	public boolean insert(Clientes t) throws Exception {

		int salida = 0;
		boolean resultado = false;

		preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setString(2, t.getDireccion());
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}
		return resultado;
	}

	public boolean update(Clientes t) throws Exception {

		int salida = 0;
		boolean resultado = false;

		preparedstatement = Conexion.getConnection().prepareStatement(sql_UPDATE);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setString(2, t.getDireccion());
		preparedstatement.setInt(3, t.getId());

		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;

	}

	public boolean delete(int id) throws Exception {
		boolean resultado = false;

		int salida = 0;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_DELETE);
		preparedstatement.setInt(1, id);
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;

	}

}
