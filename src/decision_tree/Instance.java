package decision_tree;

import java.util.Arrays;

public class Instance {
	boolean[] ins;

	public Instance(int size) {
		ins = new boolean[size];
	}

	@Override
	public String toString() {
		return "Instance [ins=" + Arrays.toString(ins) + "]";
	}
}
