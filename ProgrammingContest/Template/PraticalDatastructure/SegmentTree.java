
public class SegmentTree {
	Node tree[];
	void build(Node tree[], int o, int l, int r){
		tree[o] = new Node(l, r);
		if(l == r)	return;
		int mid = (l + r) / 2;
		build(tree, 2 * o, l, mid);
		build(tree, 2 * o + 1, mid + 1, r);
	}
	void pushdown(Node tree[], int c){ 
		//first set then add
	    if(tree[c].setv!=0){  
	        tree[2*c].addv=tree[2*c+1].addv=0;  
	        tree[2*c].setv=tree[2*c+1].setv=tree[c].setv;  
	        tree[2*c].maxv=tree[2*c+1].maxv=tree[c].setv;  
	        tree[2*c].minv=tree[2*c+1].minv=tree[c].setv;  
	        tree[2*c].sum=(tree[2*c].r-tree[2*c].l+1)*tree[c].setv;  
	        tree[2*c+1].sum=(tree[2*c+1].r-tree[2*c+1].l+1)*tree[c].setv;  
	        tree[c].setv=0;  
	    }  
	    if(tree[c].addv!=0){
	        tree[2*c].addv+=tree[c].addv;tree[2*c+1].addv+=tree[c].addv;  
	        tree[2*c].maxv+=tree[c].addv;tree[2*c+1].maxv+=tree[c].addv;  
	        tree[2*c].minv+=tree[c].addv;tree[2*c+1].minv+=tree[c].addv;  
	        tree[2*c].sum+=(tree[2*c].r-tree[2*c].l+1)*tree[c].addv;  
	        tree[2*c+1].sum+=(tree[2*c+1].r-tree[2*c+1].l+1)*tree[c].addv;  
	        tree[c].addv=0;  
	    }
	}
	void add(Node tree[], int ul,int ur,int c,int val){  
	    if(tree[c].l==ul&&tree[c].r==ur){  
	        tree[c].addv+=val;  
	        tree[c].maxv+=val;  
	        tree[c].minv+=val;  
	        tree[c].sum+=(ur-ul+1)*val;  
	        return ;  
	    }  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    pushdown(tree, c);  
	    if(ur<=mid)		add(tree, ul,ur,2*c,val);  
	    else if(ul>mid)	add(tree, ul,ur,2*c+1,val);  
	    else{  
	        add(tree, ul,mid,2*c,val);  
	        add(tree, mid+1,ur,2*c+1,val);  
	    }  
	    tree[c].maxv=Math.max(tree[2*c].maxv,tree[2*c+1].maxv);  
	    tree[c].minv=Math.min(tree[2*c].minv,tree[2*c+1].minv);  
	    tree[c].sum=tree[2*c].sum+tree[2*c+1].sum;  
	}  
	void set(Node tree[], int ul,int ur,int c,int val){  
	    if(tree[c].l==ul&&tree[c].r==ur)  
	    {  
	        tree[c].addv=0;  
	        tree[c].setv=val;  
	        tree[c].maxv=val;  
	        tree[c].minv=val;  
	        tree[c].sum=(ur-ul+1)*val;  
	        return ;  
	    }  
	    pushdown(tree, c);  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    if(ur<=mid)set(tree, ul,ur,2*c,val);  
	    else if(ul>mid)set(tree, ul,ur,2*c+1,val);  
	    else{  
	        set(tree, ul,mid,2*c,val);  
	        set(tree, mid+1,ur,2*c+1,val);  
	    }  
	    tree[c].maxv=Math.max(tree[2*c].maxv,tree[2*c+1].maxv);  
	    tree[c].minv=Math.min(tree[2*c].minv,tree[2*c+1].minv);  
	    tree[c].sum=tree[2*c].sum+tree[2*c+1].sum;  
	}  
	Node query(Node tree[], int ql,int qr,int c){  
	    if(tree[c].l==ql&&tree[c].r==qr)	return tree[c];  
	    pushdown(tree, c);  
	    int mid=(tree[c].l+tree[c].r)/2;  
	    if(qr<=mid)		return query(tree, ql,qr,2*c);  
	    else if(ql>mid)	return query(tree, ql,qr,2*c+1);  
	    else{  
	        Node o = new Node(ql, qr),p,q;  
	        p=query(tree, ql,mid,2*c);  
	        q=query(tree, mid+1,qr,2*c+1);  
	        o.sum=p.sum+q.sum;  
	        o.maxv=Math.max(p.maxv,q.maxv);  
	        o.minv=Math.min(p.minv,q.minv);  
	        return o;  
	    }
	} 
}
class Node{
	int l,r,addv,setv,maxv,minv,sum;
	public Node(int l,int r) {
		this.l = l;
		this.r = r;
	}
}