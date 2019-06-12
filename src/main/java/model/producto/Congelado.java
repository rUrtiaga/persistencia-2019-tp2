package model.producto;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Congelado extends Alimento {

	public Congelado() {
		super();
	}
	
	public Congelado(String codigo,String desc,BigDecimal monto) {
		super(codigo,desc,monto);
	}
	
	@Override
	public BigDecimal getPrecioFinal() {
		return this.calcularPrecioConRecargo(0.08);
	}

}
