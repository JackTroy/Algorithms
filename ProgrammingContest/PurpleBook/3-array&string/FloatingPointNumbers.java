import java.util.Scanner;

public class FloatingPointNumbers {
//uva 11809
	public static void main(String[] args) {
		//为什么选用打表法：
		//条件限制大，输入的数已知使最大的，M和E的位数比较小，可以枚举
		long[][] exp = new long[10][31];
		double[][] mat = new double[10][31];
		
		for(int i=0;i<=9;i++)
			for(int j=1;j<=30;j++){
				double m = 1-Math.pow(2, -1-i),e=Math.pow(2,j)-1;
				//m*(2^e) 直接算太大，无法存储， 于是想办法将表分别对应 A 和 B（既科学计算法的具体数字和指数）
				//方法是取以10为底的对数
				double t = Math.log10(m)+e*Math.log10(2);
				exp[i][j]=(long)t;
				mat[i][j]=Math.pow(10, t-exp[i][j]);
			}
		
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String AeB = in.next();
			if(AeB.equals("0e0"))	break;
			int epos =AeB.indexOf('e');
			double A = Double.parseDouble(AeB.substring(0,epos));
			int B = Integer.parseInt(AeB.substring(epos+1, AeB.length()));
			for(int i=0;i<=9;i++)
				for(int j=1;j<=30;j++){
					if(B==exp[i][j]&&Math.abs(A-mat[i][j])<1e-4){
						System.out.println(i+" "+j);
					}
				}

		}
		in.close();
	}

}
