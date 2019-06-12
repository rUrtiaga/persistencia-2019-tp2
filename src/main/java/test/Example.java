package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import model.producto.Congelado;
import model.producto.Frio;
import model.producto.General;
import model.producto.Gondola;
import utils.HibernateUtil;

public final class Example {

	public static void generateExample() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			congelados().forEach(c->session.persist(c));
			gondola().forEach(c->session.persist(c));
			frios().forEach(c->session.persist(c));
			general().forEach(c->session.persist(c));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
	
	private static List<General> general() {
		String[] arr = {"Alimento para Gatos", "Alimento para Perros", "Plato", "Vaso",
				"Lingote de Oro", "Vino en damajuana"};
		List<String> nombres = Arrays.asList( arr);
		List<General> general = new ArrayList<>();
		
		for (int i = 0; i < nombres.size(); i++) {
			general.add(new General("C" + i, nombres.get(i),new BigDecimal(i*500),i+1.5));
		}
		
		return general;
	}

	private static List<Frio> frios() {
		String[] arr = {"Leche", "Manteca", "Yogourt", "Gasesosa Cola",
				"Pascualina", "Salchichas", "Queso roquefort"};
		List<String> nombres = Arrays.asList( arr);
		List<Frio> frios = new ArrayList<>();
		
		for (int i = 0; i < nombres.size(); i++) {
			frios.add(new Frio("C" + i, nombres.get(i),new BigDecimal(i*5)));
		}
		
		return frios;
	}

	private static List<Congelado> congelados() {
		String[] arr = {"Pezcado", "Patitas de pollo", "Hamburgesas", "Papas Fritas",
				"Brocoli", "Fideos", "Milanesas"};
		List<String> nombres = Arrays.asList( arr);
		List<Congelado> congelados = new ArrayList<>();
		
		for (int i = 0; i < nombres.size(); i++) {
			congelados.add(new Congelado("C" + i, nombres.get(i),new BigDecimal(i*5)));
		}
		
		return congelados;
	}
	
	

	private static List<Gondola> gondola() {
		String[] arr = {"Amaranto", "Arroz", "Avena", "Tortillas de maíz",
				"Pan Integral", "Fideos", "Cereal"};
		List<String> nombres = Arrays.asList( arr);
		List<Gondola> gondola = new ArrayList<>();
		
		for (int i = 0; i < nombres.size(); i++) {
			gondola.add(new Gondola("G" + i, nombres.get(i),new BigDecimal(i*5),i*33));
		}
		
		return gondola;
	}
	
}
