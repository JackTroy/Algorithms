
public class DirectedEdge implements Comparable<DirectedEdge>{
	private final int v,w;//v->w
	private final double weight;
	public DirectedEdge(int v,int w,double weight) {
		// TODO Auto-generated constructor stub
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	public double weight() {
		return weight;
	}
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	public int compareTo(DirectedEdge that){
		return Double.compare(this.weight, that.weight);
	}
	public String toString(){
		return String.format("%d-%d %.2f", v,w,weight);
	}
}
