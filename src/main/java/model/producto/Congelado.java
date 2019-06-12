package model.producto;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Congelado extends Alimento {

	@Override
	public BigDecimal getPrecioFinal() {
		return this.calcularPrecioConRecargo(0.08);
	}

}
