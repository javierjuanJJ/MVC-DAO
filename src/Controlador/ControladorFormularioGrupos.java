package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Articulos;
import Modelo.Grupos;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControladorFormularioGrupos implements ArticuloDAO {

	private static Connection conexion = null;

	private static final String sql_select_by_PK = "SELECT * FROM empresa_ad.articulos WHERE id=?;";
	private static final String sql_select_all = "SELECT * FROM empresa_ad.articulos;";
	private static final String sql_select_all_grupos = "SELECT * FROM empresa_ad.grupos;";
	private static final String sql_UPDATE = "UPDATE `empresa_ad`.`articulos` SET `nombre`=?, `precio`=?, `codigo`=?, `grupo`=? WHERE `id`=?;";
	private static final String sql_INSERT = "INSERT INTO `empresa_ad`.`articulos` (`nombre`, `precio`, `codigo`, `grupo`) VALUES (?, ?, ?, ?);";
	private static final String sql_DELETE = "DELETE FROM `empresa_ad`.`articulos` WHERE `id`=?;";
	private static final String sql_INSERT_GRUPO = "INSERT INTO `empresa_ad`.`grupos` (`descripcion`) VALUES (?);";
	private static PreparedStatement preparedstatement = null;

	@FXML
	private TextField TextField_descripcion;

	@FXML
	public void initialize() {
		try {
			conexion = Conexion.getConnection();
		} catch (Exception e) {
			Platform.exit();
		}
	}

	public void Cambiar_Pantalla(ActionEvent action) throws IOException {
		String id_boton = "";
		id_boton = ((Button) action.getSource()).getId();
		Main main = new Main();
		main.Cambiar_Pantalla(id_boton);
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

	
	public void insertar_grupo() {
		Grupos grupo_seleccionado = new Grupos(0,this.TextField_descripcion.getText());
				
		try {
			insert(grupo_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Articulos findByPK(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Articulos> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Articulos> findBySQL(String sqlselect) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Articulos t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Articulos t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
