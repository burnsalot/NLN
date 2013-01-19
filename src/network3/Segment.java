package network3;

import java.util.ArrayList;

public class Segment extends AAddressable{
//	private ArrayList<Node> nodes;
	private ArrayList<IAddressable> targets;
	private int activePredecessors;
	private int threshold;
	
	public Segment(int threshold){
		this.threshold=threshold;
		this.targets=new ArrayList<IAddressable>();
	}
	
	public void addTarget(IAddressable target){
		targets.add(target);
	}
	
	
	
	
	public void update(){
		boolean state = activePredecessors>=threshold;
		activePredecessors=0;
		if (state){
			for (IAddressable target : targets) {
				target.address();
			}
		}
	}

	@Override
	public void address() {
//		adressed=true;
		activePredecessors++;
	}
	
	
	
		

}
