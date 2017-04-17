import java.util.Comparator;
import java.util.PriorityQueue;
//uva 136
public class UglyNumber {

	public static void main(String[] args) {
		Comparator<Long> LongDes = new Comparator<Long>() {
			public int compare(Long o1, Long o2) {
				return 0;
			}
		};
		PriorityQueue<Long> pq = new PriorityQueue<Long>(LongDes);
		pq.add((long) 1);
		for(int i=1;i<=1500;i++){
			long n = pq.remove();
			if(i==1500) System.out.println("The 1500'th ugly number is "+n+".");
			else{
				if(!pq.contains(n*2))	pq.add(n*2);
				if(!pq.contains(n*3))	pq.add(n*3);
				if(!pq.contains(n*5))	pq.add(n*5);
			}
		}
		
	}

}
