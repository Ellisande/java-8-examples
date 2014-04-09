package sf.examples.annotations;

@New
@New
@New
@New
public class Car {

	public void honk(@Volume int volume) {
		System.out.println("Honked at: " + volume);
	}
}
