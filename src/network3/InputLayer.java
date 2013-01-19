package network3;

import java.util.ArrayList;
import java.util.Collections;

public class InputLayer extends ALayer{
	
	
//	private ArrayList<>
	
	public InputLayer(Node[] inputNodes){
		this.features=new Feature[inputNodes.length];
		this.bottomUpSegments=new ArrayList<Segment>();
		this.lateralSegments=new ArrayList<Segment>();
		this.previouslyActiveNodes=new ArrayList<Node>();
		this.currentlyActiveNodes=new ArrayList<Node>();
		this.representatives=new ArrayList<Representative>();
		this.predictedRepresentatives=new ArrayList<Representative>();
		
		for (int i = 0; i < features.length; i++) {
			features[i]=new Feature(this);
			Segment s = new Segment(1);
			bottomUpSegments.add(s);
			s.addTarget(features[i]);
			inputNodes[i].targets.add(s);	
		}
		
		features[0].representatives.add(new Representative(this, features[0]));
		
	}
	
	public void prcocess(){
		previouslyActiveNodes=currentlyActiveNodes;
		currentlyActiveNodes=new ArrayList<Node>();
		
		processBottomUpSignals();
		
		matchPredictions();
		
//		previouslyActiveNodes=new ArrayList<Node>();
		
		updatePredictions();
		
		
	}
	
	private void processBottomUpSignals(){
		for (Segment segment : bottomUpSegments) {
			segment.update();
		}
	}
	
	private void matchPredictions(){
		for (Feature feature : features) {
			feature.update();
		}
	}
	
	private void updatePredictions(){
		for (Segment segment : lateralSegments) {
			segment.update();
		}
	}
	
	public String toString(){
		int numRepresentatives=0;
		ArrayList<Node> predictive=new ArrayList<Node>();
		for (Feature feature : features) {
			numRepresentatives+=feature.representatives.size();
			for (Representative representative : feature.representatives) {
				if(representative.addressed){
					predictive.add(representative.horizontalOutput);
				}
			}
		}
//		if (currentlyActiveNodes.size()>1){
//			System.err.println("OMG");
//		}
		
		return "#lateral Segments: "+lateralSegments.size()+"	#active Nodes: "+getNodeIDs(currentlyActiveNodes)+"	predicting Nodes: "+getNodeIDs(predictive)+"	#representatives: "+numRepresentatives;
//		return "#lateral Segments: "+lateralSegments.size()+"	#active Nodes: "+currentlyActiveNodes.size()+"	predicting Nodes: "+numPredictive+"	#representatives: "+numRepresentatives;
	}
	
	public void setInitialState(){
		features[0].representatives.get(0).address();
	}
	
	public ArrayList<Double[]> featureObjectData(){
		ArrayList<Double[]> data=new ArrayList<Double[]>();
		
		for (int i = 0; i < features.length; i++) {
			Double[] record={(double)i, (double)features[i].representatives.size(), features[i].frequency};
			data.add(record);
		}
		return data;
	}
	
	public String getNodeIDs(ArrayList<Node> nodes){
		String s="";
		for (Node node : nodes) {
			s+=node.toString()+" ";
		}
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public void rankRepresentatives(){
		Collections.sort(representatives);
	}
	
	//returns list of pairs, estimated char+probability
	public ArrayList<Estimate> estimates(){
		ArrayList<Estimate>  estimates=new ArrayList<Estimate>();
		for (Representative predicted : predictedRepresentatives) {
			estimates.add(new Estimate(predicted.semanticValue(), predicted.frequency));
		}
		return estimates;
	}
	
	public int error(char realValue){
		ArrayList<Estimate> estimates=estimates();
		System.out.println("number of estimates: "+estimates.size());
		Collections.sort(estimates);
		Collections.reverse(estimates);
		
		double predecessorProbability=1;
		boolean success=false;
		int error=0;
		for (Estimate estimate : estimates) {
			if(estimate.value==realValue){
				success=true;
				break;
			} else if (estimate.probability!=predecessorProbability){
				error++;
				//avoid counting errors for estimates of same probability
				predecessorProbability=estimate.probability;
			}
		}
		if (!success){
			error=Integer.MAX_VALUE;
		}
		return error;
	}

	
}
