
public class SegmentTreeSegment {
	int sum[], min[], max[];
	int addv[], setv[];//binary heap
	int ql, qr, QuerySum, QueryMax, QueryMin;
	int ul, ur, v;
	void init(int[] a){
		int n = a.length, h = 0;
		while((1<<h) < n)	h++;
		//heap elements start from 1
		sum = new int[1<<(h + 1)];
		min = new int[1<<(h + 1)];
		max = new int[1<<(h + 1)];
		addv = new int[1<<(h + 1)];
		setv = new int[1<<(h + 1)];
		for(int i = 0; i < n; i++){
			ul = ur = i + 1;
			v = a[i];
			update(1, 1, (1<<h) - 1);
		}
	}
	void update(int o, int l, int r){
		if(ul <= l && r <= ur)	addv[o] += v;
		else{
			int m = l + (r - l) / 2;
			if(ul <= m)	update(2 * o, l, m);
			if(m < ur)	update(2 * o + 1, m + 1, r);
		}
		maintain(o, l, r);
	}
	void maintain(int o, int l, int r){
		sum[o] = max[o] = min[o] = 0;
		if(r > l){
			int lc = o * 2, rc = o * 2 + 1;
			sum[o] = sum[lc] + sum[rc];
			min[o] = Math.min(min[lc], min[rc]);
			max[o] = Math.max(max[lc], max[rc]);
		}
		sum[o] += addv[o] * (r - l + 1);
		min[o] += addv[o];
		max[o] += addv[o];
	}
	void query(int o, int l, int r, int add){
		if(ql <= l && r <= qr){
			QuerySum += sum[o] + add * (r - l + 1);
			QueryMin = Math.min(QueryMin, min[o] + add);
			QueryMax = Math.max(QueryMax, max[o] + add);
		} 
		else{
			int m = l + (r - l) / 2;
			if(ql <= m)	query(o * 2, l, m, add + addv[o]);
			if(m < qr)	query(o * 2 + 1, m + 1, r, add + addv[o]);
		}
	}
	void  pushdown(int o){
		if(setv[o] >= 0){
			int lc = 2 * o, rc = 2 * o + 1;
			setv[lc] = setv[o];
			setv[rc] = setv[o];
			setv[o] = -1;
		}
	}
	public static void main(String[] args) {
		
	}

}
