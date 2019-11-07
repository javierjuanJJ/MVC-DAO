package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {

    private static Scene scene;
    private static Stage Stage;

    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/Resources/Principio"));
        stage.setScene(scene);
        Stage=stage;
        Stage.setTitle("Pantalla inicial");
        Stage.getIcons().clear();
		Stage.getIcons().add(new Image("/Resources/"+"principio.png"));
        Stage.show();
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    /**
	 * Cambia la escena de la ventana.
	 */
	
	public void Cambiar_Pantalla(String id) throws IOException {
		FXMLLoader fxmlLoader = null;
		StringBuilder archivo=new StringBuilder();
		StringBuilder icono=new StringBuilder();
		String[] titulo=null;
		
		archivo.append("/Resources/");
		icono.append("/Resources/");
		switch (id) {
		case "Pantalla_Clientes":
			archivo.append("Form_clientes");
			icono.append("cliente.jpg");
		break;
		case "Pantalla_Articulos":
			archivo.append("Form_articulos");
			icono.append("articulo.jpg");
			break;
		case "Pantalla_Grupos":
			archivo.append("Form_grupos");
			icono.append("grupo.jpg");
			break;
		case "Pantalla_Principio":
			archivo.append("Principio");
			icono.append("principio.png");
			break;
		default:
			break;
		}
		archivo.append(".fxml");
		
		fxmlLoader = new FXMLLoader(Main.class.getResource(archivo.toString()));
		scene = new Scene((Parent) fxmlLoader.load());
		Stage.setScene(scene);
		titulo=id.split("_");
		Stage.setTitle(titulo[0] + " de " + titulo[1]);
		Stage.getIcons().clear();
		Stage.getIcons().add(new Image(icono.toString()));
		Stage.show();
        
	}

}
