package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {
	
	private Node<Integer, String> node;
	
	void setup1() {
		node = new Node<>(1,"Uno");
	}
	
	void setup2() {
		node = new Node<>(2,"Dos");
		node.setHead(new Node<>(4, "Cuatro"));
		node.setLeft(new Node<>(1, "Uno"));
		node.setRight(new Node<>(3, "Tres"));
	}
	
	void setup3() {
		node = new Node<>(11,"Once");
		node.setLeft(new Node<>(6, "Seis"));
		node.setRight(new Node<>(12, "Doce"));
		node.getLeft().setLeft(new Node<>(5, "Cinco"));
		node.getLeft().setRight(new Node<>(8, "Ocho"));
		node.getLeft().getLeft().setLeft(new Node<>(3, "Tres"));
	}
	
	@Test
	void testGetters() {
		setup1();
		assertEquals(node.getValue(), "Uno");
		assertEquals(node.getKey(), 1);
	}
	
	@Test
	void testGettersAndSetters() {
		setup2();
		assertEquals(node.getValue(), "Dos");
		assertEquals(node.getKey(), 2);
		//Head
		assertEquals(node.getHead().getValue(), "Cuatro");
		assertEquals(node.getHead().getKey(), 4);
		//Left
		assertEquals(node.getLeft().getValue(), "Uno");
		assertEquals(node.getLeft().getKey(), 1);
		//Right
		assertEquals(node.getRight().getValue(), "Tres");
		assertEquals(node.getRight().getKey(), 3);
	}
	
	@Test
	void testGetMin1() {
		setup1();
		Node<Integer, String> min = node.getMin();
		
		assertFalse(min==null);
		assertEquals(min.getValue(), "Uno");
		assertEquals(min.getKey(), 1);
	}
	
	@Test
	void testGetMin2() {
		setup2();
		Node<Integer, String> min = node.getMin();
		
		assertFalse(min==null);
		assertEquals(min.getValue(), "Uno");
		assertEquals(min.getKey(), 1);
	}
	
	@Test
	void testGetMin3() {
		setup3();
		Node<Integer, String> min = node.getMin();
		
		assertFalse(min==null);
		assertEquals(min.getValue(), "Tres");
		assertEquals(min.getKey(), 3);
	}
}
