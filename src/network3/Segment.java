package network3;

import java.util.ArrayList;

public class Segment extends AAdressable{
//	private ArrayList<Node> nodes;
	private ArrayList<IAdressable> targets;
	private int activePredecessors;
	private int threshold;
	
	public Segment(int threshold){
		this.threshold=threshold;
		this.targets=new ArrayList<IAdressable>();
	}
	
	public void addTarget(IAdressable target){
		targets.add(target);
	}
	
	
	
	
	public void update(){
		boolean state = activePredecessors>=threshold;
		activePredecessors=0;
		if (state){
			for (IAdressable target : targets) {
				target.adress();
			}
		}
	}

	@Override
	public void adress() {
//		adressed=true;
		activePredecessors++;
	}
	
	
	
		

}
