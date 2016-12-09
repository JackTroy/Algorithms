import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private long patHash;
	private int M;
	private long Q;
	private int R = 256;
	private long RM; 
	public RabinKarp(String pat){
		this.M = pat.length();
		Q = longRandomPrime();
		RM=1;
		for(int i=1;i<=M-1;i++)
			RM = (R*RM)%Q;
        patHash = hash(pat,M);
	}
    private long hash(String key, int m) { 
        long h = 0; 
        for (int j = 0; j < m; j++) 
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }
    public int search(String txt) {
        int n = txt.length(); 
        if (n < M) return n;
        long txtHash = hash(txt, M); 

        // check for match at offset 0


        // check for hash match; if hash match, check for exact match
        for (int i = M; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; 
            txtHash = (txtHash*R + txt.charAt(i)) % Q; 

            // match
            int offset = i - M + 1;
            if (patHash == txtHash)
                return offset;
        }

        // no match
        return n;
    }

    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
