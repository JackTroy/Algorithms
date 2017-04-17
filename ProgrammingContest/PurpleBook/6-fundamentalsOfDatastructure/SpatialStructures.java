import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SpatialStructures {
	public class Reader {
		public BufferedReader r;
		public StringTokenizer s;
		public Reader(InputStream in){
			r = new BufferedReader(new InputStreamReader(in));
		}
		public String next(){
			if(!hasNext())	return null;
			else 			return s.nextToken();
		}
		public boolean hasNext(){
			if(s==null||!s.hasMoreTokens()){
				String line;
				try	{line = r.readLine();}
				catch(Exception e)	{return false;}
				if(line==null)	return false;
				s = new StringTokenizer(line);
				return true;
			}
			else	return true;
		}
		public int nextInt(){
			return Integer.parseInt(next());
		}
	}
	public int[][] pic;
	public ArrayList<Integer> blacknums;
	public int[] rs = {0,0,1,1},cs = {0,1,0,1};
	public SpatialStructures(){}
	public void solve(){
		Reader in = new Reader(System.in);
		int rnd = 0;
		while(true){
			int n = in.nextInt();	if(n==0)	break;
			if(rnd>0)	
			System.out.println("Image "+(++rnd));
			if(n>0){
				pic = new int[n][n];
				blacknums = new ArrayList<Integer>();
				for(int i=0;i<n;i++){
					String line = in.next();
					//System.out.println(line);
					for(int j=0;j<n;j++)
						pic[i][j] = line.charAt(j)-'0';
				}
					
				scan(0,0,n,"");
				Collections.sort(blacknums);
				int cnt = 0;
				for(int i=0;i<blacknums.size();i++){
					if(cnt%12 != 0)	System.out.print(' ');
					System.out.print(blacknums.get(i));
					if(++cnt%12==0)	System.out.println();
				}
				if(cnt%12!=0)	System.out.println();
				System.out.println("Total number of black nodes = "+cnt);
			}
			else{
				pic = new int[-n][-n];
				while(true){
					int bn = in.nextInt(); if(bn==-1)	break;
					paint(bn,0,0,-n);
				}
				for(int i = 0;i<-n;i++){
					for(int j=0;j<-n;j++)
						if(pic[i][j]==0)	System.out.print('.');
						else				System.out.print('*');
					System.out.println();
				}
			}
		}
	}
	public void scan(int r,int c,int w,String num){
		//System.out.println(num);
		//0 g -1 b 1 w
		boolean black = true,white = true;
		for(int i=r;i<r+w;i++){
			for(int j=c;j<c+w;j++){
				if(pic[i][j]==0)	black = false;
				else				white = false;
				if(!black&&!white)	break;	
			}
			if(!black&&!white)	break;
		}
		if(black)	{blacknums.add(toTen(num));return;}
		if(white)	return;
		for(int i = 0;i<4;i++){
			scan(r+rs[i]*w/2, c+cs[i]*w/2, w/2, (i+1)+num);
		}
	}
	public static int toTen(String num){
		int sum = 0;
		for(int i = 0;i<num.length();i++){
			int factor = num.charAt(num.length()-1-i)-'0';
			//System.out.println(factor);
			sum += factor*Math.pow(5, i);
		}
			
		return sum;
	}
	public void paint(int bn,int r,int c,int w){
		if(bn!=0){
			int dir = bn % 5;
			paint(bn/5, r+rs[dir-1]*w/2, c+cs[dir-1]*w/2, w/2);
		}
		else{
			for(int i = r;i<r+w;i++)
				for(int j=c;j<c+w;j++)
					pic[i][j] = 1;
			return;
		}
	}
	public static void main(String[] args) {
		SpatialStructures a = new SpatialStructures();
		a.solve();
		//System.out.println(toFive(9));
	}

}