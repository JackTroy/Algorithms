import edu.princeton.cs.algs4.Queue;

public class TopologicalX {
	private Queue<Integer> order;
	private int[] ranks; 
	public TopologicalX(Digraph G) {
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }
        ranks = new int[G.V()];
        order = new Queue<Integer>();
        int count=0;
        Queue<Integer> queue = new Queue<Integer>();
        for(int v=0;v<G.V();v++)
        	if(indegree[v]==0)
        		queue.enqueue(v);
        while(!queue.isEmpty()){
        	int v = queue.dequeue();
        	order.enqueue(v);
        	ranks[v]=count++;
        	for(int w:G.adj(v)){
        		indegree[w]--;
        		if(indegree[w]==0)
        			queue.enqueue(w);
        	}
        }
        
        if(count!=G.V())
        	order=null;
	}
	public Iterable<Integer> order() {
		return order;
	}
	public boolean hasOrder(){
		return order!=null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
