package Controlador;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorPrincipio {
	
	
	@FXML
	public void initialize() {

	}
	
	public void Cambiar_Pantalla(ActionEvent action) throws IOException {
		String id_boton ="";
		id_boton = ((Button) action.getSource()).getId();
		Main main=new Main();	
		main.Cambiar_Pantalla(id_boton);
	}
}
