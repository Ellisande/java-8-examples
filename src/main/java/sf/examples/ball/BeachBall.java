package sf.examples.ball;

public interface BeachBall extends Ball{

	default int getBounceHeight(int force){
		return force * 5;
	}
}
