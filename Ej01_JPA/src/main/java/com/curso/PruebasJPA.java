package com.curso;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.curso.modelo.entidad.Pelicula;

public class PruebasJPA {

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
		sources.addAnnotatedClass(Pelicula.class);
		sources.addPackage("com.curso.modelo.entidad");

		//Hibernate a palo seco:
		SessionFactory sf = sources.getMetadataBuilder().build().buildSessionFactory();
		//JPA:
		EntityManagerFactory emf = sources.getMetadataBuilder().build().buildSessionFactory();
		
		EntityManager em = null;
		
		//////////
		//INSERT//
		//////////
		System.out.println("=========================================");
		Pelicula p1 = new Pelicula(null, "Conan", "Acción", 500, LocalDate.now());
		em = emf.createEntityManager();
		em.getTransaction().begin();
		//em.persist(p1); 
		System.out.println(p1);
		em.getTransaction().commit(); //.rollback();
		em.close();
		
		///////////////////////////////
		//UPDATE CON TODOS LOS CAMPOS//
		///////////////////////////////		
		System.out.println("=========================================");		
		/*Si usamos esta pelicula para el merge perderemos titulo, genero y fechaEstreno
		Integer idPelicula = 3;
		int duracion = 131;
		Pelicula p2 = new Pelicula();
		p2.setId(idPelicula);
		p2.setDuracion(duracion);
		*/		
		//Debemos asegurarnos de que el objeto tenga todos los valores
		Pelicula p2 = new Pelicula(3, "Die Hard", "Acción", 131, LocalDate.now());		
		em = emf.createEntityManager();
		em.getTransaction().begin();		
		//merge no es update
		//merge es sincronizar la base de datos con el objeto
		em.merge(p2);
		em.getTransaction().commit(); //.rollback();
		em.close();		
		
		/////////////////
		//SELECT POR ID//
		/////////////////
		System.out.println("=========================================");
		em = emf.createEntityManager();
		Pelicula p3 = em.find(Pelicula.class, 3);
		System.out.println(p3);
		em.close();
		
		//////////////////////////////////////////////
		//UPDATE CUANDO NO TENEMOS TODOS LOS VALORES//
		//////////////////////////////////////////////
		System.out.println("=========================================");
		//Temenos estos datos:
		Integer idPelicula = 4;
		int duracion = 121;
		
		em = emf.createEntityManager();
		em.getTransaction().begin();		

		Pelicula p4 = em.find(Pelicula.class, idPelicula);
		p4.setDuracion(duracion);
		
		//Gracias a la caché de primer nivel el merge no es necesario
		//(Pero no pasa nada si lo ponemos)
		//em.merge(p4);
	
		em.getTransaction().commit(); //.rollback();
		em.close();		
		
		/////////////////////////
		//CACHÉ DE PRIMER NIVEL//
		/////////////////////////
		System.out.println("=========================================");
		em = emf.createEntityManager(); //La caché de este em está vacía
		
		Pelicula p5a = em.find(Pelicula.class, 5); //Hace el select y guarda el objeto en la caché
		Pelicula p5b = em.find(Pelicula.class, 5); //Ya no hay select y nos entregan la instancia de pelicula que está en la caché
		Pelicula p5c = em.find(Pelicula.class, 5); //Ídem

		p5a.setTitulo("Airplane!");		
		System.out.println(p5c);	
		
		em.close();
		
		//////////
		//DELETE//
		//////////
		System.out.println("=========================================");
		idPelicula = 6;
		Pelicula p6 = new Pelicula();
		p6.setId(idPelicula); //Para borrar solo necesitamos el id
		
		em = emf.createEntityManager(); //La caché de este em está vacía
		em.getTransaction().begin();		
		//em.remove(p6); //El delete solo se ejecuta en el momento del commit

		em.getTransaction().commit(); //.rollback();
		em.close();			
		
		///////////
		//REFRESH//
		///////////
		System.out.println("=========================================");
		//Temenos estos datos:
		idPelicula = 2;
		duracion = 136;
		
		em = emf.createEntityManager();
		em.getTransaction().begin();		

		Pelicula p7 = em.find(Pelicula.class, idPelicula);
		//Cambiamos la duración. Se marca el objeto en la caché como 'no sincronizado'
		p7.setDuracion(duracion);
		em.merge(p7); //Esto no hace el update. El update se ejecuta durante el commit
		
		//Al hacer refresh se sincroniza el objeto con lo que sigue estando en la tabla
		//Asi que pierde la marca
		em.refresh(p7); //fuerza un select

		//Ahora no hay update, porque hemos hecho 'refresh'
		em.getTransaction().commit(); //.rollback();
		em.close();			
		
		/////////////////
		//OTROS MÉTODOS//
		/////////////////
		
		//
		//DETACH: Solicitamos al em que se 'olvide' de una entidad que tiene en la caché
		//
		//em.detach(e);
		
		//
		//CLEAR: Vacía la caché de primer nivel. 
		//Si habian objetos no sincronizados (tanto para update como para delete) en la caché se pierden esos cambios
		//
		
		//
		//FLUSH: Sincroniza la base de datos con los cambios que tengan los objetos de la caché
		//Luego se podrá seguir haciendo rollback
		//Utilizado normalmente antes de un CLEAR
		//
			
		/////////////////////////////////////////
		//JPQL: Java Persistence Query Language//
		/////////////////////////////////////////
		System.out.println("=========================================");		
		em = emf.createEntityManager();
		
		//select p.* from peliculas as p
		//select p from Pelicula as p
		Query q = em.createQuery("select p from Pelicula p"); //El 'as' es opcional y no lo pone nadie
		List<Pelicula> peliculas = q.getResultList();
		for(Pelicula pAux: peliculas) {
			System.out.println(pAux);
		}
		
		em.close();
		
	}
	
}







