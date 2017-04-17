import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
public class LatticeAnimals {
//Lattice Animals UVA - 1602 
	public ArrayList<Polyomino>[] polySet = (ArrayList<Polyomino>[]) new ArrayList[11];
	public Polyomino poly = new Polyomino();
	public int[] dx = {1,0,-1,0},dy = {0,1,0,-1};
	public int N = 10;
	public void solve() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int ans[][][] = {{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 4, 5, 5, 5, 5, 5, 5, 5}, {0, 0, 4, 4, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}, {0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 2, 5, 6, 6, 6, 6, 6, 6}, {0, 0, 2, 8, 11, 12, 12, 12, 12, 12, 12}, {0, 0, 5, 11, 11, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}, {0, 1, 6, 12, 12, 12, 12, 12, 12, 12, 12}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 7, 12, 13, 13, 13, 13, 13}, {0, 0, 1, 8, 29, 34, 35, 35, 35, 35, 35}, {0, 0, 7, 29, 29, 34, 35, 35, 35, 35, 35}, {0, 0, 12, 34, 34, 34, 35, 35, 35, 35, 35}, {0, 1, 13, 35, 35, 35, 35, 35, 35, 35, 35}, {0, 1, 13, 35, 35, 35, 35, 35, 35, 35, 35}, {0, 1, 13, 35, 35, 35, 35, 35, 35, 35, 35}, {0, 1, 13, 35, 35, 35, 35, 35, 35, 35, 35}, {0, 1, 13, 35, 35, 35, 35, 35, 35, 35, 35}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 2, 13, 18, 19, 19, 19, 19}, {0, 0, 0, 7, 48, 84, 89, 90, 90, 90, 90}, {0, 0, 2, 48, 66, 102, 107, 108, 108, 108, 108}, {0, 0, 13, 84, 102, 102, 107, 108, 108, 108, 108}, {0, 0, 18, 89, 107, 107, 107, 108, 108, 108, 108}, {0, 1, 19, 90, 108, 108, 108, 108, 108, 108, 108}, {0, 1, 19, 90, 108, 108, 108, 108, 108, 108, 108}, {0, 1, 19, 90, 108, 108, 108, 108, 108, 108, 108}, {0, 1, 19, 90, 108, 108, 108, 108, 108, 108, 108}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 11, 30, 37, 38, 38, 38}, {0, 0, 0, 3, 63, 169, 223, 230, 231, 231, 231}, {0, 0, 1, 63, 140, 307, 361, 368, 369, 369, 369}, {0, 0, 11, 169, 307, 307, 361, 368, 369, 369, 369}, {0, 0, 30, 223, 361, 361, 361, 368, 369, 369, 369}, {0, 0, 37, 230, 368, 368, 368, 368, 369, 369, 369}, {0, 1, 38, 231, 369, 369, 369, 369, 369, 369, 369}, {0, 1, 38, 231, 369, 369, 369, 369, 369, 369, 369}, {0, 1, 38, 231, 369, 369, 369, 369, 369, 369, 369}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 3, 25, 53, 60, 61, 61}, {0, 0, 0, 1, 43, 256, 466, 543, 550, 551, 551}, {0, 0, 0, 43, 224, 820, 1127, 1204, 1211, 1212, 1212}, {0, 0, 3, 256, 820, 893, 1200, 1277, 1284, 1285, 1285}, {0, 0, 25, 466, 1127, 1200, 1200, 1277, 1284, 1285, 1285}, {0, 0, 53, 543, 1204, 1277, 1277, 1277, 1284, 1285, 1285}, {0, 0, 60, 550, 1211, 1284, 1284, 1284, 1284, 1285, 1285}, {0, 1, 61, 551, 1212, 1285, 1285, 1285, 1285, 1285, 1285}, {0, 1, 61, 551, 1212, 1285, 1285, 1285, 1285, 1285, 1285}}, {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 1, 16, 68, 108, 117, 118}, {0, 0, 0, 0, 21, 277, 842, 1226, 1329, 1338, 1339}, {0, 0, 0, 21, 287, 1847, 3234, 3773, 3876, 3885, 3886}, {0, 0, 1, 277, 1847, 2376, 4003, 4542, 4645, 4654, 4655}, {0, 0, 16, 842, 3234, 4003, 4003, 4542, 4645, 4654, 4655}, {0, 0, 68, 1226, 3773, 4542, 4542, 4542, 4645, 4654, 4655}, {0, 0, 108, 1329, 3876, 4645, 4645, 4645, 4645, 4654, 4655}, {0, 0, 117, 1338, 3885, 4654, 4654, 4654, 4654, 4654, 4655}, {0, 1, 118, 1339, 3886, 4655, 4655, 4655, 4655, 4655, 4655}}};
		/*
		generate();		
		for(int n = 1;n <= N; n++)
			for(int w = 1;w <= N; w++)
				for(int h = 1;h <= N; h++){
					for(Polyomino p:polySet[n]){
						int[] max = p.max();
						if(Math.min(max[0], max[1])<Math.min(w, h)&&Math.max(max[0], max[1])<Math.max(w, h)){
							ans[n][w][h]++;
						}
							
					}
				}
		*/
		while(true){
			String line = in.readLine();	if(line == null)	break;
			String[] nums = line.split(" ");	
			int n = Integer.parseInt(nums[0]);
			int w = Integer.parseInt(nums[1]);
			int h = Integer.parseInt(nums[2]);
			out.append(ans[n][w][h]+"\n");
		}
		System.out.print(out.toString());
	}
	public void generate(){
		for(int n = 1; n <= N; n++)
			polySet[n] = new ArrayList<Polyomino>();
		polySet[1].add(new Polyomino());
		for(int n = 1; n < N; n++){
			for(Polyomino p:polySet[n]){
				for(Cell c:p.s){
					for(int i = 0; i < 4; i++){
						Cell tmp = new Cell(c.x+dx[i], c.y+dy[i]);
						if(!p.s.contains(tmp)){
							Polyomino next = p.clone();
							next.s.add(tmp);
							insert(next, n+1);
						}
					}
				}
			}
		}
	}
	public boolean insert(Polyomino poly,int n){
		Polyomino tmp = poly.clone();
		for(int i = 0; i < 4; i++){
			 tmp = tmp.rotate();
			if(polySet[n].contains(tmp.normalize()))	return false;
		}
		tmp = tmp.flip();
		for(int i = 0; i < 4; i++){
			 tmp = tmp.rotate();
			if(polySet[n].contains(tmp.normalize()))	return false;
		}
		polySet[n].add(poly.clone().normalize());
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LatticeAnimals a = new LatticeAnimals();
		a.solve();
	}

}
class Polyomino{
	TreeSet<Cell> s = new TreeSet<Cell>();
	public Polyomino(){
		s.add(new Cell(0, 0));
	}
	public Polyomino(TreeSet<Cell> s){
		this.s = (TreeSet<Cell>) s.clone();
	}
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Polyomino))	return false;
		Polyomino obj = (Polyomino) o;
		return this.s.equals(obj.s);
	}
	public Polyomino clone(){
		return new Polyomino(this.s);
	}
	public Polyomino rotate(){
		Polyomino rt = new Polyomino();
		for(Cell c:s)
			rt.s.add(new Cell(c.y, -c.x));
		return rt;
	}
	public Polyomino flip(){
		Polyomino fp = new Polyomino();
		for(Cell c:s)
			fp.s.add(new Cell(c.x, -c.y));
		return fp;
	}
	public Polyomino normalize(){
		Polyomino norm = new Polyomino();
		int x = this.s.first().x,y = this.s.first().y;
		for(Cell c:this.s)
			norm.s.add(new Cell(c.x-x, c.y-y));
		return norm;
	}
	public int[] max(){
		int maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE, minx = Integer.MAX_VALUE,miny = Integer.MAX_VALUE;
		for(Cell c:s){
			if(c.x > maxx)	maxx = c.x;
			if(c.y > maxy)	maxy = c.y;
			if(c.x < minx)	minx = c.x;
			if(c.y < miny)	miny = c.y;
		}
		int[] ans = {maxx-minx,maxy-miny};
		return ans;
	}
}
class Cell implements Comparable<Cell>{
	public int x,y;
	public Cell(int x,int y){
		this.x = x;	this.y = y;
	}
	public int compareTo(Cell that) {
		if(this.x==that.x&&this.y==that.y)								return 0;
		else if(this.x < that.x || this.x == that.x && this.y<that.y)	return -1;
		else															return 1;
	}
	public Cell clone(){
		return new Cell(x, y);
	}
}