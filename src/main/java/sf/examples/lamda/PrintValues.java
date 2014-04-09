package sf.examples.lamda;

import java.util.function.BiConsumer;

public class PrintValues<T,U> implements BiConsumer<T, U> {

	@Override
	public void accept(T key, U value) {
		System.out.println("My key " + key + " My value " + value);
	}
	
}
