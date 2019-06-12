package model.producto;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class General extends Producto {
	private double peso;

	public General() {
		super();
	}

	public General(String codigo,String desc,BigDecimal monto,double peso) {
		super(codigo,desc,monto);
		this.setPeso(peso);
	}

	@Override
	public BigDecimal getPrecioFinal() {
		if (this.getPeso() < 2) {
			return this.getPrecio().getMonto();
		}
		if (this.getPeso() >= 2 && this.getPeso() < 4) {
			return this.calcularPrecioConRecargo(0.04);
		}
		if (this.getPeso() >= 4 && this.getPeso() < 7) {
			return this.calcularPrecioConRecargo(0.07);
		}
		return this.calcularPrecioConRecargo(0.12);
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

}
