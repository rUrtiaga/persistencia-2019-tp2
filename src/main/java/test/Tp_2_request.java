package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.TestMethodOrder;

import model.Cliente;
import model.Factura;
import model.Producto;
import model.Proveedor;
import utils.HibernateUtil;
import utils.Pair;

@TestMethodOrder(Alphanumeric.class)
class Tp_2_request{
	static Session session;
	
	@AfterAll
	static void closeThings() {	
		HibernateUtil.getSessionFactory().close();
	}
	
	@org.junit.jupiter.api.Test
	void punto_5() {

		session= HibernateUtil.getSessionFactory().openSession();
		
		Cliente cli0 = new Cliente("22","El Loco","Juan");
		Cliente cli1 = new Cliente("23","Castas","Juan");
		Cliente cli2 = new Cliente("24","Pettinato","Roberto");

		try {
			session.beginTransaction();
			session.persist(cli0);
			session.persist(cli1);
			session.persist(cli2);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception",e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}
	
	
	@org.junit.jupiter.api.Test
	void punto_6() {

		session= HibernateUtil.getSessionFactory().openSession();
		
		Proveedor pro0 = new Proveedor(0,"El Arca - ramos generales");
		Proveedor pro1 = new Proveedor(1,"Limpito - productos de limpieza");
		Proveedor pro2 = new Proveedor(2,"Acuarela - dietetica");
		
		try {
			session.beginTransaction();
			session.persist(pro0);
			session.persist(pro1);
			session.persist(pro2);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception",e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}

	
	@org.junit.jupiter.api.Test
	void punto_7() {
		session= HibernateUtil.getSessionFactory().openSession();
		
		Producto p0 = new Producto("A3A","Salchichas",new BigDecimal(23.3));
		Producto p1 = new Producto("A3B","Harina",new BigDecimal(15.00));
		Producto p2 = new Producto("A3C","Leche",new BigDecimal(25.5));

		try {
			session.beginTransaction();
			Proveedor pro = session.byNaturalId(Proveedor.class).using("codigo",0).load();

			p0.addProveedor(pro);
			p1.addProveedor(pro);
			p2.addProveedor(pro);
			
			session.persist(p0);
			session.persist(p1);
			session.persist(p2);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Saltó Exception",e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
	
	
	//PUNTO 8
		//cambio de precio un producto

	@org.junit.jupiter.api.Test
		void punto_8_changePrice() {
		session= HibernateUtil.getSessionFactory().openSession();
		

			try {
				session.beginTransaction();
				//Me traigo el producto
				Producto pro1 = session.byNaturalId(Producto.class).using("codigo", "A3A").load();
				
				pro1.newPrecio(new BigDecimal(120));
				
				session.persist(pro1);
				
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				fail("Saltó Exception",e);
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			

		}

		//agrego precio a un producto nuevo

	@org.junit.jupiter.api.Test
		void punto_8_newPrice() {
		session= HibernateUtil.getSessionFactory().openSession();
		
			//Creo el producto
			Producto pro0 = new Producto("B3E","Cacao");

			try {
				//Persisto el producto en una transaccion
				session.beginTransaction();
				session.persist(pro0);
				session.getTransaction().commit();
				
//				//puedo traerme el producto pero como ya lo tengo no lo hago.
//				Producto pro1 = session.byNaturalId(Producto.class).using("codigo", "A3A").load();
//				
				
				session.beginTransaction();
				pro0.newPrecio(new BigDecimal(66));
				session.persist(pro0);
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				fail("Saltó Exception",e);
				session.getTransaction().rollback();
			} finally {
				session.close();
			}

		}

		

	@org.junit.jupiter.api.Test
		void punto_9_altaFactura() {
		session= HibernateUtil.getSessionFactory().openSession();
		
			ArrayList<Entry<Producto, Integer>> productosConCantidad = new ArrayList<Map.Entry<Producto,Integer>>();
			Cliente cli = null;
			
			try {
				session.beginTransaction();
				//Me traigo el producto
				Producto pro1 = session.byNaturalId(Producto.class).using("codigo", "A3A").load();
				Producto pro2 = session.byNaturalId(Producto.class).using("codigo", "A3B").load();

				//Los agrego con sus cantidades
				productosConCantidad.add(Pair.of(pro1,2));
				productosConCantidad.add(Pair.of(pro2,10));
				
				cli = session.byNaturalId(Cliente.class).using("codigo", "22").load();
				
			} catch (Exception e) {
				e.printStackTrace();
				fail("Saltó Exception",e);
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			session = HibernateUtil.getSessionFactory().openSession();
			
			//Creo la factura y les agrego los productos
			Factura f0 = new Factura(cli,productosConCantidad);
			
			
			try {
				session.beginTransaction();
				session.persist(f0);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				fail("Saltó Exception",e);
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
			
		}
}
