package model.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public abstract class Alimento extends Producto {
	private LocalDateTime fechaDeIngreso;
	
	public Alimento() {
		super();
	}

	public Alimento(String codigo, String desc) {
		this(codigo,desc,null);
	}
	
	public Alimento(String codigo,String desc,BigDecimal monto) {
		super(codigo,desc,monto);
		this.setFechaDeIngreso(LocalDateTime.now());
	}

	
	public LocalDateTime getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(LocalDateTime fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

}
