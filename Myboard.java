public class Myboard{

	public String[][]Tablero;

	public Myboard(){
		this.Tablero=new String[10][10];
	
		
		for(int i=0;i<Tablero.length;i++){
			for(int j=0;j<Tablero.length;j++){
				Tablero[i][j]="~";
			}
		}
	}	
	public boolean mark(int row, int column, String symbol){

		if(row>10){
			return false;
		}

		if(column>10){
			return false;
		}

		if(this.Tablero[row][column]!=symbol){
			this.Tablero[row][column]=symbol;
			return true;
		}
			else{
				return false;
			}

	}

	public String toString(){

		String str_tablero="";
			
			for(int fila=0;fila<Tablero.length;fila++){
				for(int columna=0;columna<Tablero.length;columna++){
						str_tablero+=Tablero[fila][columna]+" ";
				}
			str_tablero+="\n";
			}
		return str_tablero;
	}

	public static void main(String[]args){

		Myboard b=new Myboard();
		System.out.println(b.mark(5,5,"X"));
		System.out.println();
		System.out.println(b.mark(2,2,"V"));
		System.out.println();
		System.out.println(b.toString());
	}




} 
