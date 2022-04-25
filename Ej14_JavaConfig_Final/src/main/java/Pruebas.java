import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;

public class Pruebas {

	public static void main(String[] args) {
		AbstractApplicationContext appCtx = new AnnotationConfigApplicationContext(Configuracion.class);
		
		System.out.println("===================================");
		
		GestorPeliculas gp =  appCtx.getBean(GestorPeliculas.class);
		Pelicula p1 = new Pelicula(null, "Titulo", "Genero");
		gp.insertar(p1);

		appCtx.close();
	}
	
}

