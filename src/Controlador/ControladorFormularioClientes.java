package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Articulos;
import Modelo.Clientes;
import Modelo.Grupos;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControladorFormularioClientes implements ClienteDAO {
	private static Connection conexion = null;
	private static final String sql_select_by_PK = "SELECT * FROM empresa_ad.clientes WHERE id=?;";
	private static final String sql_select_all = "SELECT * FROM empresa_ad.clientes;";
	private static final String sql_UPDATE = "UPDATE `empresa_ad`.`clientes` SET `nombre`=?, `direccion`=? WHERE `id`=?;";
	private static final String sql_INSERT = "INSERT INTO `empresa_ad`.`clientes` (`nombre`, `direccion`) VALUES (?, ?);";
	private static final String sql_DELETE = "DELETE FROM `empresa_ad`.`clientes` WHERE `id`=?;";
	private static PreparedStatement preparedstatement = null;
	
	@FXML
	private Button boton_anyadir_atras_clientes;
	@FXML
	private Button boton_actualizar_clientes;
	@FXML
	private Button boton_eliminar_clientes;
	@FXML
	private Button boton_aceptar_clientes;
	@FXML
	private Button boton_atras_clientes;
	@FXML
	private Button boton_buscar_por_id_clientes;
	
	@FXML
	private ComboBox<Clientes> ComboBox_id_clientes;
	@FXML
	private TextField TextField_Nombre_clientes;
	@FXML
	private TextField TextField_direccion;
	@FXML
	private TextField TextField_buscar_por_id_clientes;

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
		List<Clientes> clientes_recibidos = new ArrayList<Clientes>();
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
		// TODO Ap�ndice de m�todo generado autom�ticamente
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
	
	public void actualizar_informacion_clientes() {
		try {
			ComboBox_id_clientes.getItems().clear();
			List<Clientes> clientes_recibidos = findAll();

			for (int contador = 0; contador < clientes_recibidos.size(); contador++) {
				ComboBox_id_clientes.getItems().add(clientes_recibidos.get(contador));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void poner_informacion_clientes() {
		Clientes cliente_seleccionado = null;
		cliente_seleccionado = ComboBox_id_clientes.getSelectionModel().getSelectedItem();
		
		try {
			poner_informacion(cliente_seleccionado);
		}
		catch(NullPointerException e) {
			
		}
		
	}

	public void insertar_informacion_clientes() {
		Clientes cliente_seleccionado = coger_informacion();
		
		try {
			insert(cliente_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_informacion() {
		Clientes cliente_seleccionado = coger_informacion();
		
		try {
			update(cliente_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminar_informacion() {
		Clientes cliente_seleccionado = coger_informacion();

		try {
			delete(cliente_seleccionado.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Buscar_por_id() {
		Clientes cliente_seleccionado = null;
		try {
			cliente_seleccionado = findByPK(Integer.parseInt(TextField_buscar_por_id_clientes.getText()));
		
			try {
				ComboBox_id_clientes.getItems().clear();
				ComboBox_id_clientes.getItems().add(findByPK(cliente_seleccionado.getId()));
				ComboBox_id_clientes.getSelectionModel().select(0);
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		
		} catch (NumberFormatException e) {
			cliente_seleccionado=new Clientes();
		} catch (Exception e) {
			cliente_seleccionado=new Clientes();
		}
		poner_informacion(cliente_seleccionado);
	}

	public void poner_informacion(Clientes cliente) {
		ComboBox_id_clientes.setPromptText(cliente.getId() + "");
		TextField_Nombre_clientes.setText(cliente.getNombre());
		TextField_direccion.setText(cliente.getDireccion());
	}

	public Clientes coger_informacion() {
		int id = 0;
		if (!ComboBox_id_clientes.getPromptText().isEmpty()) {
			id = Integer.parseInt(ComboBox_id_clientes.getPromptText());
		}

		Clientes cliente = new Clientes(id, TextField_Nombre_clientes.getText(),
				TextField_direccion.getText());
		return cliente;
	}

	/*
	 * public void botones() { if
	 * (TextField_buscar_por_id_articulos.getPromptText().isEmpty()) {
	 * boton_anyadir_atras_articulos.setDisable(false);
	 * boton_actualizar_articulos.setDisable(true);
	 * boton_eliminar_articulos.setDisable(true);
	 * boton_aceptar_articulos.setDisable(true);
	 * boton_atras_articulos.setDisable(false);
	 * boton_buscar_por_id_articulos.setDisable(true); } else {
	 * boton_anyadir_atras_articulos.setDisable(true);
	 * boton_actualizar_articulos.setDisable(false);
	 * boton_eliminar_articulos.setDisable(false);
	 * boton_aceptar_articulos.setDisable(false);
	 * boton_atras_articulos.setDisable(true);
	 * boton_buscar_por_id_articulos.setDisable(false); } }
	 */


}
