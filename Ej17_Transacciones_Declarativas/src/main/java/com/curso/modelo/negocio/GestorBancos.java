package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class GestorBancos {

	public void comprobarTC(Integer numeroTC) throws Exception {
		
		System.out.print("comprobando TC...");
		
		if(numeroTC<5000) {
			System.out.println("MAL");
			
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw new Exception("Datos bancarios incorrectos");
		}
		
		System.out.println("OK");
	}	
	
	
	
}
















