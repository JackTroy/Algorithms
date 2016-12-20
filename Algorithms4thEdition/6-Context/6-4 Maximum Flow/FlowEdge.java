
public class FlowEdge {
	private int v,w;
	private final double capacity;
	private double flow;
	public FlowEdge(int v,int w,double capacity){
		this.capacity = capacity;
		this.v=v;
		this.w=w;
		this.flow=0.0;
	}
	public FlowEdge(int v,int w,double capacity,double flow){
		this(v, w, capacity);
		this.flow=flow;
	}
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	public double capacity(){
		return capacity;
	}
	public double flow(){
		return flow;
	}
	public int other(int vertex){
		if(vertex==v)		return w;
		else if(vertex==w)	return v;
		else throw new IllegalArgumentException("invalid endpoint");	
	}
	//below two functions is the key to solve residual network
	//first is to search augmenting path
	public double residualCapaity(int vertex){
		if(vertex==v)		return flow;			//reverse edge
		else if(vertex==w)	return capacity-flow;	//positive edge
		else throw new IllegalArgumentException("invalid endpoint");
	}
	//second is to modify residual network
	public void addResidualFlowTo(int vertex,double delta){
		if(vertex==v)		flow-=delta;
		else if(vertex==w)	flow+=delta;
		else throw new IllegalArgumentException("invalid endpoint");
	}
	public String toString() {
        return v + "->" + w + " " + flow + "/" + capacity;
    }
}
