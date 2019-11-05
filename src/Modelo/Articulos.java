package Modelo;

public class Articulos {
	private int id;
	private String nombre;
	private double precio;
	private int grupo;
	private String codigo;
	
	

	public Articulos(int id, String nombre, double precio,String codigo, int grupo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.grupo = grupo;
		this.codigo = codigo;
	}
	
	public Articulos() {
		this.id = 0;
		this.nombre = "";
		this.precio = 0.0;
		this.grupo = 0;
		this.codigo = "";
	}
	
	public Articulos(Articulos articulos) {
		this.id = articulos.getId();
		this.nombre = articulos.getNombre();
		this.precio = articulos.getPrecio();
		this.grupo = articulos.getGrupo();
		this.codigo = articulos.getCodigo();
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public int getGrupo() {
		return grupo;
	}



	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	@Override
	public String toString() {
		return "Articulos [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", grupo=" + grupo + "]";
	}
}
