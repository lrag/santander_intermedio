package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.curso.util.Logger;

@Component
public class CronometroAdvice implements MethodInterceptor {

	@Autowired
	//@Qualifier("loggerCronometro")
	private Logger loggerCronometro;	
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Method method = invocation.getMethod();         //metodo al que se está llamando
		Object[] Arguments = invocation.getArguments(); //parametros de la llamada
		Object target = invocation.getThis();           //target
		
		long inicio = System.currentTimeMillis();
		
		//Tomamos el control absoluto de la llamada al target
		//Si no invocamos proceed no se llama al target
		//Si el método del target devuelve algo es responsabilidad del interceptor el devolverlo a su vez al cliente
		Object retorno = invocation.proceed();
		
		long fin = System.currentTimeMillis();		
		loggerCronometro.escribir(LocalDateTime.now()+": Llamada al metodo "+method.getName()+" de "+target.getClass()+" procesada en "+(fin-inicio)+" milisegundos.");
		System.out.println(LocalDateTime.now()+": Llamada al metodo "+method.getName()+" de "+target.getClass()+" procesada en "+(fin-inicio)+" milisegundos. ");
		
		return retorno;
	}
	
}

/*
//Si programamos el CronometroAdvice con las interfaces MethodBeforeAdvice y AfterReturningAdvice descubrimos que no es thread safe
 *y que debemos esmerarnos un poquito más...
 
@Component
public class CronometroAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Autowired
	private Logger logger;
	
	private Map<String, Long> horasLlegada = new HashMap<>();
	
	//Esto no es 'thread safe'
	//private long inicio;
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {		
		horasLlegada.put(Thread.currentThread().getName(), System.currentTimeMillis());
		//Esto no es 'thread save'
		//inicio = System.currentTimeMillis();
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		long inicio = horasLlegada.get(Thread.currentThread().getName());
		long fin = System.currentTimeMillis();		
		logger.escribir("Llamada al metodo "+method.getName()+" de "+target.getClass()+" procesada en "+(fin-inicio)+" milisegundos. "+LocalDateTime.now());
		System.out.println("Llamada al metodo "+method.getName()+" de "+target.getClass()+" procesada en "+(fin-inicio)+" milisegundos. "+LocalDateTime.now());
		horasLlegada.remove(Thread.currentThread().getName());
	}

}
*/
