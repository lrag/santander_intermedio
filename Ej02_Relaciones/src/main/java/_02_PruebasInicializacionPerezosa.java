import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Pedido;

public class _02_PruebasInicializacionPerezosa {

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
		Pedido p1 = new Pedido(null, "PED-0", LocalDate.now(), "PENDIENTE", c, null);
		Pedido p2 = new Pedido(null, "PED-1", LocalDate.now(), "PENDIENTE", c, null);
		Pedido p3 = new Pedido(null, "PED-2", LocalDate.now(), "PENDIENTE", c, null);
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(p1);
		pedidos.add(p2);
		pedidos.add(p3);
		c.setPedidos(pedidos);
		
		/*
		System.out.println("=====================================");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		*/
		
		System.out.println("=====================================");
		EntityManager em = emf.createEntityManager();

		Cliente cAux = em.find(Cliente.class, 1);
		System.out.println(cAux.getNombre());
		System.out.println(cAux.getDireccion());

		//Podemos forzar la carga de la relación, pero es una chapucilla
		//cAux.getPedidos().size();
		
		//Podemos cambiar la relación entre cliente y pedidos a ANSIOSA
		//NO POR FAVOR!

		//Si accedieramos a los pedidos despues de haber cerrar el EM, LAZI INICIALIZATION EXCEPTION
		System.out.println(cAux.getPedidos());
		
		em.close();
		
		System.out.println("=====================================");
		em = emf.createEntityManager();

		Pedido pAux = em.find(Pedido.class, 1);

		System.out.println(pAux.getCodigo());
		em.close();		
		//Las relaciones contra uno son por defecto EAGER
		System.out.println(pAux.getCliente());
		
		
		
	}
	
}

/*
@Entity
class Libro {
	@Id
	private String ISBN;
	private String titulo;
	@ManyToOne //EAGER
	private Autor autor;
	@OneToOne(fetch = FetchType.LAZY) //Por defecto era EAGER
	private Texto contenido;	
}

@Entity
class Autor {
	@Id
	private int id;
	private String nombre;
}

@Entity
class Texto {
	private String texto;
}
*/


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



