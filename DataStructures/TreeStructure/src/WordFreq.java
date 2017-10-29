
public class WordFreq {

	private String word;
	private int frequency;
	
	public String key(){
		return word;
	}
	public String toString(){
		return "Word :" + word + " appeared "+ frequency+ " times. ";
	}
	public int getfrequency(){return frequency;}
	public void setfrequency(int freq){
		this.frequency=freq;
	}
	
	public WordFreq(String word,int frequency){
		this.word=word;
		this.frequency=frequency;
	}
}
