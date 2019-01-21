package decision_tree;

public class Attr implements Comparable <Attr>{
	double ig;
	String name;
	int index;
	
	public Attr(String name, int index) {
		this.ig = 0;
		this.name = name;
		this.index = index;
	}

	@Override
	public int compareTo(Attr o) {
		return ig > o.ig? 1: 0;
	}

	@Override
	public String toString() {
		return "Attr [name=" + name + ", "+ index +"]";
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Attr) {
			Attr k = (Attr) obj;
			return name.equals(k.name);
		}
		return super.equals(obj);
	}
	
	
}
