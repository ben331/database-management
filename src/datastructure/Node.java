package datastructure;

public class Node<K extends Comparable<K>,V> {
	
	private V value;
	private K key;
	private int h1;
	private int h2;
	
	private Node<K,V> head;
	private Node<K,V> left;
	private Node<K,V> right;
	
	public Node(K key, V value) {
		super();
		this.value= value;
		this.key = key;
	}
	
	public Node(K key, V value, int BFactor) {
		super();
		this.value= value;
		this.key = key;
		this.h1=0;
		this.h2=0;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	
	public void setHead(Node<K, V> head) {
		this.head = head;
	}

	public V getValue() {
		return value;
	}

	public Node<K, V> getHead() {
		return head;
	}
	
	public Node<K,V> getMin() {
		Node<K,V> min = this;
		while(min.getLeft()!=null) {
				min=min.getLeft();
		}
		return min;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	public int getBFactor() {
		return h2-h1;
	}

	public int getH1() {
		return h1;
	}

	public void setH1(int h1) {
		this.h1 = h1;
	}

	public int getH2() {
		return h2;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getH() {
		return h1>h2? h1:h2;
	}
	public boolean isSheet() {
		return right==null && left==null;
	}
}
