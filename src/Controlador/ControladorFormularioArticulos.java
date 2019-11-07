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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ControladorFormularioArticulos implements ArticuloDAO {

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
	private Button boton_anyadir_atras_articulos;
	@FXML
	private Button boton_actualizar_articulos;
	@FXML
	private Button boton_eliminar_articulos;
	@FXML
	private Button boton_aceptar_articulos;
	@FXML
	private Button boton_atras_articulos;
	@FXML
	private Button boton_buscar_por_id_articulos;

	@FXML
	private ComboBox<Articulos> ComboBox_id_articulos;
	@FXML
	private TextField TextField_Nombre_articulos;
	@FXML
	private TextField TextField_descripcion;
	@FXML
	private TextField TextField_direccion_articulos;
	@FXML
	private ComboBox<Grupos> ComboBox_grupos_articulos;
	@FXML
	private TextField TextField_precio_articulos;
	@FXML
	private ComboBox<Articulos> ComboBox_codigo_articulos;
	@FXML
	private TextField TextField_buscar_por_id_articulos;

	@FXML
	public void initialize() {

		try {
			conexion = Conexion.getConnection();
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			Platform.exit();
		}

	}

	public void Cambiar_Pantalla(ActionEvent action) throws IOException {
		String id_boton = "";
		id_boton = ((Button) action.getSource()).getId();
		Main main = new Main();
		main.Cambiar_Pantalla(id_boton);
	}

	public Articulos findByPK(int id) throws Exception {
		Articulos articulo_recibido = null;
		
		try {
			
			
			ResultSet resultset = null;
			preparedstatement = Conexion.getConnection().prepareStatement(sql_select_by_PK);
			preparedstatement.setInt(1, id);
			resultset = preparedstatement.executeQuery();
			resultset.first();
			
			articulo_recibido = new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
					resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo"));
		}
		catch (SQLException e) {
			poner_informacion(new Articulos());
			mensajeExcepcion(e, e.getMessage());
		}
		catch (NullPointerException e) {
			poner_informacion(new Articulos());
		}
		
		
		return articulo_recibido;
	}
	
	public Grupos findByPK_grupos(int id) throws Exception {
		Grupos articulo_recibido = null;
		try {
			ResultSet resultset = null;
			preparedstatement = Conexion.getConnection().prepareStatement("SELECT * FROM empresa_ad.grupos WHERE id=?;");
			preparedstatement.setInt(1, id);
			resultset = preparedstatement.executeQuery();
			resultset.first();
			articulo_recibido = new Grupos(resultset.getInt("id"), resultset.getString("descripcion"));
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			articulo_recibido=new Grupos();
		}

		return articulo_recibido;
	}

	public List<Articulos> findAll() throws Exception {
		List<Articulos> articulos_recibidos = null;
		try {
			articulos_recibidos = new ArrayList<Articulos>();
			ResultSet resultset = null;
			preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all);
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				articulos_recibidos.add(new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
						resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo")));
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
		
		return articulos_recibidos;
	}

	public List<Grupos> findAll_grupos() throws Exception {
		List<Grupos> grupos_recibidos = new ArrayList<Grupos>();
		
		try {
			ResultSet resultset = null;
			preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all_grupos);
			resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				grupos_recibidos.add(new Grupos(resultset.getInt("id"), resultset.getString("descripcion")));
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
		
		return grupos_recibidos;
	}

	public List<Articulos> findBySQL(String sqlselect) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}

	public boolean insert(Articulos t) throws Exception {
		int salida = 0;
		Grupos grupo=cogergrupo();
		boolean resultado = false;
		try {
			preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT);
			preparedstatement.setString(1, t.getNombre());
			preparedstatement.setDouble(2, t.getPrecio());
			preparedstatement.setString(3, t.getCodigo());
			preparedstatement.setInt(4, grupo.getId());
			salida = preparedstatement.executeUpdate();

			if (salida > 0) {
				resultado = true;
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			resultado = false;
		}
		
		return resultado;
	}

	public boolean update(Articulos t) throws Exception {
		int salida = 0;
		boolean resultado = false;
		
		try {
			preparedstatement = Conexion.getConnection().prepareStatement(sql_UPDATE);
			preparedstatement.setString(1, t.getNombre());
			preparedstatement.setDouble(2, t.getPrecio());
			preparedstatement.setString(3, t.getCodigo());
			preparedstatement.setInt(4, cogergrupo().getId());
			preparedstatement.setInt(5, this.coger_informacion().getId());

			salida = preparedstatement.executeUpdate();

			if (salida > 0) {
				resultado = true;
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			resultado = false;
		}

		return resultado;

	}

	public boolean delete(int id) throws Exception {
		int salida = 0;
		boolean resultado = false;
		
		try {
			preparedstatement = Conexion.getConnection().prepareStatement(sql_DELETE);
			preparedstatement.setInt(1, id);
			salida = preparedstatement.executeUpdate();

			if (salida > 0) {
				resultado = true;
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			resultado = false;
		}
		
		return resultado;

	}

	public boolean insert(Grupos t) throws Exception {

		int salida = 0;
		boolean resultado = false;
		
		try {
			preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT_GRUPO);
			preparedstatement.setString(1, t.getDescripcion());
			salida = preparedstatement.executeUpdate();

			if (salida > 0) {
				resultado = true;
			}
		}
		catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
			resultado = false;
		}
		
		return resultado;
	}

	public void actualizar_informacion_clientes() {

		try {
			ComboBox_id_articulos.getItems().clear();
			List<Articulos> articulos_recibidos = findAll();

			for (int contador = 0; contador < articulos_recibidos.size(); contador++) {
				ComboBox_id_articulos.getItems().add(articulos_recibidos.get(contador));
			}

		} catch (Exception e) {
			
		}
	}

	public void actualizar_informacion_grupos() {

		try {
			ComboBox_grupos_articulos.getItems().clear();
			List<Grupos> articulos_recibidos = findAll_grupos();

			for (int contador = 0; contador < articulos_recibidos.size(); contador++) {
				ComboBox_grupos_articulos.getItems().add(articulos_recibidos.get(contador));
			}

		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void poner_informacion_clientes() {
		Articulos articulo_seleccionado = null;
		articulo_seleccionado = ComboBox_id_articulos.getSelectionModel().getSelectedItem();
		poner_informacion(articulo_seleccionado);
	}

	public void insertar_informacion_clientes() {

		Articulos articulo_seleccionado = null;
		
		try {
			articulo_seleccionado = coger_informacion();
			articulo_seleccionado.setCodigo(articulo_seleccionado.getNombre().substring(0, articulo_seleccionado.getNombre().length()/2));
			if( (!(insert(articulo_seleccionado)))||(!ComboBox_id_articulos.getPromptText().isEmpty()) ){
				throw new Exception("Error en la insercion de datos del formulario");
			}
			else {
				mensajeConfirmacion("Insercion completada", "La operacion ha sido un exito");
			}
			
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void actualizar_informacion() {

		Articulos articulo_seleccionado = null;
		
		try {
			articulo_seleccionado = coger_informacion();
			
			if( (!(update(articulo_seleccionado)))){
				throw new Exception("Error en la actualizacion de datos del formulario");
			}
			else {
				mensajeConfirmacion("Actualizacion completada", "La operacion ha sido un exito");
			}
			
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void eliminar_informacion() {

		Articulos articulo_seleccionado = null;

		try {
			articulo_seleccionado = coger_informacion();
			if( (!(delete(articulo_seleccionado.getId()))) ){
				throw new Exception("Error en el borrado de datos del formulario");
			}
			else {
				mensajeConfirmacion("Eliminacion completada", "La operacion ha sido un exito");
			}
			
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void Buscar_por_id() {

		Articulos articulo_seleccionado = null;
		try {
			articulo_seleccionado = findByPK(Integer.parseInt(TextField_buscar_por_id_articulos.getText()));
		
			try {
				ComboBox_id_articulos.getItems().clear();
				ComboBox_id_articulos.getItems().add(findByPK(articulo_seleccionado.getId()));
				ComboBox_id_articulos.getSelectionModel().select(0);
			} catch (Exception e) {
				poner_informacion(new Articulos());
				mensajeExcepcion(e, e.getMessage());
			}
		
		
		} catch (Exception e) {
			poner_informacion(new Articulos());
		}
		poner_informacion(articulo_seleccionado);
	}

	public void poner_informacion(Articulos articulo) {

		try {
			TextField_Nombre_articulos.setText(articulo.getNombre());
			ComboBox_grupos_articulos.getItems().clear();
			ComboBox_grupos_articulos.getItems().add(findByPK_grupos(articulo.getGrupo()));
			ComboBox_grupos_articulos.getSelectionModel().select(0);
			TextField_precio_articulos.setText(articulo.getPrecio() + "");
			ComboBox_codigo_articulos.setPromptText(articulo.getCodigo());
		} 	
		catch (Exception e) {
			poner_informacion(new Articulos());
		}
		
	}

	public Articulos coger_informacion() {
		
		Articulos articulo =new Articulos();
		try {
			articulo.setId(ComboBox_id_articulos.getSelectionModel().getSelectedItem().getId());
		}
		catch(Exception e) {
			articulo.setId(0);
		}
		articulo.setNombre(TextField_Nombre_articulos.getText());
		articulo.setPrecio(Double.parseDouble(TextField_precio_articulos.getText()));
		articulo.setCodigo(ComboBox_codigo_articulos.getPromptText());
		
		return articulo;
	}

	public Grupos cogergrupo() {
		Grupos grupo_seleccionado = ComboBox_grupos_articulos.getSelectionModel().getSelectedItem();
		return grupo_seleccionado;
	}

	public void botones() {
		if (TextField_buscar_por_id_articulos.getPromptText().isEmpty()) {
			boton_anyadir_atras_articulos.setDisable(false);
			boton_actualizar_articulos.setDisable(true);
			boton_eliminar_articulos.setDisable(true);
			boton_aceptar_articulos.setDisable(true);
			boton_atras_articulos.setDisable(false);
			boton_buscar_por_id_articulos.setDisable(true);
		} else {
			boton_anyadir_atras_articulos.setDisable(true);
			boton_actualizar_articulos.setDisable(false);
			boton_eliminar_articulos.setDisable(false);
			boton_aceptar_articulos.setDisable(false);
			boton_atras_articulos.setDisable(true);
			boton_buscar_por_id_articulos.setDisable(false);
		}
	}
	
	private void mensajeExcepcion(Exception ex, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error de excepción");
		alert.setHeaderText(msg);
		alert.setContentText(ex.getMessage());

		String exceptionText = "";
		StackTraceElement[] stackTrace = ex.getStackTrace();
		for (StackTraceElement ste : stackTrace) {
			exceptionText = exceptionText + ste.toString() + System.getProperty("line.separator");
		}

		Label label = new Label("La traza de la excepción ha sido: ");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(800);
		textArea.setMaxHeight(800);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(800);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
	
	private void mensajeConfirmacion(String Titulo, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Exito de la operacion");
		alert.setHeaderText(msg);

		String exceptionText = "La orden SQL ha sido " + System.lineSeparator() + preparedstatement.toString();
		
		Label label = new Label("La operacion ha sido un exito");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(800);
		textArea.setMaxHeight(800);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(800);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

}
