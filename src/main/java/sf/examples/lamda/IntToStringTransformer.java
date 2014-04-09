package sf.examples.lamda;

import java.util.function.Function;

public class IntToStringTransformer {
	
	private String suffix = " - Transformed via lamda ";
	
	public Function<Integer, String> getTransformer(){
		return (inputInteger) -> {
			return inputInteger.toString() + this.suffix + this;
		};
	}
	
	public String toString(){
		return " - 'This' points to IntToStringTransformer";
	}
}
