package network3;

import java.util.ArrayList;

public class Node {
//	public boolean isActive;
	public ArrayList<IAddressable> targets;
	public int id;
	
	public Node(){
		this.targets=new ArrayList<IAddressable>();
		this.id=Network.numNodes;
		Network.numNodes++;
	}
	
	public void activate(){		
//		if (Network.verbosity>2){
//			System.out.println("Node has been activated");
//		}
		for (IAddressable addressable : targets) {
			addressable.address();
		}
	}
	
	public void addTarget(IAddressable target){
		targets.add(target);
		if (target instanceof Segment){
			((Segment)target).connect(this);
		}
	}
	
	public String toString(){
		return Integer.toString(id);
	}

}
