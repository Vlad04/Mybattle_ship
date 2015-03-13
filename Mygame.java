import java.util.Random;
import java.io.*;

public class Mygame{
	
	public static int play(Myboard enemy_board_ships, Myboard board_missiles, int row, int column){

		if(column > 9 || row > 9){
			System.out.println("Out of limit");
			return -1;
		}

		if(board_missiles.Tablero[row][column]=="*" || board_missiles.Tablero[row][column]=="$"){

			System.out.println("Hit before");
			return -1;
		}

		if(enemy_board_ships.Tablero[row][column]=="S"){
			System.out.println("You hit !!!");
			enemy_board_ships.Tablero[row][column] = "h";
			board_missiles.Tablero[row][column] = "$";
			return 0;
		}

			else{

				System.out.println("You miss !!!");
				enemy_board_ships.Tablero[row][column] = "!";
				board_missiles.Tablero[row][column] = "*";
				return 1;	
			}
    }

	public static void play_cpu(Myboard board_missiles,Myboard enemy_board_ships){
		Random Random=new Random();
		int row = Random.nextInt(9)+0;
		int column = Random.nextInt(9)+0;
		
		play(enemy_board_ships,board_missiles, row, column);
	}

	public static void play_player(Myboard board_missiles,Myboard enemy_board_ships, int row, int column){
	
		play(enemy_board_ships, board_missiles, row, column);

	}

	public static void print_boards(String owner, Myboard board_ships, Myboard board_missiles){
	 
		System.out.println("");	
		System.out.println(owner);
		System.out.println("Ship Board");
		System.out.println(board_ships.toString());


		System.out.println("");
		System.out.println(owner);
		System.out.println("Missile Board");
		System.out.println(board_missiles.toString());

	}

	public static Myboard Generate_boats_random(Myboard board){

		Random rand=new Random();
		Myboats_generator mbg=new Myboats_generator(board);
	
		int row_player;
		int column_player;
		int type_player;
		int counter=0;

			while(counter<5){
				row_player=rand.nextInt(9) + 0;
				column_player=rand.nextInt(9) + 0;
				type_player=rand.nextInt(3) + 1;
	
			
				if(mbg.generate_ship(row_player,column_player,type_player)){
					counter=counter+1;
				}
			
			}
	
		board=mbg.get_board();
		return board;
	
		//System.out.println(board.toString());
	}
	

	public static int Random(int Maximo,int Minimo){
	
		Random rand=new Random();
		int x = rand.nextInt(Maximo) + Minimo;
		return x;

	}

	public static String Menu(BufferedReader br)throws Exception{
	
		System.out.println("Choose one option");
		System.out.println("a)Play game.");
		System.out.println("b)See for the scoreboard.");
		System.out.println("c)Quit");
	
	String option=br.readLine();
	return option;

	}
	

	public static boolean is_winner(String ship_symbol,String[][]Tablero){
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				if(Tablero[i][j].equals(ship_symbol)){
					return false;
				}
			} 
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{

    
    		boolean first_player_asigned = false;
    		String[] Players = {"Player","CPU"};
    		String row,column = "0";
    
    
    		InputStreamReader isr = new  InputStreamReader (System.in);
    		BufferedReader br = new BufferedReader(isr);
   
   
    		System.out.println("Welcome, please write your name" );
    		String name_player = br.readLine();
   	 	String menu_option = Menu(br);
    

       
    		Myplayer p1 = new Myplayer(name_player);
    
   
    		Myboard player_board_ships = new Myboard();
    		Myboard cpu_board_ships = new Myboard();
    		Myboard player_board_missiles = new Myboard();
    		Myboard cpu_board_missiles = new Myboard();
    
    
    		player_board_ships = Generate_boats_random(player_board_ships);
    		cpu_board_ships = Generate_boats_random(cpu_board_ships);
    
    		Myboard mb=new Myboard();
		String ship_symbol="S";
		while(menu_option.equals("c") == false || (is_winner(ship_symbol,mb.Tablero)==false)){

  	     		if(menu_option.equals("a")){
				
        	   		System.out.println(name_player+" Vs CPU: Let's play.");
        	   			if (!first_player_asigned){
        	        			if (Random(1000,1)%2 == 0 ){
                    
        	            				System.out.println( name_player + " starts first ");
        	            				print_boards(name_player,player_board_ships,player_board_missiles);
        	   				}
                   	else{
                    
                   		 System.out.println("CPU starts first");
                   		 play_cpu(cpu_board_missiles,player_board_ships);
                   		 print_boards("CPU",cpu_board_ships,cpu_board_missiles);
                   	}
                		 first_player_asigned = true;
                   			}
           

           		
          	  	System.out.println(name_player + " choose Row ");
          	  	row = br.readLine();
                
          	 	System.out.println(name_player + " choose Column");
          	  	column = br.readLine();

           
          	  	play_player(player_board_missiles,cpu_board_ships,Integer.parseInt(row),Integer.parseInt(column));
          	  	print_boards(name_player,player_board_ships,player_board_missiles);
            
           
          	  	play_cpu(cpu_board_missiles,player_board_ships);
          	  	print_boards("CPU",cpu_board_ships,cpu_board_missiles);
                    
				   menu_option = Menu(br);
	          	}
				
		  	else if(menu_option.equals("b")){
				System.out.println(p1.get_name()+" your score is: "+p1.get_record());
				menu_option = Menu(br);				
		 	}
		   	else{
				menu_option = Menu(br);
			}
		
		}
	
    	}


}

