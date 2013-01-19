package network3;

public class Estimate implements Comparable{
	public char value;
	public double probability;
	
	public Estimate(char value, double probability){
		this.value=value;
		this.probability=probability;
	}

	@Override
	public int compareTo(Object o) {
		Estimate e=(Estimate)o;
		if (this.probability<e.probability){
			return -1;
		} else if (this.probability>e.probability){
			return 1;
		} else {
			return 0;
		}
	}

}
