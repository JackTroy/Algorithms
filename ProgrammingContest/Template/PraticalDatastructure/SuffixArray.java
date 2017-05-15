import java.util.Arrays;
import java.util.Comparator;

public class SuffixArray {
	public static String s;
	public static Integer[] sa, rank, height;
	public static Integer[][] d;
	public static void init(String input){
		s = input;
		int n = s.length();
		sa = new Integer[s.length()];
		rank = new Integer[s.length()];
		height = new Integer[s.length()];
		for(int i = 0; i < s.length(); i++)	sa[i] = i;	
		Arrays.sort(sa, new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				return s.substring(o1).compareTo(s.substring(o2));
			}
			
		});
		for(int i = 0; i < n; i++)	rank[sa[i]] = i;
		for(int i = 0, k = 0; i < n; i++){
			if(k != 0)	k--;
			if(rank[i] == 0){
				height[rank[i]] = 0;
				continue;
			}
			int j = sa[rank[i] - 1];
			while(s.charAt(i + k) == s.charAt(j + k)) k++;
			height[rank[i]] = k;
		}
		for(int i = 0; i < n; i++)	d[i][0] = height[i];
		for(int j = 1; (1<<j) < n; j++){
			for(int i = 0; i + (1<<j) - 1 <= n; i++){
				d[i][j] = Math.min(d[i][j - 1], d[i + (1<<(j - 1))][j - 1]);
			}
		}
	}
	public static int BinarySearch(String query){
		int l = 0, r = s.length() - 1, mid = 0, m = query.length();
		while(l <= r){
			mid = (l + r) / 2;
			int cmp = s.substring(sa[mid], sa[mid] + m).compareTo(query);
			if(cmp < 0)			l = mid + 1;
			else if(cmp > 0)	r = mid - 1;
			else				return mid;
		}
		return -1;
	}
	public static int BinarySearchUsingLCP(String query){
		int l = 0, r = s.length() - 1, mid = 0;
		int m = query.length(), MaxMatch = 0, ans = s.length() - 1;
		if(s.charAt(s.length() - 1) == query.charAt(0))	MaxMatch = 1;
		while(l <= r){
			mid = (l + r) / 2;
			int cmp = mid < ans ? RangeMin(mid, ans) : RangeMin(ans, mid);
			if(cmp < MaxMatch){
				MaxMatch = cmp;
				if(s.charAt(sa[mid] + cmp) < query.charAt(cmp))	r = mid - 1;
				else	l = mid + 1;
			}
			else{
				while(s.charAt(sa[mid] + MaxMatch) == query.charAt(MaxMatch)){
					MaxMatch++;
					if(sa[mid] + MaxMatch == s.length() || MaxMatch == query.length()){
						return mid;
					}
				}
				if(s.charAt(sa[mid] + MaxMatch) < query.charAt(MaxMatch))
					r = mid - 1;
				else	l = mid + 1;
			}
		}
		return -1;
	}
	public static int RangeMin(int l, int r){
		int k = 0;
		while(1<<(k + 1) <= r - l + 1)	k++;
		return Math.min(d[l][k], d[r - (1<<k) + 1][k]);
	}
	public static void main(String[] args) {

	}

}
