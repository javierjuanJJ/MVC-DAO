package Controlador;

import java.io.IOException;
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

public class ControladorFormularioArticulos {
	private static ArticulosDAO controladorarticulos;
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
	private Button siguiente_clientes;
	@FXML
	private Button anterior_clientes;
	@FXML
	private Button ultimo_clientes;
	@FXML
	private Button primero_clientes;
	
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
			Conexion.getConnection();
			controladorarticulos=new ArticulosDAO();
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

	

	public void actualizar_informacion_clientes() {

		try {
			ComboBox_id_articulos.getItems().clear();
			List<Articulos> articulos_recibidos = controladorarticulos.findAll();

			for (int contador = 0; contador < articulos_recibidos.size(); contador++) {
				ComboBox_id_articulos.getItems().add(articulos_recibidos.get(contador));
			}

		} catch (Exception e) {

		}
	}

	public void actualizar_informacion_grupos() {

		try {
			ComboBox_grupos_articulos.getItems().clear();
			List<Grupos> articulos_recibidos = controladorarticulos.findAll_grupos();

			for (int contador = 0; contador < articulos_recibidos.size(); contador++) {
				ComboBox_grupos_articulos.getItems().add(articulos_recibidos.get(contador));
			}

		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void poner_informacion_clientes() {
		Articulos articulo_seleccionado = null;
		try {
			articulo_seleccionado = ComboBox_id_articulos.getSelectionModel().getSelectedItem();
			poner_informacion(articulo_seleccionado);
		}
		catch (Exception e) {
			articulo_seleccionado =new Articulos();
			poner_informacion(articulo_seleccionado);
		}
		
		
		
	}

	public void insertar_informacion_clientes() {

		Articulos articulo_seleccionado = null;

		try {
			articulo_seleccionado = coger_informacion();
			articulo_seleccionado.setCodigo(
					articulo_seleccionado.getNombre().substring(
							0, articulo_seleccionado.getNombre().length() / 2));
			if ((!(controladorarticulos.insert(articulo_seleccionado)))) {
				throw new Exception("Error en la insercion de datos del formulario");
			} else {
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

			if ((!(controladorarticulos.update(articulo_seleccionado)))) {
				throw new Exception("Error en la actualizacion de datos del formulario");
			} else {
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
			if ((!(controladorarticulos.delete(articulo_seleccionado.getId())))) {
				throw new Exception("Error en el borrado de datos del formulario");
			} else {
				mensajeConfirmacion("Eliminacion completada", "La operacion ha sido un exito");
			}

		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void Buscar_por_id() {

		Articulos articulo_seleccionado = null;
		try {
			articulo_seleccionado = controladorarticulos.findByPK(Integer.parseInt(TextField_buscar_por_id_articulos.getText()));

			ComboBox_id_articulos.getItems().clear();
			ComboBox_id_articulos.getItems().add(controladorarticulos.findByPK(articulo_seleccionado.getId()));
			ComboBox_id_articulos.getSelectionModel().select(0);

		} catch (Exception e) {
			poner_informacion(new Articulos());
		}
		poner_informacion(articulo_seleccionado);
	}

	public void poner_informacion(Articulos articulo) {

		try {
			TextField_Nombre_articulos.setText(articulo.getNombre());
			ComboBox_grupos_articulos.getItems().clear();
			ComboBox_grupos_articulos.getItems().add(controladorarticulos.findByPK_grupos(articulo.getGrupo()));
			ComboBox_grupos_articulos.getSelectionModel().select(0);
			TextField_precio_articulos.setText(articulo.getPrecio() + "");
			ComboBox_codigo_articulos.setPromptText(articulo.getCodigo());
		} catch (Exception e) {

		}

	}

	public Articulos coger_informacion() {

		Articulos articulo = null;
		int id = 0;
		try {
			
			if (ComboBox_id_articulos.getSelectionModel().getSelectedItem() != null) {
				articulo = new Articulos(ComboBox_id_articulos.getSelectionModel().getSelectedItem());
				id = articulo.getId();
			}
			else {
				articulo = new Articulos();
			}
						
			articulo.setNombre(TextField_Nombre_articulos.getText());
			articulo.setPrecio(Double.parseDouble(TextField_precio_articulos.getText()));
			articulo.setCodigo(ComboBox_codigo_articulos.getPromptText());
			articulo.setId(id);
			articulo.setGrupo(cogergrupo().getId());
			
		} catch (Exception e) {
			articulo = null;
		}

		return articulo;
	}

	public Grupos cogergrupo() {
		Grupos grupo_seleccionado = new Grupos();
		try {
			grupo_seleccionado = controladorarticulos.findAll_grupos().get(ComboBox_grupos_articulos.getSelectionModel().getSelectedIndex());
		}
		catch (Exception e) {
			
		}
		
		
		return grupo_seleccionado;
	}
	
	public void cambiar_de_extremos(ActionEvent action) {
		ultimo_clientes.setDisable(false);
		siguiente_clientes.setDisable(false);
		anterior_clientes.setDisable(false);
		primero_clientes.setDisable(false);
		
		String id_boton = "";
		id_boton = ((Button) action.getSource()).getId();
		Articulos cliente = null;
		List<Articulos> clientes = null;
		int posicion = 0;
		
		try {
			clientes=controladorarticulos.findAll();
			posicion=ComboBox_id_articulos.getSelectionModel().getSelectedIndex();
			
			
			
			switch (id_boton) {
			
			case "Siguiente":
				posicion++;
				break;
			case "Ultimo":
				posicion=clientes.size()-1;
				break;
			case "Anterior":
				posicion--;
				break;
			case "Primero":
				posicion=0;
				break;
			}
			
			
			if( (posicion == clientes.size()-1) || (posicion == 0) ){
				if (posicion == clientes.size()-1) {
					ultimo_clientes.setDisable(true);
					siguiente_clientes.setDisable(true);
				}
				else {
					anterior_clientes.setDisable(true);
					primero_clientes.setDisable(true);
				}
			}
			
			cliente = clientes.get(posicion);
			ComboBox_id_articulos.getSelectionModel().select(posicion);
			poner_informacion(cliente);
			
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
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

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
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
		String exceptionText = "";
		exceptionText = "La orden SQL ha sido " + System.lineSeparator()
				+ ArticulosDAO.preparedstatement.toString().substring("com.mysql.cj.jdbc.ClientPreparedStatement: ".length());
		Label label = new Label("La operacion ha sido un exito");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

	
}
