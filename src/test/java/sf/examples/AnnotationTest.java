package sf.examples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

import sf.examples.annotations.Car;

public class AnnotationTest {

	@Test
	public void multipleAnnotations(){
		Car newCar = new Car();
		Annotation[] a = newCar.getClass().getAnnotations();
		for(Annotation annotation : a){
			System.out.println(annotation);
		}
	}
	
	@Test
	public void methodParameterReflection(){
		Car newCar = new Car();
		Method[] a = newCar.getClass().getDeclaredMethods();
		for(Method method : a){
			for(Parameter parameter : method.getParameters()){
				System.out.println(parameter.getName());
			}
		}
	}
}
