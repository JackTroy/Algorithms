import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrinterQueue {
//uva 12100
	public static void main(String[] args) {
		Comparator<Integer> des = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return -Integer.compare(o1, o2);
			}
		};
		Scanner in = new Scanner(System.in);
		int rnd = in.nextInt();
		for(int r=0;r<rnd;r++){
			int n = in.nextInt(),pos = in.nextInt(),time = 0;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(des);
			for(int i=0;i<n;i++){
				int job = in.nextInt();
				queue.add(job);
				pq.add(job);
			}
			while(true){
				if(queue.getFirst()<pq.peek()){
					queue.addLast(queue.pollFirst());
					if(pos==0)	pos = queue.size()-1;
					else		pos--;
				}
				else if(queue.getFirst()==pq.peek()){
					queue.pollFirst();
					pq.remove();
					time++;
					if(pos==0)	break;
					else		pos--;
				}
			}
			System.out.println(time);
		}
		in.close();
	}
}
