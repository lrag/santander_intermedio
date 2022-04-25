import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import com.curso.modelo.entidad.Producto_Joined;


public class ProcedimientosAlmacenados {

	/*

	DELIMITER $$
	
	DROP PROCEDURE IF EXISTS `bbdd`.`PROCEDIMIENTO` $$
	CREATE DEFINER=`root`@`localhost` PROCEDURE `PROCEDIMIENTO`(IN precio INT)
	BEGIN
	
	select p.* from producto p where p.precio<precio;
	
	END $$
	
	DELIMITER ;
	
	*/
	
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_procedimiento");
		
		EntityManager em = null;
		em = emf.createEntityManager();
		//Si el procedimiento almacenado maneja las transacciones no debemos
		//invocar el begin ni commit ni rollback
		em.getTransaction().begin();
		
		StoredProcedureQuery query = em.createStoredProcedureQuery("PROCEDIMIENTO", Producto_Joined.class);
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.setParameter(1, 30);
		
		List<Producto_Joined> rs = query.getResultList();
		for(Producto_Joined p: rs){
			System.out.println(p.getNombre());
		}
		
		//Despues de haber ejecutado la consulta accedemos a los parametros OUT con
		//query.getParameter
		
		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
