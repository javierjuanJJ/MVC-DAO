package Controlador;

import java.util.List;

import Modelo.Articulos;
import Modelo.Grupos;
import javafx.fxml.FXML;

public class ControladorFormularioArticulos implements ArticuloDAO {
	
	@FXML
	public void initialize() {

	}

	@Override
	public Articulos findByPK(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public List<Articulos> findAll() throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public List<Articulos> findBySQL(String sqlselect) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public boolean insert(Articulos t) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public boolean update(Articulos t) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	
	public boolean insert(Grupos t) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

}
