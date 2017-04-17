
public class Subset {
	public static void print(String set,int n){
		System.out.println(set);
		int s = set.length()>0 ? Integer.parseInt(set.charAt(set.length()-1)+"")+1:0;
		for(int i = s; i < n; i++){
			print(set + i, n);
		}
	}
	public static void print2(boolean[] vec, int cur){
		if(cur == vec.length){
			for(int i=0;i<vec.length;i++)
				if(vec[i])	System.out.print(i);
			System.out.println();
		}
		else{
			vec[cur] = true;
			print2(vec, cur+1);
			vec[cur] = false;
			print2(vec, cur+1);
		}
	}
	public static void print3(int n){
		for(int i=0; i < (1<<n); i++){
			for(int j = 0; j < n; j++){
				if((i&1<<j)!=0)
					System.out.print(j);
			}
			System.out.println();
		}
			
	}  
	public static void main(String[] args) {
		//print("",3);
		//print2(new boolean[3],0);
		print3(3);
	}

}
