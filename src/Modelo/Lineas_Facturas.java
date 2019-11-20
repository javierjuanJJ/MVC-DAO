package Modelo;

public class Lineas_Facturas {
	private int linea;
	private Facturas factura;
	private Articulos articulo;
	private int cantidad;
	private double importe;
	
	public Lineas_Facturas(int linea, Facturas factura, Articulos articulo, int cantidad, double importe) {
		this.linea = linea;
		this.factura = factura;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.importe = importe;
	}
	
	public Lineas_Facturas() {
		this.linea = 0;
		this.factura = new Facturas();
		this.articulo = new Articulos();
		this.cantidad = 0;
		this.importe = 0;
	}
	
	public Lineas_Facturas(Lineas_Facturas lineas_factura) {
		this.linea = lineas_factura.getLinea();
		this.factura = lineas_factura.getFactura();
		this.articulo = lineas_factura.getArticulo();
		this.cantidad = lineas_factura.getCantidad();
		this.importe = lineas_factura.getImporte();
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public Facturas getFactura() {
		return factura;
	}

	public void setFactura(Facturas factura) {
		this.factura = factura;
	}

	public Articulos getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulos articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Lineas_Facturas [linea=" + linea + ", factura=" + factura + ", articulo=" + articulo + ", cantidad="
				+ cantidad + ", importe=" + importe + "]";
	}
}
