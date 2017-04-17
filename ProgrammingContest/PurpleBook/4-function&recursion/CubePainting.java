import java.util.Scanner;

public class CubePainting {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			boolean is=false;
			String input = in.next();
			String a = input.substring(0, 6);
			String b = input.substring(6,12);
			
			int[][] indexs = {{1,2,3,4,5,6},{2,6,3,4,1,5},{6,5,3,4,2,1},{5,1,3,4,6,2},{3,2,6,1,5,4},{4,2,1,6,5,3}};
			for(int i=0;i<6;i++){
				char[] tmp = new char[6];
				for(int j=0;j<6;j++)
					tmp[j] = a.charAt(indexs[i][j]-1);
				for(int k=0;k<4;k++){
					char hold = tmp[1];
					tmp[1]=tmp[3];
					tmp[3]=tmp[4];
					tmp[4]=tmp[2];
					tmp[2]=hold;
					if(String.valueOf(tmp).equals(b))	{is=true;break;}
				}
				if(is==true)	break;	
			}
			if(is)	System.out.println("TRUE");
			else	System.out.println("FALSE");
		}
		in.close();
	}

}
