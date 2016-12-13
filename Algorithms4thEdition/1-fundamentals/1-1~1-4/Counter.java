

public class Counter {
	private int count=0;
	private String name="count";
	public Counter(String s){
		this.name=s;
	}
	public Counter(){
	}
	public void increment(){
		this.count++;
	}
	public int getCount(){
		return this.count;
	}
	public String toString(){
		return count+name;
	}
}