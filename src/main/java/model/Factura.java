package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity(name="Factura")
@Table(name="FACTURA")
public class Factura {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private int id;
	@ManyToOne
	private Cliente cliente;

	private LocalDateTime fecha;
	//ejemplo con algunos name
	@Column(name="numero",length=8, nullable=false,unique = true)
	@Type(type="integer")
	private int numero;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Detalle> detalles = new ArrayList<Detalle>();

	
	public Factura() {
		super();
	}
	
	public Factura(Cliente cliente, ArrayList<Entry<Producto, Integer>> productosConCantidad) {
		this.setCliente(cliente);
		this.productosToDetallles(productosConCantidad);
	}
	
	
	private void productosToDetallles(ArrayList<Entry<Producto, Integer>> productos) {
		List<Detalle> detalles;
		detalles = productos.stream().map(entry -> {
			return new Detalle(this,entry.getKey(),entry.getValue());
		}).collect(Collectors.toList());
		
		this.setDetalles(new ArrayList<Detalle>(detalles));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Factura))
			return false;
		Factura other = (Factura) obj;
		return id == other.id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
			this.numero = numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

}
