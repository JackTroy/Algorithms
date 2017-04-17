import java.util.HashMap;
import java.util.Scanner;

public class Database {
//uva 1592
	private static void check(int[][] db,int col,int row){
		for(int c1=0;c1<col;c1++){
			for(int c2=c1+1;c2<col;c2++){
				HashMap<String,Integer> map = new HashMap<String,Integer>();
				for(int r=0;r<row;r++){
					String pair = String.format("%d,%d", db[r][c1], db[r][c2]);
					if(map.containsKey(pair)){
						System.out.println("NO");
						System.out.print(String.format("%d %d\n%d %d\n",map.get(pair)+1,r+1,c1+1,c2+1));
						return;
					}
					else	map.put(pair,r);
				}
			}
		}
		System.out.println("YES");
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int row = in.nextInt(),col = in.nextInt();
			in.nextLine();
			int[][] db = new int[row][col];
			int cnt=0;
			for(int r=0;r<row;r++){
				String line = in.nextLine();
				String[] elems = line.split(",");
				for(int c=0;c<col;c++){
					if(map.containsKey(elems[c]))	db[r][c]=map.get(elems[c]);
					else{
						map.put(elems[c], cnt);
						db[r][c] = cnt++;
					}
				}
			}
			if(row==1||col==1)	{
				System.out.println("YES");
				continue;
			}
			check(db, col, row);
		}
		in.close();	
	}

}
