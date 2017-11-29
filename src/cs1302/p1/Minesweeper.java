
package cs1302.p1;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

/**
 * This class represents a Minesweeper game.
 *
 * @author Christopher Sitzmann <cks61423@uga.edu>
 */
public class Minesweeper {

    /**
     * Constructs an object instance of the {@link Minesweeper} class using the
     * information provided in <code>seedFile</code>. Documentation about the
     * format of seed files can be found in the project's <code>README.md</code>
     * file.
     *
     * @param seedFile the seed file used to construct the game
     * @see            <a href="https://github.com/mepcotterell-cs1302/cs1302-minesweep\
er-alpha/blob/master/README.md#seed-files">README.md#seed-files</a>
     */
	
    private static int roundsCompleted;
    private static int rows;
    private static int cols;
    private static String[][] board;
    private static boolean[][] mines;
    private static int bombs;
    private static int score = (rows*cols) - bombs - roundsCompleted;
    
    /**
    * Constructs an object instance of the {@link Minesweeper} class using the
    * <code>rows</code> and <code>cols</code> values as the game grid's number
    * of rows and columns respectively. Additionally, One quarter (rounded up)
    * of the squares in the grid will will be assigned mines, randomly.
    *
    * @param rows the number of rows in the game grid
    * @param cols the number of cols in the game grid
    */
    public Minesweeper(int rows, int cols) {
    	if(rows > 10 || rows <= 0|| cols > 10 || cols <= 0)
	    {
		System.out.println("");
		System.out.println("ಠ_ಠ  says, \"Cannot create a mine field with that many rows and/or columns!\"");
		System.exit(0);
	    }
    	Minesweeper.rows = rows+1;
    	Minesweeper.cols = cols+1;
    	populateBoard();
    	
	} //Minesweeper
    public Minesweeper(File seedFile) {
    	
    	try(Scanner f = new Scanner(seedFile)){
    		
    		Minesweeper.bombs = 0;
    	   
    			if(f.hasNextInt())
    				rows = f.nextInt()+1;
    			else
    			    {
    				System.out.println("Cannot creat game with " + seedFile + ", because it is not formatted correctly.");
    				System.exit(0);
    			    }//else  
    			if(f.hasNextInt())
    			    {
    				cols = f.nextInt()+1;
    				mines = new boolean[rows][cols];
    			    }
    			else
    			    {
    				System.out.println("Cannot creat game with " + seedFile + ", because it is not formatted correctly.");
    				System.exit(0);
    			    }//else
    			if(f.hasNextInt()){
    			   bombs = f.nextInt();
    			}
    			    else
    			    {
    				System.out.println("Cannot creat game with " + seedFile + ", because it is not formatted correctly.");
    				System.exit(0);
    			    }//else
    			if(f.hasNextInt())
    			    {
    				for(int i=0; i < bombs;i++)
    				    {
    					mines[f.nextInt()][f.nextInt()+1] = true;
    				    }//for
    			    }//if
    			if(f.hasNextInt())
    			    {
    				System.out.println("Cannot creat game with " + seedFile + ", because it is not formatted correctly.");
    				System.exit(0);
    			    }
    			if(rows > 11 || rows <= 0|| cols > 11 || cols <= 0)
    			    {
    				System.out.println("");
    				System.out.println("ಠ_ಠ  says, \"Cannot create a mine field with that many rows and/or columns!\"");
    				System.exit(0);
    			    }
    	    }catch(Exception c){
    	    
    	    }
   } // Minesweeper
   private static  void startInterface(){
        System.out.println("\r" + 
        		"```\r\n" + 
        		"        _\r\n" + 
        		"  /\\/\\ (_)_ __   ___  _____      _____  ___ _ __   ___ _ __\r\n" + 
        		" /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|\r\n" + 
        		"/ /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |\r\n" + 
        		"\\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|\r\n" + 
        		"                             ALPHA EDITION |_| v2017.f\r\n\n" + 
        		"```\r\n" + 
        		"\r\n" + 
        		"");
   }//start
   private static void gameOver(){
       System.out.println("```\r\n" + 
       		"\r\n" + 
       		" Oh no... You revealed a mine!\r\n" + 
       		"  __ _  __ _ _ __ ___   ___    _____   _____ _ __\r\n" + 
       		" / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|\r\n" + 
       		"| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |\r\n" + 
       		" \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|\r\n" + 
       		" |___/\r\n" + 
       		"\r\n" + 
       		"```\r\n" + 
       		"");
       System.exit(0);
   }//gameOver
   private static void playerWins() {
	   System.out.print("\r\n" + 
	   		"```\r\n" + 
	   		"\r\n" + 
	   		"\r\n" + 
	   		" ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"\r\n" + 
	   		" ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░\r\n" + 
	   		" ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score\"\r\n" + 
	   		" ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░\r\n" + 
	   		" ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\"\r\n" + 
	   		" ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░\r\n" + 
	   		" ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ \"Wow\"\r\n" + 
	   		" ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░\r\n" + 
	   		" ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░\r\n" + 
	   		" ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░\r\n" + 
	   		" ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░\r\n" + 
	   		" ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌\r\n" + 
	   		" ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░\r\n" + 
	   		" ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░\r\n" + 
	   		" ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░\r\n" + 
	   		" ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░\r\n" + 
	   		" ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!\r\n" + 
	   		" ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!\r\n" + 
	   		" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: " + ((rows*cols)-bombs-roundsCompleted) + "\r\n" + 
	   		"\r\n" + 
	   		"\r\n" + 
	   		"```\r\n" + 
	   		"\r\n" + 
	   		"");
	   System.exit(0);
   }//playerWIns
   private static String[][] gameBoard(int rows, int cols){
       String[][] gameBoard = new String[rows][cols];
       for(int i = 0; i < rows; i++){
    	   if(i != rows - 1) {
	           for(int j = 0; j < cols; j++){ 
	        	   if(j == 0) 
	        		   gameBoard[i][j] = Integer.toString(i) + " ";
	        	   else {
	        		   if(j == 1)
	        			   gameBoard[i][j] = "|   |";
	        		   else
	        			   gameBoard[i][j] = "   |";
	        	   }
	           }
    	   }
    	   else {
    		   for(int j = 0; j < cols; j++) {
    			   if(j == 0)
    				   gameBoard[i][j] = "    " + Integer.toString(j); 
    			   else if(j == cols - 1)
    				   gameBoard[i][j] = "";
    			   else
    				   gameBoard[i][j] = "   " + Integer.toString(j);
    		   }
    	   }
       }
       return gameBoard;
   }//gameBoard

