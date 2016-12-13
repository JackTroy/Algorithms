

import java.util.Iterator;

public class MyBag<Item> implements Iterable<Item> {
    private Node<Item> first;     // top(order no matter) of bag
    private int N;                // size of the bag

    public MyBag() {
        first = null;
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
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	@Override
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
