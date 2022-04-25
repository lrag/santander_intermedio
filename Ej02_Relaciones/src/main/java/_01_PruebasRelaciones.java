import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Pedido;

public class _01_PruebasRelaciones {

	public static void main(String[] args) {
		
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:file:C:/H2/curso_santander");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "update"); //Esto en producción jamás!
		settings.put(Environment.SHOW_SQL, "true");

		StandardServiceRegistry standardRegistry =
				new StandardServiceRegistryBuilder().applySettings(settings).build();
		MetadataSources sources = new MetadataSources( standardRegistry );
		sources.addPackage("com.curso.modelo.entidad");
		sources.addAnnotatedClass(Cliente.class);
		sources.addAnnotatedClass(Pedido.class);

		//Hibernate a palo seco:
		//SessionFactory sf = sources.getMetadataBuilder().build().buildSessionFactory();
		//JPA:
		EntityManagerFactory emf = sources.getMetadataBuilder().build().buildSessionFactory();
		
		///////////////////
		//RELACIONES 1..N//
		///////////////////

		Cliente c = new Cliente(null,"Bud Spencer","C/Falsa,123","555123456", 8000);

		//Los pedidos deben conocer a su cliente porque su tabla alberga la FK
		Pedido p1 = new Pedido(null, "PED-0", LocalDate.now(), "PENDIENTE", c, null);
		Pedido p2 = new Pedido(null, "PED-1", LocalDate.now(), "PENDIENTE", c, null);
		Pedido p3 = new Pedido(null, "PED-2", LocalDate.now(), "PENDIENTE", c, null);
		
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(p1);
		pedidos.add(p2);
		pedidos.add(p3);
		
		//Opcionalmente el cliente puede conocer a sus pedidos
		c.setPedidos(pedidos);
		
		System.out.println("=====================================");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//Podemos insertar las cuatro entidades una a una (siempre en el orden correcto)
		//em.persist(c);
		//em.persist(p1);
		//em.persist(p2);
		//em.persist(p3);
		
		//Si indicamos el cascade podemos insertar solo el cliente y se insertarán tb sus pedidos
		em.persist(c);
		
		//Como borrar un cliente y sus pedidos sin cascade
		//Cliente cAux = em.find(Cliente.class, 1);
		//for(Pedido pAux : cAux.getPedidos()) {
		//	em.remove(pAux);
		//}
		//em.remove(cAux);
		
		//Si hay un cascade REMOVE de cliente a pedido al borrar el cliente se borran los pedidos
		//Cliente cAux = em.find(Cliente.class, 1);
		//em.remove(cAux);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}


/*

@Entity
class Cliente {
	@Id
	int id;
	String nombre;

	@ManyToOne
	Comercial comercialParaCosasNormales;
	@ManyToOne
	Comercial comercialParaOtrasCosas;
}

class Comercial {
	@Id
	int id;
	String nombre;	
	
	@OneToMany(mappedBy = "comercialParaCosasNormales")
	List<Cliente> clientesParaCosasNormales;
	@OneToMany(mappedBy = "comercialOtrasCosas")
	List<Cliente> clientesParaOtrasCosas;	
}

*/



