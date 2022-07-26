package Modelo;

import java.sql.Date;
import java.util.ArrayList;

public class Facturas {
	private int id;
	private Date fecha;
	private Clientes cliente;
	private Vendedores vendedor;
	private ArrayList<Lineas_Facturas> lineas_de_la_factura;
	
	public Facturas(int id, Date fecha, Clientes cliente, Vendedores vendedor,
			ArrayList<Lineas_Facturas> lineas_de_la_factura) {
		
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.lineas_de_la_factura = lineas_de_la_factura;
	}
	
	public Facturas() {
		
		this.id = 0;
		this.fecha=new Date(0);
		this.cliente = new Clientes();
		this.vendedor = new Vendedores();
		this.lineas_de_la_factura = new ArrayList();
	}
	
	public Facturas(Facturas factura) {
		
		this.id = factura.getId();
		this.fecha = factura.getFecha();
		this.cliente = factura.getCliente();
		this.vendedor = factura.getVendedor();
		this.lineas_de_la_factura = factura.getLineas_de_la_factura();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Vendedores getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedores vendedor) {
		this.vendedor = vendedor;
	}

	public ArrayList<Lineas_Facturas> getLineas_de_la_factura() {
		return lineas_de_la_factura;
	}

	public void setLineas_de_la_factura(ArrayList<Lineas_Facturas> lineas_de_la_factura) {
		this.lineas_de_la_factura = lineas_de_la_factura;
	}
	@Override
	public String toString() {
		return "Facturas [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", vendedor=" + vendedor
				+ ", lineas_de_la_factura=" + lineas_de_la_factura + "]";
	}
	
}
