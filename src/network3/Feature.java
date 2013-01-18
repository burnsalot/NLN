package network3;

import java.util.ArrayList;

public class Feature extends AAdressable{
	public double frequency;
	private ALayer embeddingLayer;
	public ArrayList<Representative> representatives;
	public int id;

	public Feature(ALayer embeddingLayer){
		this.id=Network.numFeatures;
		Network.numFeatures++;
		this.embeddingLayer=embeddingLayer;
		this.representatives=new ArrayList<Representative>();

	}



	@Override
	// recieved bottom up signal
	public void adress() {
		if (Network.verbosity>1){
			System.out.println("Feature has been detected");
		}
		adressed=true;
	}

	public void update(){
		boolean predicted=false;

		if (adressed){
			frequency=Network.lambda*frequency+(1-Network.lambda);
			
			for (Representative representative : representatives) {
				predicted|=representative.updateActivation(this.adressed);
			}

			if (!predicted){
				if (Network.verbosity>1){
					System.out.println("prediction failed, adding new representative");
				}
				
				addRepresentative();
				for (Representative representative : representatives) {
					representative.force();
				}
				
			} else {
				if (Network.verbosity>1){
					System.out.println("prediction successful");
				}
				
			}
		} else {
			frequency=Network.lambda*frequency;
		} 


		
		adressed=false;
	}

	private void addRepresentative(){
		representatives.add(new Representative(embeddingLayer, this).reinforce());
	}
	
	
	//only makes sense for features that were created on initialization, 0-255
	public char semanticValue(){
		return (char)id;
	}


}
