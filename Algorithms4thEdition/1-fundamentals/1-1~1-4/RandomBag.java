

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomBag<Item> implements Iterable<Item> {
	private Item[] array;
	private int size,length;
	@SuppressWarnings("unchecked")
	public RandomBag(){
		size = 0;
		length = 100;
		array = (Item[]) new Object[length];
	}
    public boolean isEmpty() {
        return array == null;
    }
    public int size() {
        return size;
    }
    public void add(Item item){
    	if(size==length/2){
    		length*=2;
    		@SuppressWarnings("unchecked")
			Item[] temp = (Item[]) new Object[length];
    		for(int i=0;i<size;i++){
    			temp[i]=array[i];
    		}
    		array=temp;
    	}
    	array[size]=item;
    	size++;
    }
	public Iterator<Item> iterator() {		
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private int N = 0;
		@Override
		public boolean hasNext() {
			// TODO 自动生成的方法存根
			return array[N]!=null;
		}

		@Override
		public Item next() {
			// TODO 自动生成的方法存根
			
			//normal way
			//Item temp = array[N];
			//N++;
			//return temp;
			
			//random way
			Item temp = array[N];
			int random_N=(int) (StdRandom.uniform()*(size-N-1))+N;
			array[N] = array[random_N];
			array[random_N] = temp;
			return array[N++];
		}
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		RandomBag<Integer> Test = new RandomBag<Integer>();
		for(int i=0;i<75;i++){
			Test.add(i);
		}
		for(Integer num:Test){
			System.out.print(num+" ");
		}
	}

}
