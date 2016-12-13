

import java.util.Iterator;

public class MyStack<Item> implements Iterable<Item>{
    private Node<Item> first;     // top of stack
    private int N;                // size of the stack

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    public MyStack() {
        first = null;
        N = 0;
    }
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
    	Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }
    public Item peek(){
    	return first.item;
    }
	private class ListIterator implements Iterator<Item>{
		private Node<Item> current = first;

		public boolean hasNext(){ return current != null;}
		public Item next() {
			Item item = current.item;
            current = current.next; 
            return item;
		}		
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	public MyStack<String> copy(){
		MyStack<String> temp = new MyStack<String>();
		MyStack<String> copy = new MyStack<String>();
		if(!this.isEmpty()){
			for(Item s:this){
				temp.push((String) s);
			}
			for(String s:temp){
				copy.push((String) s);
			}
		}
		return copy;
	}
	public static MyStack<String> copy(MyStack<String> from){
		MyStack<String> temp = new MyStack<String>();
		MyStack<String> copy = new MyStack<String>();
		if(!from.isEmpty()){
			for(String s:from){
				temp.push((String) s);
			}
			for(String s:temp){
				copy.push((String) s);
			}
		}
		return from;		
	}
	public static void main(String[] args){
		// TODO 自动生成的方法存根
		MyStack<String> origin = new MyStack<String>();
		for(int i=0;i<10;i++){
			origin.push(i+"");
		}
		MyStack<String> copy = new MyStack<String>();
		copy=origin.copy();
		for(int i=0;i<10;i++){
			System.out.print(copy.pop());
		}
		System.out.print('\n');
		System.out.print(origin.N);
		for(String s:origin){
			System.out.print(s);
		}
	}
}