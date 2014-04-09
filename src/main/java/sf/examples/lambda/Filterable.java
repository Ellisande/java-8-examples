package sf.examples.lambda;

@FunctionalInterface
public interface Filterable<T> {
	void filter(T item);
}
