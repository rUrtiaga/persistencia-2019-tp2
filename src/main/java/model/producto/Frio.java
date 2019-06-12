package model.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;

@Entity
public class Frio extends Alimento {

	public Frio() {
		super();
	}
	
	public Frio(String string, String string2, BigDecimal bigDecimal) {
		super(string, string2, bigDecimal);
	}

	@Override
	public BigDecimal getPrecioFinal() {
		return this.calcularPrecioConRecargo(0.05).multiply(this.porcentajeDeDescuento());
	}

	private BigDecimal porcentajeDeDescuento() {
		return (this.estaEnFecha()) ? new BigDecimal(0) : new BigDecimal(0.5);
	}

	private boolean estaEnFecha() {
		return LocalDateTime.now().minus(5,ChronoUnit.DAYS).isAfter(this.getFechaDeIngreso()) ;
	}


}
