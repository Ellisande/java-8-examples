package sf.examples.ball;

public interface Ball {
	default int getBounceHeight(int force){
		return force * 2;
	}
}
