
public class Flight implements PQEntry<Flight, KeyFlight>{
	private boolean departs;
	private KeyFlight key;

	public KeyFlight getKey(){return key;}
	public void setDeparts(boolean d){
		departs=d;
	}
	public boolean getDeparts(){return departs;}
	public int compareTo(Flight a){
		return (this.key.compareTo( (KeyFlight) a.key));	
	}


	public Flight(boolean departs,KeyFlight key){
		setDeparts(departs);
		setKey(key);
	}
	public Flight(KeyFlight key){
		this(false,key);
	}

	@Override
	public void setKey(KeyFlight k) {
		this.key=k;	
	}
	public String toString(){
		if (this.departs){
			return "This flight has number " +this.getKey().getNumber() +" and departed at  " + String.format("%04d", this.getKey().getTime());
		}
		else {
			return "This flight has number " +this.getKey().getNumber() +" and departed at  " + String.format("%04d", this.getKey().getTime());
		}
	}


}
