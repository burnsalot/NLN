package network3;

import java.util.ArrayList;

public class Node {
//	public boolean isActive;
	public ArrayList<IAdressable> targets;
	public int id;
	
	public Node(){
		this.targets=new ArrayList<IAdressable>();
		this.id=Network.numNodes;
		Network.numNodes++;
	}
	
	public void activate(){		
//		if (Network.verbosity>2){
//			System.out.println("Node has been activated");
//		}
		for (IAdressable adressable : targets) {
			adressable.adress();
		}
	}
	
	public void addTarget(IAdressable target){
		targets.add(target);
	}
	
	public String toString(){
		return Integer.toString(id);
	}

}
