import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastMatrixOperations {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	
	int r, c, m;
	void solve() throws NumberFormatException, IOException{
		tree = new Node[25][1<<20+1];
		for(;;){
			String str = in.readLine();	if(str == null)	break;
			
			StringTokenizer tk = new StringTokenizer(str);
			r = Integer.parseInt(tk.nextToken());
			c = Integer.parseInt(tk.nextToken());
			m = Integer.parseInt(tk.nextToken());
			int right = 1;
			while(right < c)	right *= 2;
			for(int i = 1; i <= r; i++)	build(tree[i], 1, 1, right);
			for(int i = 0; i < m; i++){
				String s = in.readLine();
				tk = new StringTokenizer(s);
				//System.out.println(s);
				int type = Integer.parseInt(tk.nextToken());
				int x1 = Integer.parseInt(tk.nextToken());
				int y1 = Integer.parseInt(tk.nextToken());
				int x2 = Integer.parseInt(tk.nextToken());
				int y2 = Integer.parseInt(tk.nextToken());
				if(type != 3){
					int v = Integer.parseInt(tk.nextToken());
					if(type == 1)
						for(int j = x1; j <= x2; j++)	add(tree[j], y1, y2, 1, v);
					else
						for(int j = x1; j <= x2; j++)	update(tree[j], y1, y2, 1, v);
				}
				else{
					int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
					for(int j = x1; j <= x2; j++){
						Node o = query(tree[j], y1, y2, 1);
						sum += o.sum;
						max = Math.max(max, o.maxv);
						min = Math.min(min, o.minv);
					}
					out.append(String.format("%d %d %d\n", sum, min, max));
				}
			}
		}
		System.out.print(out.toString());
	}
	Node tree[][];
	void build(Node tree[], int o, int l, int r){
		tree[o] = new Node(l, r);
		if(l == r)	return;
		int mid = (l + r) / 2;
		build(tree, 2 * o, l, mid);
		build(tree, 2 * o + 1, mid + 1, r);
	}
	void pushdown(Node tree[], int c)  
	{  
	    if(tree[c].setv!=0)  
	    {  
	        tree[2*c].addv=tree[2*c+1].addv=0;  
	        tree[2*c].setv=tree[2*c+1].setv=tree[c].setv;  
	        tree[2*c].maxv=tree[2*c+1].maxv=tree[c].setv;  
	        tree[2*c].minv=tree[2*c+1].minv=tree[c].setv;  
	        tree[2*c].sum=(tree[2*c].r-tree[2*c].l+1)*tree[c].setv;  
	        tree[2*c+1].sum=(tree[2*c+1].r-tree[2*c+1].l+1)*tree[c].setv;  
	        tree[c].setv=0;  
	    }  
	    if(tree[c].addv!=0)  
	    {  
	        tree[2*c].addv+=tree[c].addv;tree[2*c+1].addv+=tree[c].addv;  
	        tree[2*c].maxv+=tree[c].addv;tree[2*c+1].maxv+=tree[c].addv;  
	        tree[2*c].minv+=tree[c].addv;tree[2*c+1].minv+=tree[c].addv;  
	        tree[2*c].sum+=(tree[2*c].r-tree[2*c].l+1)*tree[c].addv;  
	        tree[2*c+1].sum+=(tree[2*c+1].r-tree[2*c+1].l+1)*tree[c].addv;  
	        tree[c].addv=0;  
	    }  
	}  
	void add(Node tree[], int a,int b,int c,int val)  
	{  
	    if(tree[c].l==a&&tree[c].r==b)  
	    {  
	        tree[c].addv+=val;  
	        tree[c].maxv+=val;  
	        tree[c].minv+=val;  
	        tree[c].sum+=(b-a+1)*val;  
	        return ;  
	    }  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    pushdown(tree, c);  
	    if(b<=mid)add(tree, a,b,2*c,val);  
	    else if(a>mid)add(tree, a,b,2*c+1,val);  
	    else  
	    {  
	        add(tree, a,mid,2*c,val);  
	        add(tree, mid+1,b,2*c+1,val);  
	    }  
	    tree[c].maxv=Math.max(tree[2*c].maxv,tree[2*c+1].maxv);  
	    tree[c].minv=Math.min(tree[2*c].minv,tree[2*c+1].minv);  
	    tree[c].sum=tree[2*c].sum+tree[2*c+1].sum;  
	}  
	void update(Node tree[], int a,int b,int c,int val)  
	{  
	    if(tree[c].l==a&&tree[c].r==b)  
	    {  
	        tree[c].addv=0;  
	        tree[c].setv=val;  
	        tree[c].maxv=val;  
	        tree[c].minv=val;  
	        tree[c].sum=(b-a+1)*val;  
	        return ;  
	    }  
	    pushdown(tree, c);  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    if(b<=mid)update(tree, a,b,2*c,val);  
	    else if(a>mid)update(tree, a,b,2*c+1,val);  
	    else  
	    {  
	        update(tree, a,mid,2*c,val);  
	        update(tree, mid+1,b,2*c+1,val);  
	    }  
	    tree[c].maxv=Math.max(tree[2*c].maxv,tree[2*c+1].maxv);  
	    tree[c].minv=Math.min(tree[2*c].minv,tree[2*c+1].minv);  
	    tree[c].sum=tree[2*c].sum+tree[2*c+1].sum;  
	}  
	Node query(Node tree[], int a,int b,int c)  
	{  
	    if(tree[c].l==a&&tree[c].r==b)return tree[c];  
	    pushdown(tree, c);  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    if(b<=mid)return query(tree, a,b,2*c);  
	    else if(a>mid)return query(tree, a,b,2*c+1);  
	    else  
	    {  
	        Node o = new Node(a, b),p,q;  
	        p=query(tree, a,mid,2*c);  
	        q=query(tree, mid+1,b,2*c+1);  
	        o.sum=p.sum+q.sum;  
	        o.maxv=Math.max(p.maxv,q.maxv);  
	        o.minv=Math.min(p.minv,q.minv);  
	        return o;  
	    }  
	} 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		FastMatrixOperations a = new FastMatrixOperations();
		a.solve();
	}

}
class Node{
	int l,r,addv,setv,maxv,minv,sum;
	public Node(int l,int r) {
		this.l = l;
		this.r = r;
	}
}