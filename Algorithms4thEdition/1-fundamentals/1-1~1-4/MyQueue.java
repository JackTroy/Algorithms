

import java.util.Iterator;

public class MyQueue<Item> implements Iterable<Item> {
    private Node<Item> first;     
    private Node<Item> last;     
    private int N;                // size of the queue

    public MyQueue() {
        first = null;
        last = null;
        N = 0;
    }
    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;
    }
    public void enqueue(Item item){    //this or no this ??? 
        Node<Item> oldlast = this.last;
        this.last = new Node<Item>();
        this.last.item = item;
        this.last.next = null;
        if(isEmpty()){
        	this.first = this.last;
        }
        else{
        	oldlast.next = this.last;
        }
        this.N++;
    }   
    public Item dequeue(){
    	Item item = first.item;
    	first = first.next;
    	if(isEmpty()){
    		this.last = null;
    	}
    	this.N--;
    	return item;
    }

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	public Iterator<Item> iterator() {		
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		
		private Node<Item> current = first;
		@Override
		public boolean hasNext() {
			// TODO 自动生成的方法存根
			return current!=null;
		}

		@Override
		public Item next() {
			// TODO 自动生成的方法存根
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}

}
