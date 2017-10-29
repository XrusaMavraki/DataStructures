public class KeyFlight implements PQKey<KeyFlight> {

	public static KeyFlight[] allKeyFlights = new KeyFlight[1000];

	private int number,time;

	private int position;
	public static void initMethod(){
		for(int i=0;i<1000;i++){
			KeyFlight k= new KeyFlight();
			allKeyFlights[i]=k;
			allKeyFlights[i].setPos(-1);// we use -1 to show it is not yet in PQ;
			allKeyFlights[i].setNumber(i);
		}
	}
	public static KeyFlight getKeyFlight(int fnum){
		if(fnum>=0&&fnum<1000){
			return allKeyFlights[fnum];
		}
		else return null;
	}
	public void setPos(int pos){
		position=pos;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public int getPos(){
		return position;
	}


	public void setTime(int time){
		this.time=time;
	}
	public int getNumber(){return number;}
	public int getTime(){return time;}
	private KeyFlight(){};

	public int compareTo(KeyFlight k){
		if (this.getTime()<k.getTime()){
			return 1;
		}
		else if(this.getTime()>k.getTime()){
			return -1;
		}
		else {
			if(this.getNumber()<k.getNumber())return 1;
			else return -1;
			//the sings are like this because we want out data from smallest to biggest

		}


	}

	@Override
	public KeyFlight pushToTop() {
		this.setTime(-1);
		return this;
	}
}