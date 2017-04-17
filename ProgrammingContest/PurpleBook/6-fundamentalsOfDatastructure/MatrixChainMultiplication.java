import java.util.Scanner;
import java.util.Stack;
//uva 442
public class MatrixChainMultiplication {

	public static class Matrix{
		public int r,c;
		public Matrix(int r,int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		Matrix[] matrixs = new Matrix[26];
		//ArrayList<Matrix> ms = new ArrayList<Matrix>();
		for(int i=0;i<r;i++){
			in.next();
			//ms.add(new Matrix(in.nextInt(), in.nextInt()));
			matrixs[i] = new Matrix(in.nextInt(), in.nextInt());
		}
		while(in.hasNext()){
			String exp = in.next();
			if(exp.length()==1)	System.out.println(0);
			else{
				boolean error = false;
				int sum = 0;
				Stack<Matrix> s = new Stack<Matrix>();
				for(int i=0;i<exp.length();i++){
					char c = exp.charAt(i);
					if(c=='(')continue;
					else if(c==')'){
						Matrix b = s.pop();
						Matrix a = s.pop();
						if(a.c!=b.r) {error = true;break;}
						sum+=a.r*a.c*b.c;
						s.push(new Matrix(a.r, b.c));
					}
					else s.push(matrixs[c-'A']);
				}
				if(error)	System.out.println("error");
				else		System.out.println(sum);
			}
		}
		in.close();
	}

}
