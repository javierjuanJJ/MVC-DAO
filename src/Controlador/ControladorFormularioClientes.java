package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import Modelo.Clientes;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorFormularioClientes implements ClienteDAO {
	private static Connection conexion = null;
	private static PreparedStatement preparedstatement = null;
	@FXML
	public void initialize() {
		try {
			conexion=Conexion.getConnection();
		} catch (Exception e) {
			Platform.exit();
		}
	}

	
	public void Cambiar_Pantalla(ActionEvent action) throws IOException {
		String id_boton ="";
		id_boton = ((Button) action.getSource()).getId();
		Main main=new Main();	
		main.Cambiar_Pantalla(id_boton);
	}

	public Clientes findByPK(int id) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}


	public List<Clientes> findAll() throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}


	public List<Clientes> findBySQL(String sqlselect) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}


	public boolean insert(Clientes t) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return false;
	}


	public boolean update(Clientes t) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return false;
	}


	public boolean delete(int id) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return false;
	}

}
