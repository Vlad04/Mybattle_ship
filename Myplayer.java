public class Myplayer{

	private String name;
	private int record;

	public Myplayer(String name){
		this.name=name;
		this.record=0;
	}

	public String get_name(){
		return this.name;
	}

	public int get_record(){
		return this.record;
	}
	private void set_name(String name){
		this.name=name;
	}

	private void set_record(int record){
		this.record=record;
	}
	
	public void declare_winner(){
		this.record++;
	}
	
	public static void main(String[]args){
	Myplayer mp=new Myplayer("Vladimir");
	String a=mp.get_name();
	int b=mp.get_record();
	mp.set_record(1);
	mp.declare_winner();
	b=mp.get_record();
	System.out.println(a);
	System.out.println(b);
	}
}
