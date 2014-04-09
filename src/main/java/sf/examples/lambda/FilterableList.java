package sf.examples.lambda;

import java.util.LinkedList;

public class FilterableList<T> extends LinkedList<T> {

	private static final long serialVersionUID = -8445305487861996475L;

	public void filter(Filterable<T> filter) {
		for(T item : this){
			filter.filter(item);
		}
	}

}
