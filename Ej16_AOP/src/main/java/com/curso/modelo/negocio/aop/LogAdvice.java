package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.util.Logger;

//
//El advice es el que sabe lo que hay que hacer
//No sabe cuando
//No sabe delante de quien
//

@Component
public class LogAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Autowired
	private Logger logger;
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.escribir(LocalDateTime.now()+": Llamada al método "+method.getName()+" de "+target.getClass());
		System.out.println(LocalDateTime.now()+": Llamada al método "+method.getName()+" de "+target.getClass());
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		logger.escribir(LocalDateTime.now()+": Fin de la llamada al método "+method.getName()+" de "+target.getClass());
		System.out.println(LocalDateTime.now()+": Fin de la llamada al método "+method.getName()+" de "+target.getClass());
	}

}
