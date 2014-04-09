package sf.examples;

import org.junit.Test;

import sf.examples.ball.Ball;
import sf.examples.ball.BeachBall;
import sf.examples.ball.HardBall;
import sf.examples.ball.LeadBeachBall;
import sf.examples.ball.RubberBall;
import static org.junit.Assert.*;

public class DefaultMethodImplementationTest {

	@Test
	public void defaultMethod(){
		Ball rubber = new RubberBall();
		assertEquals(4, rubber.getBounceHeight(2));
	}
	
	@Test
	public void concreteImplementation(){
		HardBall ball = new LeadBeachBall();
		assertEquals(10, ball.getBounceHeight(1));
	}
	
	@Test
	public void anonymousInnerClasses(){
		Ball ball = new Ball() {
		};
		
		Ball beachBall = new BeachBall() {
		};
		
		Ball leadBall = new LeadBeachBall();
		
		assertEquals(2, ball.getBounceHeight(1));
		assertEquals(5, beachBall.getBounceHeight(1));
		assertEquals(10, leadBall.getBounceHeight(1));
	}
}
