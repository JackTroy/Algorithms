public class Binomial {
	public static double binomial(int n, int k, double p,Counter c){
		double[][] b=new double[n+1][k+1];
		for(int i=0;i<n;i++)
			for(int j=0;j<k;j++)
				b[i][j]=-1;
		return binomial(b,n,k,p,c);
	}
	public static double binomial(double b[][],int n, int k, double p,Counter c)
    {
        if (n == 0 && k == 0) return 1.0;
        if (n < 0 || k < 0) return 0.0;
        if(b[n][k]==-1){
        	c.increment();
        	return (1.0 - p) * binomial(n-1, k, p,c) + p * binomial(n-1, k-1, p,c);
        }
        return b[n][k];
    }
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
	}
}
