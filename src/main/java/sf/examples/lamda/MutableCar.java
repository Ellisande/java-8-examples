package sf.examples.lamda;

import java.util.function.Consumer;

public class MutableCar {
	private String engine = "large";
	private String color = "blue";
	private int distance = 0;
	private int speed = 1;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}
	
	public void goFoward(int distanceToMove, Consumer<Integer> onArrival){
		for(int i = 0; i < distanceToMove; i++){
			moveOneUnit();
		}
		
		Consumer<Integer> honk = this::honk;
		honk.andThen(onArrival).accept(distance);
	}
	
	private Integer moveOneUnit(){
		return distance += speed;
	}
	
	private void honk(Integer position){
		System.out.println("Honked at: "+position);
	}
	
	
}
