package four;

public class GenericsExample<X> {

	public static <T> void catchBall(T t, X x) { 	// static reference to non-static type X error
	}

	public <T> void dribbleBall(T t, X x) { 		// Compiled
	}

	public <X> static void fetchBall(X t, X x) {	 // Order is Wrong - Static <X>
	}

	public <X> void inflateBall(X t, X x) { 		// Compiled
	}

	public <T> static void spinBall(T t, X x) {		// Order is Wrong - Static <T>
	}

	public static <X> void throwBall(X t, X x) { 	// All References to Static Reference is the Same
	}
}
