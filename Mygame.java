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
	

	public static boolean no_ships_on_board(String ship_symbol,String[][]Tablero){

        boolean ret = true;

		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				if(Tablero[i][j].equals(ship_symbol)){
                    //System.out.println("I still found a ship");
                    //System.out.println("row = " + i );
                    //System.out.println("column = " + j);
			        ret = false;
                    return ret;
				}
			} 
		}
	    return ret;
	}
    public static String row_conversion(String row){
		int new_row = 0;
		new_row = Integer.parseInt(row)-1;
		return String.valueOf(new_row);
    }

    public static String column_conversion(String column){
			String[] letras_arreglo={"A","B","C","D","E","F","G","H","I","J"};
    			    for(int x = 0; x < letras_arreglo.length; x++){
				if (letras_arreglo[x].equals( column)){
					return String.valueOf(x);
				}
			    }
		return String.valueOf(0);
    }

    public static void main(String[] args) throws Exception{

    // variables
    boolean first_player_asigned = false;
	String[] Players = {"Player","CPU"};
	String row,column = "0";
	String ship_symbol="S";
    
    // Buffer Reader
    InputStreamReader isr = new  InputStreamReader (System.in);
    BufferedReader br = new BufferedReader(isr);
   
    // Welcome message & get user name
    System.out.println("Welcome, please write your name" );
    String name_player = br.readLine();
   	String menu_option;
    Myplayer p1 = new Myplayer(name_player);
  
    // Create boats && ships boards
    Myboard player_board_ships = new Myboard();
    Myboard cpu_board_ships = new Myboard();
    Myboard player_board_missiles = new Myboard();
    Myboard cpu_board_missiles = new Myboard();
    
    // Generate the ships random inside the board
    player_board_ships = Generate_boats_random(player_board_ships);
    cpu_board_ships = Generate_boats_random(cpu_board_ships);
    
	while(true){
       
            // ask the user for a valid option
			menu_option = Menu(br);
            if (menu_option.equals("a")){
                while(true){

                    //check that player win
                    if (no_ships_on_board(ship_symbol,player_board_ships.Tablero)) {
                        System.out.println(name_player + " you loose");
                        break;
                    }

                    //check that cpu win
                    if (no_ships_on_board(ship_symbol,cpu_board_ships.Tablero)){
                        System.out.println(name_player + " you win");
	                    p1.declare_winner();
                        break;
                    }
                

                    // Start to play
                    System.out.println(name_player+" Vs CPU: Let's play.");

                    // Asign the first player
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
           

           		    // Ask row and column pos of player
	
          	  	    System.out.println(name_player + " choose Row ");
          	  	    row = br.readLine();
			    row = row_conversion(row);
                
          	 	    System.out.println(name_player + " choose Column");
          	  	    column = br.readLine();
			    column = column_conversion(column);

                    // Play with player cordinates
          	  	    play_player(player_board_missiles,cpu_board_ships,Integer.parseInt(row),Integer.parseInt(column));
          	  	    print_boards(name_player,player_board_ships,player_board_missiles);
            
                    // Play as CPU in random
          	  	    play_cpu(cpu_board_missiles,player_board_ships);
          	  	    print_boards("CPU",cpu_board_ships,cpu_board_missiles);

                }
            }
		  	else if(menu_option.equals("b")){
				System.out.println(p1.get_name()+" your score is: "+p1.get_record());
		 	}

            else if (menu_option.equals("c") == true ){
                System.out.println("Good Bye");
                break;
            }
        }
	
    }


}

