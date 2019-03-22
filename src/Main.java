import java.util.Scanner;

public class Main {

	static boolean running = true ;
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String fen  = "rr,rr,rr,rr,rr,rr/,,,,,/,,,,,/,,,,,/,,,,,/bb,bb,bb,bb,bb,bb";
		String[] splittedFen = splitter(fen);
		String move = "";
		int round = 1;
		String player = "";
		int playerCount = 1;
		System.out.println("\""+fen+" r\"");
		while (running) {
			System.out.println("Round : " +round );
			
				if(player.equals("")) {
					System.out.println("startplayer :haskell oder :ruby press enter to finish");
					player= scan.nextLine();
				}
				if(!player.equals("")) {
				System.out.println("Input Move for "+player);
				move= scan.nextLine();
				
				if(playerCount == 1) {
					if(player.equals("ruby")) System.out.print("getMove ");
					System.out.println("\""+asFenNot(applyMove(splittedFen, move))+" b\"");
					playerCount = 2;
				}
				else if(playerCount == 2) {
					if(player.equals("ruby")) System.out.print("getMove ");
					System.out.println("\""+asFenNot(applyMove(splittedFen, move))+" r\"");
					playerCount = 1 ;
				}
				if(player.equals("haskell")) {
					player = "ruby";
				}
				else {
					player="haskell";
				}
				}
				else {
					running = false;
				}
				round++;
			
			
		}

	}
	
	public static String[] splitter(String fen) {
		String[] firstSplit = fen.split("/");
		String flattenedSplit = "";
		for(int i = 0; i< firstSplit.length-1 ; i++) {
			flattenedSplit += firstSplit[i]+",";
		}
		flattenedSplit += firstSplit[firstSplit.length-1];
		String[] secondSplit = flattenedSplit.split(",");
		return secondSplit;
	}
	
	public static String asFenNot(String[] splitted) {
		String temp = "";
		for(int i = 0; i< splitted.length-1 ; i++) {
			if((i+1)%6 ==0) {
				temp += splitted[i]+"/";
			}else {
			temp += splitted[i]+",";
			}
		}
		temp += splitted[splitted.length-1];
		return temp;
	}
	public static String[] applyMove(String[] fen,String move) {
		
		String[] moveElems = move.split("-");
		String startPos    = moveElems[0];
		int steps          = Integer.parseInt(moveElems[1]);
		String endPos      = moveElems[2];
		String[] startPosSplit = {""+startPos.charAt(0),""+startPos.charAt(1)};
		String[] endPosSplit   = {""+endPos.charAt(0)  ,""+endPos.charAt(1)  };
		int startPosIndex = toIndexNumber(startPosSplit)-1;
		int endPosIndex   = toIndexNumber(endPosSplit)-1;
		String temp="";
		if(fen[startPosIndex].length() >1) {
		 temp = fen[startPosIndex].substring(0, steps);
		fen[startPosIndex] = fen[startPosIndex].substring(steps, fen[startPosIndex].length());
		}
		else {
		 temp = ""+fen[startPosIndex].charAt(0);
		 fen[startPosIndex] = "";
		}
		
			fen[endPosIndex]  = temp + fen[endPosIndex] ;
		
		return fen;
	}
	
	public static int toIndexNumber(String[] inputPos) {
		String chara = inputPos[0];
		String number = inputPos[1];
		
		int column	= 0;
		int row   = 6-Integer.parseInt(number);
		switch (chara ) {
		case "a": column	= 1 ;
		break;
		case "b": column	= 2 ;
		break;
		case "c": column	= 3 ;
		break;
		case "d": column	= 4 ;
		break;
		case "e": column	= 5 ;
		break;
		case "f": column	= 6 ;
		break;
		
		}
		
		return column+row*6;
	}
}
