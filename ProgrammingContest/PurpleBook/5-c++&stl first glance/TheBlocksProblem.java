import java.util.ArrayList;
import java.util.Scanner;

public class TheBlocksProblem {
	//uva 101
	public static void clear(ArrayList<Integer>[] world,int n){
		int[] pos = find(world, n);
		for(int i=pos[1]+1;i<world[pos[0]].size();i++){
			int tmp = world[pos[0]].get(i);
			world[tmp].add(tmp);
		}
		for(int i=world[pos[0]].size()-1;i>pos[1];i--)
			world[pos[0]].remove(world[pos[0]].size()-1);
	}
	public static void move(ArrayList<Integer>[] world,int[] a,int[] b){
		for(int i = a[1];i<world[a[0]].size();i++)
			world[b[0]].add(world[a[0]].get(i));
		for(int i=world[a[0]].size()-1;i>=a[1];i--)
			world[a[0]].remove(world[a[0]].size()-1);
	}
	public static int[] find(ArrayList<Integer>[] world,int n){
		int[] pos = new int[2];
		for(int i=0;i<world.length;i++){
			int j = world[i].indexOf(n);
			if(j!=-1){
				pos[0]=i;
				pos[1]=j;
				break;
			}
		}
		return pos;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Integer>[] world =(ArrayList<Integer>[]) new ArrayList[n];
		for(int i=0;i<n;i++){
			world[i] = new ArrayList<Integer>();
			world[i].add(i);
		}
		while(in.hasNext()){
			int a,b;
			String s1,s2;
			s1 = in.next();
			if(s1.equals("quit"))	break;
			a = in.nextInt();
			s2 = in.next();
			b = in.nextInt();
			if(find(world,a)[0]==find(world, b)[0])	continue;
			if(s1.equals("move"))	clear(world, a);
			if(s2.equals("onto"))	clear(world, b);
			move(world, find(world, a), find(world, b));
			/*these code could be simplified
			 * 			switch (mark) {
			case 0:{
				clear(world, a);
				clear(world, b);
				move(world, find(world, a), find(world, b));
				break;
			}
			case 1:{
				clear(world, a);
				move(world, find(world, a), find(world, b));
				break;
			}
			case 2:{
				clear(world, b);
				move(world, find(world, a), find(world, b));
				break;
			}
			case 3:{
				move(world, find(world, a), find(world, b));
				break;
			}
			}
			*/
		}
		//notice space lie in front of number
		for(int i=0;i<world.length;i++){
			System.out.print(i+":");
			if(!world[i].isEmpty())
				for(int e:world[i])
					System.out.print(" "+e);
			System.out.println();
		}
	}

}
