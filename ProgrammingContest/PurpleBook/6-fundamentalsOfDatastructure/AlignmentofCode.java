import java.util.Scanner;

public class AlignmentofCode {
//uva 1593
	public static void main(String[] args) {
		int[] maxlens = new int[90];
		int col=0;
		String[][] words = new String[1000][90];
		int lines=0;
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			String line = in.nextLine();
			Scanner ps = new Scanner(line);
			int i;
			for(i=0;ps.hasNext();i++){
				words[lines][i] = ps.next();
				int len = words[lines][i].length();
				if(len>maxlens[i])	maxlens[i]=len;
			}
			if(i>col)	col=i;
			ps.close();
			lines++;
		}
		in.close();
		for(int i=0;i<lines&&i<1000;i++){
			for(int j=0;j<col;j++){
				//if(words[i][j]==null)	break;
				System.out.print(words[i][j]);
				if(words[i][j+1]==null)	break;
				int spaces = maxlens[j]-words[i][j].length()+1;
				while(spaces-->0) System.out.print(" ");
			}
			System.out.println();
		}
	}
}
