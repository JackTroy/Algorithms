import java.util.Scanner;

public class RepeatingDemicals {
	//uva 202
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int[] digits = new int[3003];
			boolean[] exist = new boolean[3003];
			int[] pos = new int[3003];
			int on = in.nextInt(),d=in.nextInt(),n=on;
			int remainder = n%d;
			int integer = n/d;
			
			int cycleStart = 0;
			int cycleEnd = 0;
			
			for(int i=0;i<3003;i++){
				if(exist[remainder]==true){
					cycleStart = pos[remainder];
					cycleEnd = i;
					break;
				}
				else{
					exist[remainder]=true;
					pos[remainder]=i;
				}
				n = remainder*10;
				remainder = n%d;
				digits[i]=n/d;
			}
			System.out.print(String.format("%d/%d = %d.", on,d,integer));
			for(int i=0;i<cycleStart;i++)
				System.out.print(digits[i]);
			System.out.print('(');
			for(int i=cycleStart;i<cycleEnd&&i<50;i++)
				System.out.print(digits[i]);
			if(cycleEnd>50)
				System.out.print("...");
			System.out.println(')');
			System.out.println("   "+(cycleEnd-cycleStart)+" = number of digits in repeating cycle");
			System.out.println();
		}
		in.close();
	}

}
