

import edu.princeton.cs.algs4.Queue;

public class Josephus {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Queue<Integer> folks = new Queue<Integer>();
		int n = Integer.parseInt(args[0]);//total amount of folks
		int m = Integer.parseInt(args[1]);//death counting
		Counter counter = new Counter();
		for(int i=0;i<n;i++){
			folks.enqueue(i+1);
		}
		while(!folks.isEmpty()){
			int linch = folks.dequeue();
			counter.increment();
			if(counter.getCount()%m==0){
				System.out.print(linch+" ");
			}
			else{
				folks.enqueue(linch);
			}
		}
	}

}
