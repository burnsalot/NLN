package network3;

import java.awt.DisplayMode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Network {

	private Node[] inputNodes;
	private InputLayer inputLayer;
	private ALayer[] layers;
	private String inputFilePath;



	public static double lambda=0.99;
	public static double theta=0.2;
	public static int verbosity=0;

	//variables changed during runtime
	public static int numNodes=0;
	public static int numFeatures=0;

	public static int inputRange=256;

	public Network(int inputRange, int numLayers){
		this.inputNodes=new Node[inputRange];
		for (int i = 0; i < inputNodes.length; i++) {
			inputNodes[i]=new Node();
		}

		this.inputLayer=new InputLayer(inputNodes);

		this.layers=new ALayer[numLayers];
		this.layers[0]=this.inputLayer;
	}

	public void setInputFilePath(String path){
		this.inputFilePath=path;
	}

	public void run(File inputFile) throws IOException{
		//		System.out.println("starting new run========================");
		FileReader reader= new FileReader(inputFile);
		int symbol=0;
		resetState();
		inputLayer.setInitialState();


		while (symbol!=-1){

			if(verbosity>1){
				System.out.println("symbol read: _"+(char)symbol+"_");
			}

			inputNodes[symbol].activate();
			this.inputLayer.prcocess();

			if (verbosity>1){

				System.out.println(inputLayer.toString());
				System.out.println("========================\n");
			}
			symbol=reader.read();
		}
		reader.close();
	}



	//	public void run(){
	//		System.out.println("starting new run========================");
	//		try {
	//			FileReader reader= new FileReader(inputFilePath);
	//			int symbol=0;
	//			resetState();
	//			inputLayer.setInitialState();
	//			
	//
	//			while (symbol!=-1){
	//				
	//				if(verbosity>1){
	//					System.out.println("symbol read: _"+(char)symbol+"_");
	//				}
	//				
	//				inputNodes[symbol].activate();
	//				this.inputLayer.prcocess();
	//				
	//				if (verbosity>1){
	//					
	//					System.out.println(inputLayer.toString());
	//					System.out.println("========================\n");
	//				}
	//				
	//				
	//				
	//				try {symbol=reader.read();
	//				} catch (IOException e) {e.printStackTrace();}
	//			}
	//			
	//			reader.close();
	//		} catch (FileNotFoundException e1) {
	//			e1.printStackTrace();} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

	public void resetState(){
		for (ALayer layer : layers) {
			layer.resetStates();
		}
	}





	public static void main(String[] args) throws IOException {

		System.out.println("starting...");
		Network n= new Network(inputRange,1);
		//		n.setInputFilePath("testFile1");

		String path="C:/Users/john/Desktop/random";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		int numRuns=3;

		for (int i = 0; i < numRuns; i++) {
			for (File file : listOfFiles) {
				//				n.setInputFilePath(path)+"/"+file.
				System.out.println("Run #"+i+", file: "+file.getName());
				n.run(file);
				System.out.println(n.inputLayer.toString());
			}

		}

		n.displayFeatureObjectData();
		n.displayRepresentativeRanking();
	}
	
	public void displayFeatureObjectData(){
		ArrayList<Double[]> data=this.inputLayer.featureObjectData();
		for (Double[] doubles : data) {
			System.out.println((char)Math.round(doubles[0])+"   "+Math.round(doubles[1])+"   "+doubles[2]);
		}
	}
	
	public void displayRepresentativeRanking(){
		this.inputLayer.rankRepresentatives();
		ArrayList<Representative> representatives =this.inputLayer.representatives;
		Collections.reverse(representatives);
		for (Representative representative : representatives) {
			System.out.println(representative.semanticValue()+"	relative: "+representative.relativeFrequency()+" lokal: "+representative.frequency);
		}
		
	}
}
