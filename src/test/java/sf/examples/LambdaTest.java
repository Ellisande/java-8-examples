package sf.examples;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import sf.examples.lambda.CallBacker;
import sf.examples.lambda.IntToStringTransformer;
import sf.examples.lambda.MutableCar;
import sf.examples.lambda.PrintValues;

public class LambdaTest {

	@Test
	public void forEachClosure() {
		Map<String, String> testMap = new HashMap<>();

		testMap.putIfAbsent("key1", "value1");
		testMap.putIfAbsent("key1", "value1");
		testMap.putIfAbsent("key2", "value2");

		System.out.println(testMap);

		testMap.forEach((key, value) -> {
			System.out.println("My key " + key + " My value " + value);
		});
	}
	
	@Test
	public void forEachStandard() {
		Map<String, String> testMap = new HashMap<>();

		testMap.putIfAbsent("key1", "value1");
		testMap.putIfAbsent("key1", "value1");
		testMap.putIfAbsent("key2", "value2");

		System.out.println(testMap);

		testMap.forEach(new PrintValues<>());
	}

	@Test
	public void callback() {
		CallBacker callback = new CallBacker();
		callback.waitAndInvoke(sayWhat -> 
			System.out.println("I said " + sayWhat));
	}

	@Test
	public void storeAclosure() {
		Consumer<String> callback = (sayWhat) -> {
			System.out.println("I said "+ sayWhat);
		};

		callback.accept("Weird stuff");
	}

	@Test
	public void comapreIt() {
		List<Integer> strings = new ArrayList<>();
		strings.add(1);
		strings.add(2);
		strings.add(3);

		strings.sort((left, right) -> {
			if (left > right)
				return -1;
			if (left == right)
				return 0;
			return 1;
		});

		System.out.println(strings);
	}

	@Test
	public void ansyc() throws InterruptedException {
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(2);
		ints.add(3);

		List<Integer> doubleList = new ArrayList<>();

		Consumer<List<Integer>> callback = (list) -> {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}

			doubleList.addAll(ints);
			doubleList.addAll(ints);
		};
		
		Consumer<List<Integer>> add1000Callback = (list) -> {
			doubleList.add(1000);
		};

		System.out.println("Intitial State: "+doubleList);
		
		
		Runnable doubleTheList = () -> callback.andThen(add1000Callback).accept(ints);
		
		Thread thread = new Thread(doubleTheList);
		thread.run();
		System.out.println("Intermidiate State"+doubleList);
		thread.join();

		System.out.println("Finished state " + doubleList);
	}

	@Test
	public void functionalCallbacks() {
		MutableCar car = new MutableCar();

		car.goFoward(5, (position) -> {
			System.out.println("I'm currently at " + position);
		});

		car.goFoward(5, (position) -> {
			System.out.println("I'm currently at " + position);
		});

	}

	@Test
	public void streaming() {
		List<String> friendsOfMine = new ArrayList<>();
		friendsOfMine.add("MWM");
		friendsOfMine.add("Brad");
		friendsOfMine.add("Mike");
		friendsOfMine.add("Matt");
		friendsOfMine.add("Mike");
		friendsOfMine.add("Steve");
		friendsOfMine.add("Singh");

		List<String> result = friendsOfMine.stream()
				.filter((friend) -> !friend.equals("MWM")).distinct().sorted()
				.collect(Collectors.toList());

		System.out.println(friendsOfMine);
		System.out.println(result);
	}

	@Test
	public void lambdaInlineScope() {
		String hello = "Hello";

		Function<String, String> function = (inputString) -> {
			// hello = "new world";
			String world = "World";

			return hello + inputString + world;
		};

		// System.out.println(world);

		System.out.println(function.apply(hello));

		// hello = "New World";

		System.out.println(function.apply(hello));

	}

	@Test
	public void lambdaEncapsulatedScope() {
		MutableCar car = new MutableCar();
		assertEquals("large", car.getEngine());

		Function<String, String> stringBuilder = (inputString) -> {
			return car.getEngine() + inputString;
		};

		System.out.println(stringBuilder.apply(" and in charge"));

		car.setEngine("small");

		assertEquals("small", car.getEngine());

		System.out.println(stringBuilder.apply(" and weak"));
	}

	@Test
	public void lambdaThis() {
		IntToStringTransformer transformer = new IntToStringTransformer();
		Function<Integer, String> intToString = transformer.getTransformer();

		int one = 1;

		String result = intToString.apply(one);

		System.out.println(result);
	}
}
