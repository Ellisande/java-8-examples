package sf.examples.lamda;

import java.util.function.Consumer;

public class CallBacker {

	public void waitAndInvoke(Consumer<String> callback){
		try {
			Thread.sleep(1000);
			callback.accept("World");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
