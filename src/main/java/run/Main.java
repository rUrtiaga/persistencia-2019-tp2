package run;

import org.hibernate.Session;

import utils.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();

//		try {
//			session.beginTransaction();
////			session.persist(p1);
////			session.persist(e1);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
			session.close();
//		}
//
		HibernateUtil.getSessionFactory().close();

	}

}
