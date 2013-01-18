package network3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Network {

	private Node[] inputNodes;
	private InputLayer inputLayer;
	private ALayer[] layers;
	private String inputFilePath;
	
	
	public static double lambda=0.99;
	public static double theta=0.2;
	public static int verbosity=2;
	
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

	public void run(){
		System.out.println("starting new run========================");
		try {
			FileReader reader= new FileReader(inputFilePath);
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
				
				
				
				try {symbol=reader.read();
				} catch (IOException e) {e.printStackTrace();}
			}
			
			reader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetState(){
		for (ALayer layer : layers) {
			layer.resetStates();
		}
	}



	

	public static void main(String[] args) {
		
		System.out.println("starting...");
		Network n= new Network(inputRange,1);
		n.setInputFilePath("testFile1");
		int numRuns=1;
		
		for (int i = 0; i < numRuns; i++) {
			n.run();
		}
		
//		ArrayList<Double[]> data=n.inputLayer.featureObjectData();
//		for (Double[] doubles : data) {
//			System.out.println((char)Math.round(doubles[0])+"   "+Math.round(doubles[1])+"   "+doubles[2]);
//		}
		


	}

}
