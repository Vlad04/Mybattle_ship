import java.util.Random;
	
public class Myboats_generator{
	//atributos
	private Myboard board;
	private String ship_symbol="S";
	
	//getter
	
	public Myboard get_board(){
		return this.board;
	}
	//constructor
	public Myboats_generator(Myboard board){
		this.board=board;
	}
	//boolean check_fit (revisa si el barco generado cabe dentro del board y que no se encimen los barcos)
	boolean check_fit(int row, int column, int type){
		
		boolean ret=true;
			try{							//se usa para preveer excepciones en la ejecucion del programa.
				for(int i=0 ; i < type + 1 ; i++){		//i=0, y si i es menor al tipo de barco+1(digamos 2=2+1=3),suma 1
					if(board.Tablero[row][column+i]!="~"){ //si el tablero en fila dada y(columna dada+i) es diferente a "~"
						ret=false;                      //entonces ret(booleano) es falso
						break;				//se rompe el ciclo
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){		//da a conocer la excepcion 
				
				ret=false;					//donde ret es false, queremos evitarlo
			}
		return ret;							//regresa a ret
	}
	
    	public boolean generate_ship(int row,int column,int type){
	
	boolean ret1,ret2,ret3,ret4=false;
	
		//System.out.println("Type = " + type);
		if(!check_fit(row,column,type)){
			return false;
		}
	
		else if(type==1){
				
	
			ret1=board.mark(row,column,ship_symbol);
			ret2=board.mark(row,column+1,ship_symbol);
			System.out.println(board.toString());
		
			return(ret1 && ret2);		
	
		}
		else if(type==2){
			
				
			ret1=board.mark(row,column,ship_symbol);
			ret2=board.mark(row,column+1,ship_symbol);
			ret3=board.mark(row,column+2,ship_symbol);
			System.out.println(board.toString());
			
			return(ret1 && ret2 && ret3);				
	
		}
		else if(type==3){
				
			ret1=board.mark(row,column,ship_symbol);
			ret2=board.mark(row,column+1,ship_symbol);
			ret3=board.mark(row,column+2,ship_symbol);
			ret4=board.mark(row,column+3,ship_symbol);
			System.out.println(board.toString());
		
			return(ret1 && ret2 && ret3 && ret4);
		}
		else{
			return false;
	
		}
		
	}
	
	
	public static void main(String[]args){
		
		Random r=new Random();
		Myboard mb=new Myboard();
		
	
		Myboats_generator mbg=new Myboats_generator(mb);
	
		int row,column,type;
		int counter=0;
	
		while(counter<5){
			row=r.nextInt(4) + 1;
			column=r.nextInt(8) + 2;
			type=r.nextInt(9) + 0;
	
			System.out.println("");
			System.out.println(row);
			System.out.println(column);
			System.out.println(type);
	
			if(mbg.generate_ship(row,column,type)){
				counter=counter+1;
			}
			counter++;
		}
	
		mb=mbg.get_board();
		
	
		//System.out.println(mb.toString());
	
	}
}	
	
	
	  
	
