import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.curso.cfg.Configuracion;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

public class Pruebas {

	public static void main(String[] args) {
		
		//Sin Spring:
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2PU");
		//EntityManager em = emf.createEntityManager();
		//...
		//em.close();
		
		ApplicationContext appCtx = new AnnotationConfigApplicationContext(Configuracion.class);
		
		GestorClientes gc = appCtx.getBean(GestorClientes.class);
		
		Cliente cli = new Cliente(null,"Lisa","C/Evergreen Terrace","555",5000);
		gc.insertar(cli);
		
		System.out.println("================================================");
		List<Cliente> clientes = gc.listar();
		clientes.forEach(c -> System.out.println(c.getNombre()));
		
	}
	
}










