package datastructure;

public interface IBST<K extends Comparable<K>, V> {
	
	public void insertE(K key, V value);
	public boolean removeE(K key);
	public Node<K,V> getRoot();
	public V searchE(K key);
	public int getSize();
	public int getHeight();
	public String inOrden();
	public String postOrden();
	public String preOrden();
}
