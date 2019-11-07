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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ControladorFormularioGrupos {

	private static Connection conexion = null;
	
	private static final String sql_INSERT_GRUPO = "INSERT INTO `empresa_ad`.`grupos` (`descripcion`) VALUES (?);";
	private static PreparedStatement preparedstatement = null;

	@FXML
	private TextArea TextField_descripcion;
	@FXML
	private Label error_grupos;

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
		} catch (Exception e) {
			resultado = false;
			mensajeExcepcion(e, e.getMessage());
		}
		
		

		return resultado;
	}

	public void insertar_grupo() throws Exception {
		error_grupos.setText("");
		Grupos grupo_seleccionado = new Grupos(0, TextField_descripcion.getText());
		if ((!(insert(grupo_seleccionado)))||(TextField_descripcion.getText().isEmpty())) {
			error_grupos.setText(error_grupos.getText() + "Tienes que insertar una descripcion ");
		}
		else {
			mensajeConfirmacion("Insercion completada", "La operacion ha sido un exito");
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