   private static boolean[][] populateBoard(){
	   mines = new boolean[rows+1][cols+1];
	   bombs = (int)Math.ceil(rows * cols * 0.25);
	   for(int i = 0; i < bombs; i++){
		   int width = ThreadLocalRandom.current().nextInt(0, rows);
		   int height = ThreadLocalRandom.current().nextInt(1,cols);
		   while(mines[width][height]){
			   width = ThreadLocalRandom.current().nextInt(0,rows);
			   height = ThreadLocalRandom.current().nextInt(1,cols);
		   }
		   mines[width][height] = true;
	   }
	   return mines;
   }//populateBoard
   private static void printBoard(){ 
	   System.out.println();
	   System.out.println("Rounds Completed: " + roundsCompleted++);
	   System.out.println();

       for(int i = 0; i < rows; i++){
           for(int j = 0; j < cols; j++){
        	   if(j == cols)
        		   continue;
        	   else
        		   System.out.print(board[i][j]);
           }
           System.out.println();
       }
       System.out.println();
       System.out.print("minesweeper-alpha$ ");
   }//printBoard
   private static void noFog(){
	   String[][] board2 = new String[rows][cols];
	   for(int x = 0; x < rows; x++){
		   for(int y = 0; y < cols; y++){
			board2[x][y] = board[x][y];   
		   }
	   }
	   for(int x = 0; x < rows; x++){
		   for(int y = 0; y < cols; y++){
			   if(y == 1 && board[x][y].equals("| F |") && mines[x][y] == true)
				   board2[x][y] = "|<F>|";
			   else if(board[x][y].equals(" F |") && mines[x][y] == true)
				   board2[x][y] = "<F>|"	;
			   else if(y == 1 && mines[x][y] == true)
				   board2[x][y] = "|< >|";
			   else if(mines[x][y] == true)
				   board2[x][y] = "< >|";
		   }
	   }
	   System.out.println();
	   System.out.println("Rounds Completed: " + roundsCompleted++);
	   System.out.println();

       for(int i = 0; i < rows; i++){
           for(int j = 0; j < cols; j++){
        	   if(j == cols)
        		   continue;
        	   else
        		   System.out.print(board2[i][j]);
           }
           System.out.println();
       }
       System.out.println();
       System.out.print("minesweeper-alpha$ ");
   }//noFog
   private static int neighbors(int row, int col){
	   int neighbors = 0;
	   
	   
	   if(row < rows) {
		   if(mines[row+1][col] == true)
			   neighbors++;
	   }
	   if(col < cols-1) {
		   if(mines[row][col+1] == true)
			   neighbors++;
	   }
	   if(row > 0) {
		   if(mines[row-1][col] == true)
			   neighbors++;
	   }
	   if(col > 0) {
		   if(mines[row][col-1] == true)
			   neighbors++;
	   }
	   if(row > 0 && col > 0) {
		   if(mines[row-1][col-1] == true)
			   neighbors++;
	   }
	   if(row < rows && col < cols-1) {
		   if(mines[row+1][col+1] == true)
			   neighbors++;
	   }
	   if(row < rows && col > 0) {
		   if(mines[row+1][col-1] == true)
			   neighbors++;
	   }
	   if(row > 0 && col < cols-1) {
		   if(mines[row-1][col+1] == true)
			   neighbors++;
	   }
	   
	   return neighbors;
   }//neighbors
   private static void response(String cmd, String row, String col){
	   cmd = cmd.trim().toLowerCase();
	   row = row.trim(); int rowNum = Integer.parseInt(row);
	   col = col.trim(); int colNum = Integer.parseInt(col);
	   colNum += 1;
	   switch(cmd){
	   case "guess" :
	   case "g" :
		   if(colNum == 0){
			   board[rowNum][colNum] = "| ? |";
			   printBoard();
			   
		   }
		   else if(rowNum >= 0 && rowNum <= rows && colNum >= 0 && colNum <= cols){
			   board[rowNum][colNum+1] = " ? |";
			   printBoard();
			   
		   }
		   else{
			   System.out.println("ಠ_ಠ  says, \"Your guess is not within the minefield!\"");
		   }
		   
		   break;
	   case "reveal" :
	   case "r" :
		   if(colNum == 1 && mines[rowNum][colNum] == false){
			   int neighbors = neighbors(rowNum, colNum);
			   board[rowNum][colNum] = "| " + String.valueOf(neighbors) + " |";
			   printBoard();
		   }
		   else if(rowNum >= 0 && rowNum <= rows && colNum >= 0 && colNum <= cols && mines[rowNum][colNum] == false){
			   int neighbors = neighbors(rowNum, colNum);
			   board[rowNum][colNum] = " " + String.valueOf(neighbors) + " |";
			   printBoard();
		   }
		   else if(mines[rowNum][colNum] == true){
			   gameOver();
		   }
		   break;
	   case "mark" :
	   case "m" : 
		   if(colNum == 1){
			   board[rowNum][colNum] = "| F |";
			   printBoard();
		   }
		   else if(rowNum >= 0 && rowNum <= rows && colNum >= 0 && colNum <= cols){
			   board[rowNum][colNum] = " F |";
			   printBoard();
		   }
	   	}   
   }//response
   private static void response(String cmd){
	   switch(cmd){
	   case "nofog" :
		   noFog();
		   break;
	   case "quit" :
	   case "q" :
		   quit();
		   break;
	   case "help" :
	   case "h" :
		   help();
		   
		   printBoard();
	   	}   
   }
  
