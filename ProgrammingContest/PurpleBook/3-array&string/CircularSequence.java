import java.util.Scanner;

public class CircularSequence {
	//uva 1584
	private static boolean less(String input,int i,int min) {
		int len = input.length();
		for (int j = 0; j < len; j++){
			int indexI=(i+j)%len;
			int indexMin = (min+j)%len;
			if(input.charAt(indexI)!=input.charAt(indexMin)) 
				return input.charAt(indexI)<input.charAt(indexMin);
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int round = in.nextInt();
		while(round-->0){
			String input = in.next();
			int min=0,len=input.length();;
			for(int i=1;i<len;i++)
				if(less(input,i,min)==true)
					min=i;
			System.out.println(input.substring(min,len)+input.substring(0, min));
		}
		in.close();
	}

}
