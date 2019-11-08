package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.Articulos;
import Modelo.Clientes;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

public class ControladorFormularioClientes {
	
	private static ClientesDAO controladorclientes=new ClientesDAO();
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
	private Button siguiente_clientes;
	@FXML
	private Button anterior_clientes;
	@FXML
	private Button ultimo_clientes;
	@FXML
	private Button primero_clientes;

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
			Conexion.getConnection();
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

	

	public void actualizar_informacion_clientes() {

		try {
			ComboBox_id_clientes.getItems().clear();
			List<Clientes> clientes_recibidos = controladorclientes.findAll();

			for (int contador = 0; contador < clientes_recibidos.size(); contador++) {
				ComboBox_id_clientes.getItems().add(clientes_recibidos.get(contador));
			}
		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}

	}

	public void poner_informacion_clientes() {
		Clientes cliente_seleccionado = null;
		cliente_seleccionado = ComboBox_id_clientes.getSelectionModel().getSelectedItem();

		try {
			poner_informacion(cliente_seleccionado);
		} catch (NullPointerException e) {

		}
	}

	public void insertar_informacion_clientes() {

		Clientes cliente_seleccionado = null;

		try {
			cliente_seleccionado = coger_informacion();
			if ((!(controladorclientes.insert(cliente_seleccionado)))) {

				throw new Exception("Error en la insercion de datos del formulario");

			} else {
				mensajeConfirmacion("Insercion completada", "La operacion ha sido un exito");
			}
		} catch (Exception e) {
			ComboBox_id_clientes.getItems().clear();
			ComboBox_id_clientes.getSelectionModel().selectFirst();
			ComboBox_id_clientes.setPromptText("");
			mensajeExcepcion(e, e.getMessage());
		}

	}

	public void actualizar_informacion() {
		Clientes cliente_seleccionado = coger_informacion();

		try {

			if (!(controladorclientes.update(cliente_seleccionado))) {
				throw new Exception("Error en la actualizacion de datos del formulario");
			} else {
				mensajeConfirmacion("Actualizacion completada", "La operacion ha sido un exito");
			}

		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void eliminar_informacion() {
		Clientes cliente_seleccionado = coger_informacion();

		try {

			if (!(controladorclientes.delete(cliente_seleccionado.getId()))) {
				throw new Exception("Error en el borrado de datos del formulario");
			} else {
				mensajeConfirmacion("Eliminacion completada", "La operacion ha sido un exito");
			}

		} catch (Exception e) {
			mensajeExcepcion(e, e.getMessage());
		}
	}

	public void Buscar_por_id() {
		Clientes cliente_seleccionado = null;
		try {
			cliente_seleccionado = controladorclientes.findByPK(Integer.parseInt(TextField_buscar_por_id_clientes.getText()));

			ComboBox_id_clientes.getItems().clear();
			ComboBox_id_clientes.getItems().add(controladorclientes.findByPK(cliente_seleccionado.getId()));
			ComboBox_id_clientes.getSelectionModel().select(0);

		} catch (NumberFormatException e) {
			cliente_seleccionado = new Clientes();
			mensajeExcepcion(e, e.getMessage());
		} catch (Exception e) {
			cliente_seleccionado = new Clientes();
			mensajeExcepcion(e, e.getMessage());
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

		try {
			if (ComboBox_id_clientes.getPromptText().isEmpty()) {
				id = 0;
			} else {
				id = ComboBox_id_clientes.getSelectionModel().getSelectedItem().getId();
			}
		} catch (Exception e) {
			id = 0;
		}

		Clientes cliente = new Clientes(id, TextField_Nombre_clientes.getText(), TextField_direccion.getText());

		if ((cliente.getNombre().isEmpty()) && (cliente.getDireccion().isEmpty())) {
			cliente = null;
		}

		return cliente;
	}
	
	public void cambiar_de_extremos(ActionEvent action) {
		ultimo_clientes.setDisable(false);
		siguiente_clientes.setDisable(false);
		anterior_clientes.setDisable(false);
		primero_clientes.setDisable(false);
		
		String id_boton = "";
		id_boton = ((Button) action.getSource()).getId();
		Clientes cliente = null;
		List<Clientes> clientes = null;
		int posicion = 0;
		
		try {
			clientes=controladorclientes.findAll();
			posicion=ComboBox_id_clientes.getSelectionModel().getSelectedIndex();
			
			
			
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
			ComboBox_id_clientes.getSelectionModel().select(posicion);
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
				+ controladorclientes.preparedstatement.toString().substring("com.mysql.cj.jdbc.ClientPreparedStatement: ".length());
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