   private static void help(){
	   System.out.println("\nCommands Available...\n- Reveal: r/reveal row col\n-   Mark: m/mark   row col\n-  Guess:"
	   		+ " g/guess  row col\n-   Help: h/help\n-   Quit: q/quit");
   }//help
   private static void quit(){
	   System.out.println("\n\nლ(ಠ_ಠლ))\nY U NO PLAY MORE?\nBye!\n");
	   System.exit(0);
   }//quit
   /**
    * Starts the game and execute the game loop.
    */
   public static void run() {
	   
	   Scanner kb = new Scanner(System.in);
	   startInterface();
	   boolean play = true;
	   board = gameBoard(rows, cols);
	   printBoard();
	   do{
		   String cmd = kb.next();
		   cmd = cmd.toLowerCase();
		   if(cmd.equals("mark") || cmd.equals("m") || cmd.equals("g") || cmd.equals("guess") || cmd.equals("r") || cmd.equals("reveal") ){
			   String row = kb.next(); 
			   String col = kb.next();
			   if(Integer.parseInt(row) < rows || Integer.parseInt(col) < cols || Integer.parseInt(row) >= 0 || Integer.parseInt(col) >= 0) {
				   response(cmd,row,col);
			   }
			   else {
				   System.out.println("\n\nಠ_ಠ  says, \"Your guess is out of bounds. Try Again.\"\n");
				   printBoard();
			   }
		   }
		   else if(cmd.equals("help") || cmd.equals("h") || cmd.equals("q") || cmd.equals("quit") || cmd.equals("nofog")){
			   response(cmd);
		   }
		   else{
			   System.out.println("\nಠ_ಠ  says, \"Command not recognized!\"");
			   printBoard();
		   }
		   kb.nextLine();
		   int count = 0;
		   for(int x = 0; x < rows; x++){
			   for(int y = 0; y < cols; y++){
				   if(y == 1 && board[x][y].equals("| F |") && mines[x][y] == true)
					   count++;
				   else if(board[x][y].equals(" F |") && mines[x][y] == true)
					   count++;
			   }
		   }
		   if(count == bombs)
			   playerWins();
	   }while(play == true);
	   
       

   } // run
   /**
    * The entry point into the program. This main method does implement some
    * logic for handling command line arguments. If two integers are provided
    * as arguments, then a Minesweeper game is created and started with a
    * grid size corresponding to the integers provided and with 10% (rounded
    * up) of the squares containing mines, placed randomly. If a single word
    * string is provided as an argument then it is treated as a seed file and
    * a Minesweeper game is created and started using the information contained
    * in the seed file. If none of the above applies, then a usage statement
    * is displayed and the program exits gracefully.
    *
    * @param args the shell arguments provided to the program
    */
   public static void main(String[] args) {
       /*
         The following switch statement has been designed in such a way that if
         errors occur within the first two cases, the default case still gets
         executed. This was accomplished by special placement of the break
         statements.
       */
       Minesweeper game = null;
    
       switch (args.length) {

       // random game
       case 2:

           int rows, cols;

           // try to parse the arguments and create a game
           try {
               rows = Integer.parseInt(args[0]);
               cols = Integer.parseInt(args[1]);
               game = new Minesweeper(rows, cols);
               break;
           } catch (NumberFormatException nfe) {
               // line intentionally left blank
           } // try

       // seed file game
       case 1:

           String filename = args[0];
           File file = new File(filename);

           if (file.isFile()) {
               game = new Minesweeper(file);
               break;
           } // if

       // display usage statement
       default:

           System.out.println("Usage: java Minesweeper [FILE]");
           System.out.println("Usage: java Minesweeper [ROWS] [COLS]");
           System.exit(0);

       } // switch

       // if all is good, then run the game
       run();
   } // main
   
}// class




