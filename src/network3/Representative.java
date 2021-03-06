package network3;

import java.util.ArrayList;

public class Representative extends AAddressable implements Comparable{
	public ArrayList<Segment> segments;
//	public Node verticalOutput; //points to higher layer
	public Node horizontalOutput; //points to same layer
	
	public boolean isActive;
	private ALayer embeddingLayer;
	public Feature represented;
	public double frequency;
	
	public Representative(ALayer embeddingLayer, Feature represented){
		this.represented=represented;
		this.segments=new ArrayList<Segment>();
//		if (embeddingLayer.previouslyActiveNodes.size()>0){
//			Segment segment=new Segment(1);
//			for (Node node : embeddingLayer.previouslyActiveNodes) {
//				node.addTarget(segment);
//			}
//		}
		
		this.embeddingLayer=embeddingLayer;		
		this.embeddingLayer.representatives.add(this);
//		this.verticalOutput=new Node();
		this.horizontalOutput=new Node();
//		this.embeddingLayer.currentlyActiveNodes.add(this.horizontalOutput);
	}
	
	public void reinforce(){
		if (embeddingLayer.previouslyActiveNodes.size()>0){
			Segment segment=new Segment(1);
			segment.addTarget(this);
			for (Node node : embeddingLayer.previouslyActiveNodes) {
				node.addTarget(segment);
			}
//			if (Network.verbosity>1){
//				System.err.println("new lateral segment created");
//			}
			this.segments.add(segment);
			this.embeddingLayer.lateralSegments.add(segment);
		}
//		this.embeddingLayer.currentlyActiveNodes.add(this.horizontalOutput);
//		return this;
	}
	
	public void force(){
		this.embeddingLayer.currentlyActiveNodes.add(this.horizontalOutput);
		reinforce();
		horizontalOutput.activate();
	}

	@Override
	//received lateral input
	public void address() {
		embeddingLayer.predictedRepresentatives.add(this);
		addressed=true;
	}

//	public boolean updateActivation(boolean parentAdressed) {
//		isActive=parentAdressed&&this.adressed;
//		
//		if (isActive) {
//			embeddingLayer.currentlyActiveNodes.add(horizontalOutput);
//			horizontalOutput.activate();
////			frequency=Network.lambda*frequency+(1-Network.lambda);
//		}
//		
//		
//		adressed=false;				
//		return isActive;
//	}

	@Override
//	public int compareTo(Object other) {
//		Representative o=(Representative) other;
//		if (o.frequency>this.frequency) {
//			return -1;
//		} else if (o.frequency<this.frequency){
//			return 1;
//		} else {
//			return 0;
//		}
//	}
	
//	public int compareTo(Object other) {
//		Representative o=(Representative) other;
//		if (o.relativeFrequency()>this.relativeFrequency()) {
//			return -1;
//		} else if (o.relativeFrequency()<this.relativeFrequency()){
//			return 1;
//		} else {
//			return 0;
//		}
//	}
	public int compareTo(Object other) {
		Representative o=(Representative) other;
		if (o.score()>this.score()) {
			return -1;
		} else if (o.score()<this.score()){
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public void remove(){
		
	}
	
	//probability of activation given parent activation
	public double relativeFrequency(){
		return represented.frequency*this.frequency;
	}
	
	//representatives with high accuracy and high fan out might be focal points
	public double score(){
		return this.horizontalOutput.targets.size()*frequency;
	}
	
	
	public char semanticValue(){
		return represented.semanticValue();
	}
	
	public boolean updateActivation() {
		isActive=false;
		if (this.addressed){
			if (represented.addressed){
				isActive=true;
				embeddingLayer.currentlyActiveNodes.add(horizontalOutput);
				frequency=Network.lambda*frequency+(1-Network.lambda);
				horizontalOutput.activate();
			} else {				
				frequency=Network.lambda*frequency;
			}
		} else {

		}
//		isActive=false;
//		if (represented.addressed){
//			if (this.addressed){
//				isActive=true;
//				embeddingLayer.currentlyActiveNodes.add(horizontalOutput);
//				frequency=Network.lambda*frequency+(1-Network.lambda);
//				horizontalOutput.activate();
//			} else {
//				frequency=Network.lambda*frequency;
//			}			
//		} else {
//			System.out.println("parent not addressed");
//		}
		addressed=false;		
		return isActive;
	}
	
//	public boolean updateActivation(boolean parentAdressed) {
//		isActive=false;
//		if (parentAdressed){
//			if (this.adressed){
//				isActive=true;
//				embeddingLayer.currentlyActiveNodes.add(horizontalOutput);
//				frequency=Network.lambda*frequency+(1-Network.lambda);
//				horizontalOutput.activate();
//			} else {
//				frequency=Network.lambda*frequency;
//			}			
//		} else {
//			
//		}
//		adressed=false;		
//		return isActive;
//	}
	
}
