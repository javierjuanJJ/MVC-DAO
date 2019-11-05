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
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_by_PK);
		preparedstatement.setInt(1, id);
		resultset = preparedstatement.executeQuery();
		resultset.first();
		articulo_recibido = new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
				resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo"));
		return articulo_recibido;
	}

	public List<Articulos> findAll() throws Exception {
		List<Articulos> articulos_recibidos = new ArrayList<Articulos>();
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all);
		resultset = preparedstatement.executeQuery();

		while (resultset.next()) {
			articulos_recibidos.add(new Articulos(resultset.getInt("id"), resultset.getString("nombre"),
					resultset.getDouble("precio"), resultset.getString("codigo"), resultset.getInt("grupo")));
		}
		return articulos_recibidos;
	}

	public List<Grupos> findAll_grupos() throws Exception {
		List<Grupos> grupos_recibidos = new ArrayList<Grupos>();
		ResultSet resultset = null;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_select_all_grupos);
		resultset = preparedstatement.executeQuery();

		while (resultset.next()) {
			grupos_recibidos.add(new Grupos(resultset.getInt("id"), resultset.getString("descripcion")));
		}
		return grupos_recibidos;
	}

	public List<Articulos> findBySQL(String sqlselect) throws Exception {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}

	public boolean insert(Articulos t) throws Exception {
		int salida = 0;
		boolean resultado = false;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_INSERT);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setDouble(2, t.getPrecio());
		preparedstatement.setString(3, t.getCodigo());
		preparedstatement.setInt(4, t.getGrupo());
		salida = preparedstatement.executeUpdate();

		if (salida > 0) {
			resultado = true;
		}

		return resultado;
	}

	public boolean update(Articulos t) throws Exception {

		int salida = 0;
		boolean resultado = false;
		preparedstatement = Conexion.getConnection().prepareStatement(sql_UPDATE);
		preparedstatement.setString(1, t.getNombre());
		preparedstatement.setDouble(2, t.getPrecio());
		preparedstatement.setString(3, t.getCodigo());
		preparedstatement.setInt(4, t.getGrupo());
		preparedstatement.setInt(5, t.getId());

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

	public void actualizar_informacion_clientes() {
		try {
			ComboBox_id_articulos.getItems().clear();
			List<Articulos> articulos_recibidos = findAll();

			for (int contador = 0; contador < articulos_recibidos.size(); contador++) {
				ComboBox_id_articulos.getItems().add(articulos_recibidos.get(contador));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void poner_informacion_clientes() {
		Articulos articulo_seleccionado = null;
		articulo_seleccionado = ComboBox_id_articulos.getSelectionModel().getSelectedItem();
		poner_informacion(articulo_seleccionado);
	}

	public void insertar_informacion_clientes() {
		Articulos articulo_seleccionado = coger_informacion();
		if (ComboBox_codigo_articulos.getPromptText().isEmpty()) {
			articulo_seleccionado.setCodigo(articulo_seleccionado.getNombre().substring(0, 5));
		}

		try {
			insert(articulo_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertar_grupo() {
		Grupos grupo_seleccionado = new Grupos(0, this.TextField_descripcion.getText());

		try {
			insert(grupo_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_informacion() {
		Articulos articulo_seleccionado = coger_informacion();
		if (ComboBox_codigo_articulos.getPromptText().isEmpty()) {
			articulo_seleccionado.setCodigo(articulo_seleccionado.getNombre().substring(0, 5));
		}

		try {
			update(articulo_seleccionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminar_informacion() {
		Articulos articulo_seleccionado = coger_informacion();

		try {
			delete(articulo_seleccionado.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Buscar_por_id() {
		Articulos articulo_seleccionado = null;
		try {
			articulo_seleccionado = findByPK(Integer.parseInt(TextField_buscar_por_id_articulos.getText()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		poner_informacion(articulo_seleccionado);
	}

	public void poner_informacion(Articulos articulo) {
		TextField_buscar_por_id_articulos.setPromptText(articulo.getId() + "");
		TextField_Nombre_articulos.setText(articulo.getNombre());
		ComboBox_grupos_articulos.setPromptText(articulo.getGrupo() + "");
		TextField_precio_articulos.setText(articulo.getPrecio() + "");
		ComboBox_codigo_articulos.setPromptText(articulo.getCodigo());
	}

	public Articulos coger_informacion() {
		Grupos grupo_seleccionado = cogergrupo();
		int id = 0;
		if (!TextField_buscar_por_id_articulos.getPromptText().isEmpty()) {
			id = Integer.parseInt(TextField_buscar_por_id_articulos.getPromptText());
		}

		Articulos articulo = new Articulos(id, TextField_Nombre_articulos.getText(),
				Double.parseDouble(TextField_precio_articulos.getText()), ComboBox_codigo_articulos.getPromptText(),
				grupo_seleccionado.getId());
		return articulo;
	}

	private Grupos cogergrupo() {
		Grupos grupo_seleccionado = new Grupos();
		ResultSet resultset = null;

		try {
			preparedstatement = Conexion.getConnection()
					.prepareStatement("SELECT * FROM empresa_ad.grupos WHERE id=?;");
			preparedstatement.setInt(1, Integer.parseInt(this.ComboBox_grupos_articulos.getPromptText()));

			resultset = preparedstatement.executeQuery();
			resultset.first();
			grupo_seleccionado.setId(resultset.getInt("id"));
			grupo_seleccionado.setDescripcion(resultset.getString("descripcion"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

}
