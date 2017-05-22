import java.util.ArrayList;

public class FindOutSquare {
	public static boolean isOk(Long a){
		Long square = a * a;
		boolean exist[] = new boolean[10];
		while(square > 0){
			int num = (int) (square % 10);
			if(exist[num])	return false;
			else			exist[num] = true;
			square /= 10; 
		}
		return true;
	}
	public static boolean check(boolean exist[]){
		for(int i = 0; i < 10; i++)
			if(!exist[i])
				return false;
		return true;
	}
	public static void search(int d, int curd,int lastpos, boolean exist[], ArrayList<Long> combination){
		if(d == curd){
			if(check(exist)){
				ans++;
				StringBuilder out = new StringBuilder();
				for(Long l:combination)
					out.append(l + " ");
			}
			return;
		}
		else{
			for(int i = lastpos + 1; i < a.size(); i++){
				boolean copy[] = exist.clone();
				Long num = (long)a.get(i);
				num *= num;
				boolean ok = true;
				if(num == 0)	copy[0] = true;
				while(num > 0){
					int num1 = (int) (num % 10);
					if(copy[num1]){
						ok = false;
						break;
					}
					copy[num1] = true;
					num /= 10;
				}
				if(!ok)	continue;
				combination.add((long)a.get(i) * (long)a.get(i));
				search(d, curd + 1, i, copy, combination);
				combination.remove(combination.size() - 1);
			}
		}
	}
	static Long ans;
	static ArrayList<Integer> a = new ArrayList<>();
	static ArrayList<Integer> lens = new ArrayList<>();
	public static void main(String[] args) {
		for(int i = 0; i < 99381; i++)
			if(isOk((long) i)){
				a.add(i);
				lens.add((int)Math.log(i) + 1);
			}
				
		ans = (long) 0;
		for(int i = 1; i < 10; i++){
			boolean exist[] = new boolean[10];
			search(i, 0, -1, exist, new ArrayList<>());
			System.out.println(i);
			System.out.println(ans);
		}
		System.out.println(ans);
	}
	
}
