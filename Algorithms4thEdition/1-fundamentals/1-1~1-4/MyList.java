

import java.util.Iterator;


public class MyList<Item> implements Iterable<Item>{

    private Node<Item> first;     // top of stack
    @SuppressWarnings("unused")
	private Node<Item> last;     // end of stack
    private int N;                // size of the queue

    public MyList() {
        first = null;
        last = null;
        N = 0;
    }
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;
    }
    public void add(Item item){
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
        if(N==1)	last=first;
        else oldfirst.previous = first;
    }
    public void removeAfter(Node<Item> point){
    	Node<Item> temp=first;
    	while(temp.item!=point.item){
    		if(temp.next!=null)	temp=temp.next;
    		else break;
    	}
    	if(temp.item!=point.item) temp.next=null;
    }
    public void insertAfter(Node<Item> after_node,Node<Item> insert_node){  	
    	Node<Item> temp=first;
    	while(temp.item!=after_node.item){
    		temp=temp.next;
    	}
    	if(temp!=null){
    		insert_node.next=temp.next;
    		temp.next=insert_node;
    	}
    	
    }
    public void remove(MyList<Item> list,String key){
    	Node<Item> former_temp=list.first;
    	Node<Item> later_temp=list.first.next;
    	while(list.first.item==key){
    		list.first=list.first.next;
    		later_temp=list.first.next;//this method lacks of judge the N of list//double side list kill it
    	}
    	while(later_temp!=null){
    		if(later_temp.item==key){
    			former_temp.next=later_temp.next;
    		}
    		former_temp.next=later_temp;
    		later_temp=later_temp.next;//this method lacks of judge the N of list//double side list kill it and it needs changes
    	}
    }
    public void MoveToFront(Item[] input){
    	int len = input.length,flag;
    	for(int i=0;i<len;i++){
    		flag = 0;
    		Item x = input[i];
    		Node<Item> temp = first;
    		while(temp!=null){
    			if(temp.item==x){
    				if(temp!=first){
    					temp.previous.next=temp.next;
    					this.add(x);
    				}
    				flag = 1;
    			}
    			temp = temp.next;
    		}
    		if(flag==0){
    			this.add(x);
    		}
    	}		
    }    	
	public static void main(String[] args) {
		
		// TODO 自动生成的方法存根
		Integer[] a={1,2,2,1,3,4,5,6};
		MyList<Integer> test = new MyList<Integer>();
		test.MoveToFront(a);
		for(Integer num:test){
			System.out.println(num);
		}
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO 自动生成的方法存根
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

