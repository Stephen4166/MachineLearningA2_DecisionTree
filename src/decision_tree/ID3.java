package decision_tree;

import java.util.*;

public class ID3 {
	
//	Compute the entropy of the given dataSet
	public double getEntropy (List<Instance> dataSet) {
		int positive = 0, total = dataSet.size();
		
		for (int i=0; i<total; i++) {
			if (dataSet.get(i).ins [dataSet.get(i).ins.length-1]) {
				positive ++;
			}
		}
		
		double posi = (double)positive/(double)total;
		double nega = 1 - posi;
		double entropy;
		if (posi == 0 || posi == 1) {
			entropy = 0;
		} else {
			entropy = - posi * Math.log(posi) / Math.log(2) - nega * Math.log(nega) / Math.log(2);
		}
		return entropy;
	}
	
//	recursively run the ID3 algorithm
	public void runID3 (List<Instance> dataSet, List<Attr> attrs, Node node) {
		if (dataSet == null) {
			System.out.println("dataSet error!");
			return;
		}
		int numPosi = 0;
		for (Instance i : dataSet) {
			if (i.ins[i.ins.length-1]) {
				numPosi ++;
			}
		}
		if (numPosi == dataSet.size()) {
			node.name = "1";
			return;
		} else if (numPosi == 0) {
			node.name = "0";
			return;
		} else if (attrs.isEmpty()) {
			if ((double)numPosi / (double)dataSet.size() > 0.5) {
				node.name = "1";
				return;
			} else {
				node.name = "0";
				return;
			}
		} else {
			
			double sEntropy = getEntropy (dataSet);
			
			double maxIG = 0;
			Attr chosenAttr = null;
			List<Instance> posiData = null, negaData = null;
			
			for (int i=0; i<attrs.size(); i++) {
				List<Instance> tempPosi = new ArrayList<Instance> (), tempNega = new ArrayList<Instance> ();
				
				for (Instance j : dataSet) {
					if (j.ins [attrs.get(i).index]) {
						tempPosi.add(j);
					} else {
						tempNega.add(j);
					}
				}
				
				double p = (double)tempPosi.size() / (double)dataSet.size();
				double tempIG;
				if (p == 0 || p == 1) {
					tempIG = sEntropy;
				} else {
					tempIG = sEntropy - p * getEntropy (tempPosi) - (1-p) * getEntropy (tempNega);
				}
				
				
				if (tempIG >= maxIG) {
					maxIG = tempIG;
					posiData = tempPosi;
					negaData = tempNega;
					chosenAttr = attrs.get(i);
				}
			}
			List<Attr> newAttrs = new ArrayList<Attr> (attrs);

			newAttrs.remove(chosenAttr);
			node.name = chosenAttr.name;
			node.left = new Node (node.depth + 1);
			node.right = new Node (node.depth + 1);
			runID3 (posiData, newAttrs, node.left);
			runID3 (negaData, newAttrs, node.right);
		}
		
	}
	
	
}
