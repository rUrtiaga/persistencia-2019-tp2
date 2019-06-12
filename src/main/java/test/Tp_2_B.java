package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.producto.Alimento;
import model.producto.General;
import model.producto.Gondola;
import model.producto.Producto;
import utils.HibernateUtil;

@TestMethodOrder(Alphanumeric.class)
class Tp_2_B {
	static Session session;

	@BeforeAll
	static void loadExample() {
		Example.generateExample();
	}

	@AfterAll
	static void closeThings() {
		HibernateUtil.getSessionFactory().close();
	}

	// Retornar todos los productos.
	@Test
	void punto_2_A() {
		session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<Producto> q = session.createQuery("from Producto");
			List<Producto> result = q.getResultList();
			System.out.println(result);

			// Me fijo que esten todos los productos
			assertEquals(27, result.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	// Retornar los alimentos.
	@Test
	void punto_2_B() {
		session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<Alimento> q = session.createQuery("from Alimento");
			List<Alimento> result = q.getResultList();
			System.out.println(result);

			// Me fijo que esten todos los Alimentos
			assertEquals(21, result.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	// Retornar los alimentos de Gondola.
	@Test
	void punto_2_C() {
		session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<Gondola> q = session.createQuery("from Gondola");
			List<Gondola> result = q.getResultList();
			System.out.println(result);

			// Me fijo que esten todos los Alimentos de Gondola
			assertEquals(7, result.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	// Retornar los productos de tipo General con un peso mayor a 4 y precio final
	// mayor a $1500.
	@Test
	void punto_2_D() {
		session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<General> q = session.createQuery("from General ");
			List<General> result = q.getResultList();
			result = result.stream()
					.filter(g -> g.getPrecioFinal().compareTo(new BigDecimal(1500)) == 1 && g.getPeso() > 4)
					.collect(Collectors.toList());
			for (General general : result) {
						System.out.println(
								" ** Desc: " +general.getDescripcion()+ " precio: " +general.getPrecioFinal().doubleValue()+ " peso: " + general.getPeso());
			}
			
//			result.forEach(r->strin=strin+r.getPrecio()+r.getDescripcion());

			// Me fijo que esten todos los Alimentos de Gondola
			assertEquals(3, result.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

}
