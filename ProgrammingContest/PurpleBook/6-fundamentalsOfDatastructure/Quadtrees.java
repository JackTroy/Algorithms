import java.util.Scanner;
//uva 297
public class Quadtrees {
	public Scanner in;
	public boolean[][] image;
	public int cnt;
	public int index;
	public Quadtrees(){	
	}
	public void solve(){
		in = new Scanner(System.in);
		int n = in.nextInt();
		while(n-->0){
			//false-white
			//true-black
			cnt = 0;
			image = new boolean[32][32];
			String s = in.next();
			index = 0;
			draw(0, 0, 32, s);
			s = in.next();
			index = 0;
			draw(0, 0, 32, s);
			System.out.println(String.format("There are %d black pixels.", cnt));
		}
	}
	public void draw(int r,int c,int w,String s){
		char color = s.charAt(index++);
		if(color=='p'){
			draw(r, c+w/2, w/2, s);
			draw(r, c, w/2, s);
			draw(r+w/2, c, w/2, s);
			draw(r+w/2, c+w/2, w/2, s);
		}
		else if(color=='f'){
			//System.out.println(String.format("r: %d\nc: %d\nw: %d\n", r,c,w));
			for(int rn=r;rn<r+w;rn++)
				for(int cn=c;cn<c+w;cn++)
					if(image[rn][cn]==false){
						cnt++;
						image[rn][cn]=true;
					}
		}
	}
	public static void main(String[] args) {
		Quadtrees quadtrees = new Quadtrees();
		quadtrees.solve();

	}

}
