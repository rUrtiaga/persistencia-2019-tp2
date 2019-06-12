package model.producto;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Gondola extends Alimento {
	private Integer volumen;

	public Gondola() {
		super();
	}

	public Gondola(String codigo, String desc, BigDecimal monto,Integer volumen) {
		super(codigo,desc,monto);
		this.setVolumen(volumen);
	}

	public Gondola(String codigo, String desc, Integer volumen) {
		super(codigo,desc);
		this.setVolumen(volumen);
	}

	@Override
	public BigDecimal getPrecioFinal() {
		return (this.getVolumen() > 400) ? this.calcularPrecioConRecargo(0.03) : this.getPrecio().getMonto();
	}

	public Integer getVolumen() {
		return volumen;
	}

	public void setVolumen(Integer volumen) {
		this.volumen = volumen;
	}

}
