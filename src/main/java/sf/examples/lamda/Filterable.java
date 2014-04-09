package sf.examples.lamda;

@FunctionalInterface
public interface Filterable<T> {
	void filter(T item);
}
