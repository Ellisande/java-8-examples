package sf.examples.ball;

public interface HardBall {
	default int getBounceHeight(int force){
		return force * 0;
	}
}
