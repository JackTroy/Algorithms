import java.util.Scanner;
//uva 1590
public class IPNetworks {
	public static int[] strToInt(String ip){
		int[] ipInt = new int[4];
		for(int k=0;k<4;k++){
			String intStr=null;
			if(k==3)
				intStr=ip;
			else{ 
				int pos = ip.indexOf('.');
				intStr = ip.substring(0,pos);
				ip = ip.substring(pos+1);
			}
			ipInt[k]=Integer.parseInt(intStr);
		}
		return ipInt;
	}
	public static int compare(int[] ip1,int[] ip2){
		for(int i=0;i<4;i++)
			if(ip1[i]>ip2[i])		return 1;
			else if(ip1[i]<ip2[i])	return -1;
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			int[] max = {0,0,0,0},min = {255,255,255,255};
			for(int i=0;i<n;i++){
				int[] tmp = strToInt(in.next());
				if(compare(tmp, max)==1)	max = tmp.clone();
				if(compare(tmp, min)==-1)	min = tmp.clone();
			}
			/*
			for(int i:max)
				System.out.print(i);
			System.out.println();
			for(int i:min)
				System.out.print(i);
			*/
			int posGap;
			for(posGap=0;posGap<4;posGap++)
				if(min[posGap]!=max[posGap])	break;
				else if(posGap==3)				break;	
			
			String maxClip=Integer.toBinaryString(max[posGap]);
			String minClip=Integer.toBinaryString(min[posGap]);
			while(maxClip.length()<8){
				maxClip='0'+maxClip;
			}
			while(minClip.length()<8){
				minClip='0'+minClip;
			}
			int posBi;
			for(posBi=0;posBi<8;posBi++)
				if(minClip.charAt(posBi)!=maxClip.charAt(posBi))
					break;
			int[] mask = new int[]{0,0,0,0};
			int[] netAdd = new int[]{0,0,0,0};
			for(int i=0;i<posGap;i++){
				mask[i]=255;
				netAdd[i] = max[i];
			}
			for(int i=8-posBi;i<8;i++){
				mask[posGap]+=Math.pow(2, i);
			}	
			//use incredible bitwise operation
			netAdd[posGap]=max[posGap]&mask[posGap];
			//System.out.println();
			for(int i=0;i<4;i++){
				if(i>0)	System.out.print('.');
				System.out.print(netAdd[i]);
			}
			System.out.println();
			for(int i=0;i<4;i++){
				if(i>0)	System.out.print('.');
				System.out.print(mask[i]);
			}
			System.out.println();
			//System.out.println(maxClip);
			//System.out.println(minClip);
		}
		in.close();
	}

}
