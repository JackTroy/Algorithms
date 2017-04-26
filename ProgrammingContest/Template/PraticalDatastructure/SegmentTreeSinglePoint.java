public class SegmentTreeSinglePoint {

	//single point modification version
	int sum[], min[], max[];//binary heap
	int ql, qr, QuerySum, QueryMax, QueryMin;
	void query(int o, int l, int r){
		int m = l + (r - l) / 2;
		if(ql <= l && r <= qr){
			QuerySum += sum[o];
			QueryMax = Math.max(QueryMax, max[o]);
			QueryMin = Math.min(QueryMin, min[o]);
			return;
		}
		if(ql <= m)	query(2 * o, l, m);
		if(m < qr)	query(2 * o + 1, m + 1, r);
	}
	int p, v;
	void update(int o, int l, int r){
		int m = l + (r - l) / 2;
		if(l == r){
			sum[o] += v;
			min[o] += v;
			max[o] += v;
		}
		else{
			if(p <= m)	update(2 * o, l, m);
			else		update(2 * o + 1, m + 1, r);
			sum[o] = sum[2 * o] + sum[2 * o + 1];
			max[o] = Math.max(max[2 * o], max[2 * o + 1]);
			min[o] = Math.min(min[2 * o], min[2 * o + 1]);
		}
	}
	void test(){
		sum = new int[16];
		min = new int[16];
		max = new int[16];
		int[] a = new int[]{78, 12, 57, 47, 43, 70, 77, 90};
		for(int i = 0; i < 8; i++){
			v = a[i];
			p = i + 1;
			update(1, 1, 8);
		}
		ql = 2; qr = 5; QuerySum = 0;	QueryMax = -1;	QueryMin = 101;
		query(1, 1, 8);
		System.out.print(String.format("2 - 5,sum:%d,max:%d,min:%d\n", QuerySum,QueryMax,QueryMin));
		v = 20;
		p = 4;
		update(1, 1, 8);
		v = -50;
		p = 3;
		update(1, 1, 8);
		System.out.println(String.format("update %dth by %d", p, v));
		ql = 2; qr = 5; QuerySum = 0;	QueryMax = -1;	QueryMin = 101;
		query(1, 1, 8);
		System.out.print(String.format("2 - 5,sum:%d,max:%d,min:%d", QuerySum,QueryMax,QueryMin));
	}
	public static void main(String[] args) {
		SegmentTreeSinglePoint a = new SegmentTreeSinglePoint();
		a.test();
	}
}
