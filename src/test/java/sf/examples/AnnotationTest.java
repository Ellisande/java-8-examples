package sf.examples;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

import sf.examples.annotations.Car;
import sf.examples.annotations.New;
import sf.examples.annotations.News;

public class AnnotationTest {

	@Test
	public void multipleAnnotations(){
		Car newCar = new Car();
		Annotation[] a = newCar.getClass().getAnnotations();
		int topLevelAnnotationCount = 0;
		int newAnnotationCount = 0;
		for(Annotation annotation : a){
			System.out.println(annotation);
			topLevelAnnotationCount++;
			
			if(annotation instanceof News){
				News newsAnnotation = (News) annotation;
				for(New newAnnotation : newsAnnotation.value()){
					newAnnotationCount++;
				}
			}
		}
		
		assertEquals(1, topLevelAnnotationCount);
		assertEquals(4, newAnnotationCount);
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
