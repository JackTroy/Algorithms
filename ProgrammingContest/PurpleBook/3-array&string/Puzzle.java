import java.util.Scanner;
public class Puzzle {
	//227
	private static boolean move(char[][] puzzle,int r,int c,char[] moves){
		int er=r,ec=c;
		for(int i=0;i<moves.length;i++){
			switch (moves[i]) {
			case 'A':{
				if(!swap(puzzle,er,ec,er-1,ec)) return false;
				er--;
				break;
			}
			case 'B':{
				if(!swap(puzzle,er,ec,er+1,ec)) return false;
				++er;
				break;
			}
			case 'R':{
				if(!swap(puzzle,er,ec,er,ec+1)) return false;
				++ec;
				break;
			}
			case 'L':{
				if(!swap(puzzle,er,ec,er,ec-1)) return false;
				--ec;
				break;
			}
			}
		}
		return true;
	}
	private static boolean swap(char[][] puzzle,int er,int ec,int r,int c){
		if(!(r<=4&&r>=0&&c<=4&&c>=0))	return false;
		char tmp = puzzle[er][ec];
		puzzle[er][ec] = puzzle[r][c];
		puzzle[r][c] = tmp;
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in  = new Scanner(System.in);
		int round=0;
		while(true){
			String input = in.nextLine();
			if(input.equals("Z")||input.equals(""))	break;
			round++;
			char[][] puzzle = new char[5][5];
			int r=0,c=0;
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					puzzle[i][j] = input.charAt(j);
					if(puzzle[i][j]==' '){
						r=i;
						c=j;
					}
				}
				if(i==4) break;
				input = in.nextLine();
			}
			String string="";
			while(true){
				string+=in.nextLine();
				if(string.charAt(string.length()-1)=='0'){
					string = string.substring(0, string.length()-1);
					break;
				}
			}
			char[] moves = string.toCharArray();
			if(round>1) System.out.println();
			System.out.println(String.format("Puzzle #%d:", round));
			if(move(puzzle,r,c,moves)){
				for(int i=0;i<5;i++)
					System.out.println(String.format("%c %c %c %c %c", puzzle[i][0],puzzle[i][1],puzzle[i][2],puzzle[i][3],puzzle[i][4]));
			}
			else	System.out.println("This puzzle has no final configuration.");
		}
		in.close();
	}

}
