package decision_tree;

import com.opencsv.*;
import java.io.*;
import java.util.*;

public class Read {
	List<Instance> data = null;
	List<Attr> attr = null;
	
	public Read () {
		data = new ArrayList<Instance> ();
		attr = new ArrayList<Attr> ();
	}
	
	public void read (String csvPath) {
		String csvFile = csvPath;
		CSVReader reader = null;
		String [] line;
		try {
			reader = new CSVReader (new FileReader (csvFile));
			
//			Read the title and build the attrNameMap
			line = reader.readNext();
			for (int i=0; i<line.length-1; i++) {
				attr.add(new Attr (line [i], i));
				AttrNameMap.attrMap.put(line[i], i);
			}
			
//			Read the data set
            while ((line = reader.readNext()) != null) {
            	Instance a = new Instance(line.length);
            	for (int i=0; i<line.length; i++) {
            		if (line[i].equals("1")) {
            			a.ins[i] = true;
            		} else {
            			a.ins[i] = false;
            		}
            	}
            	data.add(a);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		
		String path = args[0];
		Read r = new Read ();
		r.read(path);
		Read r2 = new Read ();
		r2.read(args[1]);
		Read r3 = new Read ();
		r3.read(args[2]);
		
		Tree tree = new Tree ();
		Prune p = new Prune ();
		ID3 run = new ID3 ();
		run.runID3(r.data, r.attr, tree.root);
		tree.printTree(tree.root);
		
		Node newRoot = p.prune(tree.root, r2.data, args[3], 1000);
		
		System.out.println("Pre-Pruned	Accuracy");
		System.out.println("----------------------");
		System.out.println("Number of training instances = " + r.data.size());
		System.out.println("Number of training attributes = " + r.attr.size());
		System.out.println("Total number of nodes in the tree = " + tree.root.getLeaf(tree.root));
		System.out.println("Number of leaf nodes in the tree = " + tree.root.getNodeNum(tree.root));
		System.out.println("Accuracy of the model on the training dataset	= " + p.getAccuracy(r.data, tree.root));
		System.out.println(" ");
		System.out.println("Number of validation instances = " + r2.data.size());
		System.out.println("Number of validation attributes = " + r2.attr.size());
		System.out.println("Accuracy of the model on the validation dataset before pruning = " + p.getAccuracy(r2.data, tree.root));
		System.out.println(" ");
		System.out.println("Number of testing instances = " + r3.data.size());
		System.out.println("Number of testing	attributes	=" + r3.attr.size());
		System.out.println("Accuracy of the model on the testing dataset =" + p.getAccuracy(r3.data, tree.root));
		//tree.printTree(tree.root);
		
		System.out.println("Post-Pruned	Accuracy");
		System.out.println("----------------------");
		System.out.println("Number of training instances = " + r.data.size());
		System.out.println("Number of training attributes = " + r.attr.size());
		System.out.println("Total number of nodes in the tree = " + newRoot.getLeaf(newRoot));
		System.out.println("Number of leaf nodes in the tree = " + newRoot.getNodeNum(newRoot));
		System.out.println("Accuracy of the model on the training dataset	= " + p.getAccuracy(r.data, newRoot));
		System.out.println(" ");
		System.out.println("Number of validation instances = " + r2.data.size());
		System.out.println("Number of validation attributes = " + r2.attr.size());
		System.out.println("Accuracy of the model on the validation dataset after pruning = " + p.getAccuracy(r2.data, newRoot));
		System.out.println(" ");
		System.out.println("Number of testing instances = " + r3.data.size());
		System.out.println("Number of testing	attributes	=" + r3.attr.size());
		System.out.println("Accuracy of the model on the testing dataset =" + p.getAccuracy(r3.data, newRoot));
		
		//tree.printTree(newRoot);
	}
}
