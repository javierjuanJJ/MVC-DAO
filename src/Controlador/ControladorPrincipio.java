package Controlador;

import java.io.IOException;
import java.sql.Connection;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorPrincipio {

	private static Connection conexion = null;

	@FXML
	public void initialize() {

		/*
		 * try { conexion = Conexion.getConnection(); } catch (Exception e) {
		 * Platform.exit(); }
		 */

	}

	public void Cambiar_Pantalla(ActionEvent action) throws IOException {
		String id_boton = "";
		id_boton = ((Button) action.getSource()).getId();
		Main main = new Main();
		main.Cambiar_Pantalla(id_boton);
	}
}
