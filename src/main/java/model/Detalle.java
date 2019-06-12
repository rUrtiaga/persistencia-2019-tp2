package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import model.producto.Producto;

@Entity(name="Detalle")
@Table(name="DETALLE")
public class Detalle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Long id;
	@ManyToOne
	private Factura factura;
	@OneToOne
	@JoinColumn(name = "producto_id", foreignKey=@ForeignKey(name="producto_detalle_id_fk"))
	private Producto producto;
	private int cantidad;
	@OneToOne
	@JoinColumn(name = "precio_id", foreignKey=@ForeignKey(name="precio_detalle_id_fk"))
	private Precio precio;
	
	public Detalle() {
		super();
	}
	
	public Detalle(Factura factura) {
		this.setFactura(factura);
	}
	
	
	public Detalle(Factura factura2, Producto producto2) {
		this.setFactura(factura2);
		this.setProducto(producto2);
		this.setPrecio(producto2.getPrecio());
		this.setCantidad(1);
	}

	public Detalle(Factura factura2, Producto producto2, Integer cantidad) {
		this.setFactura(factura2);
		this.setProducto(producto2);
		this.setPrecio(producto2.getPrecio());
		this.setCantidad(cantidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Detalle)) {
			return false;
		}
		Detalle other = (Detalle) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Precio getPrecio() {
		return precio;
	}
	public void setPrecio(Precio precio) {
		this.precio = precio;
	}
	
	

}
