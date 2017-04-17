import java.util.Scanner;

public class SpringOuting {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt(), k = in.nextInt();
			int[][] pre = new int[n][k + 1];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < k + 1; j++)
					pre[i][j] = in.nextInt();
			int[] ans = new int[k + 2];
			for(int i = k; i > 0; i--){
				int count = 0;
				for(int j = 0; j < n; j++)
					for(int z = 0; z < k; z++){
						if(pre[j][z] == ans[i + 1])		break;
						else if(pre[j][z] == i)	{
							count++;
							break;
						}
					}
				if(count > n / 2)	ans[i] = i;
				else				ans[i] = ans[i + 1];
			}
			if(ans[1] > 0)	System.out.println(ans[1]);
			else			System.out.println("otaku");
		}
		in.close();
	}

}
