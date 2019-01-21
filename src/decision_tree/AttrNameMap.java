package decision_tree;

import java.util.HashMap;

// This is a map for names of attributes and their position in an instance (index);
public class AttrNameMap {
	public static HashMap<String, Integer> attrMap = new HashMap<String, Integer> ();
	public static int getIndex (String name) {
		return attrMap.get(name);
	}
}
