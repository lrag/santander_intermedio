import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Consultoria_Joined;
import com.curso.modelo.entidad.DatosBancarios;
import com.curso.modelo.entidad.Hardware_Joined;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto_Joined;
import com.curso.modelo.entidad.Software_Joined;


public class PruebasConsultasNativas {

	public static void main(String[] args) {
		
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:file:C:/H2/curso");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");	
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
		
		StandardServiceRegistry standardRegistry =
				new StandardServiceRegistryBuilder().applySettings(settings).build();
		MetadataSources sources = new MetadataSources( standardRegistry );
		sources.addAnnotatedClass(Cliente.class);
		sources.addAnnotatedClass(DatosBancarios.class);
		sources.addAnnotatedClass(Pedido.class);
		sources.addAnnotatedClass(Comercial.class);
		sources.addAnnotatedClass(Producto_Joined.class);
		sources.addAnnotatedClass(Hardware_Joined.class);
		sources.addAnnotatedClass(Software_Joined.class);
		sources.addAnnotatedClass(Consultoria_Joined.class);
		
		//SessionFactory sf = sources.getMetadataBuilder().build().buildSessionFactory();		
		//Session em = sf.openSession();
		//em.beginTransaction();
		
		EntityManagerFactory emf = sources.getMetadataBuilder().build().buildSessionFactory();	
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//Insert, update, delete
		System.out.println("======================================");
		Query q1 = em.createNativeQuery("insert into cliente (nombre, ciudad, calle, telefono) values (?,?,?,0)");
		q1.setParameter(1, "Chowder");
		q1.setParameter(2, "Su ciudad");
		q1.setParameter(3, "Su casa");
		q1.executeUpdate(); 
		
		//Select
		System.out.println("======================================");
		Query q2 = em.createNativeQuery("select nombre, calle from cliente");
		List<Object[]> rs = q2.getResultList();
		for(Object[] array: rs){
			System.out.println(array[0]+","+array[1]);
		}
		
		System.out.println("======================================");
		Query q3 = em.createNativeQuery("select * from cliente", Cliente.class);
		List<Cliente> rs2 = q3.getResultList();
		for(Cliente c: rs2){
			System.out.println(c);
		}
		
		em.getTransaction().commit();
		em.close();			
		emf.close();
	}
	
	
}