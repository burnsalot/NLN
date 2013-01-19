package network3;

import java.util.ArrayList;

public abstract class ALayer {

//	private int numResources;
//	private ALayer next;
//	private double lambda;
	
	
	protected Feature[] features;
	protected ArrayList<Segment> bottomUpSegments;
	protected ArrayList<Segment> lateralSegments;
	protected ArrayList<Node> previouslyActiveNodes;
	protected ArrayList<Node> currentlyActiveNodes;	
	protected ArrayList<Node> outputNodes;
	
	protected ArrayList<Representative> predictedRepresentatives;
	protected ArrayList<Representative> representatives;
	
	public void resetStates(){
		this.previouslyActiveNodes=new ArrayList<Node>();
		this.currentlyActiveNodes=new ArrayList<Node>();
		for (Feature feature : features) {
			for (Representative representative : feature.representatives) {
				representative.isActive=false;
				representative.addressed=false;
			}
		}
	}
	
}
