package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTTest {
	
	private BST<Integer, String> tree;
	
	void setup1(){
		tree = new BST<>();
	}
	
	void setup2() {
		tree = new BST<>();
		tree.setRoot(new Node<>(1,"Uno"));
	}
	
	void setup3() {
		tree = new BST<>();
		tree.setRoot(new Node<>(10,"Diez"));
		
		Node<Integer, String> root = tree.getRoot();
		Node<Integer, String> cuatro = new Node<>(4, "Cuatro");
		Node<Integer, String> catorce = new Node<>(14, "Catorce");
		
		cuatro.setHead(root);
		catorce.setHead(root);
		root.setLeft(cuatro);
		root.setRight(catorce);
	}
	
	void setup4() {
		tree = new BST<>();
		tree.setRoot(new Node<>(11,"Once"));
		
		Node<Integer, String> root = tree.getRoot();
		Node<Integer, String> seis = new Node<>(6, "Seis");
		Node<Integer, String> doce = new Node<>(12, "Doce");
		Node<Integer, String> cinco = new Node<>(5, "Cinco");
		Node<Integer, String> ocho = new Node<>(8, "Ocho");
		Node<Integer, String> tres = new Node<>(3, "Tres");
		
		root.setLeft(seis);
		root.setRight(doce);
		seis.setHead(root);
		doce.setHead(root);
		
		seis.setLeft(cinco);
		seis.setRight(ocho);
		cinco.setHead(seis);
		ocho.setHead(seis);
		
		cinco.setLeft(tres);
		tres.setHead(cinco);
		
	}
	
	//test Insert method----------------------------------------------------------------------
	
	@Test
	void testInsert1() {
		setup1();
		tree.insertE(11, "Once");
		assertEquals(tree.getSize(),1);
		assertEquals(tree.getHeight(), 0);
		assertEquals(tree.getRoot().getValue(),"Once");
		assertEquals(tree.getRoot().getKey(),11);
	}
	
	@Test
	void testInsert2() {
		setup2();
		tree.insertE(7, "Siete");
		assertEquals(tree.getSize(),2);
		assertEquals(tree.getHeight(), 1);
		assertEquals(tree.getRoot().getRight().getValue(),"Siete");
		assertEquals(tree.getRoot().getRight().getKey(),7);
	}
	
	@Test
	void testInsert3() {
		setup3();
		tree.insertE(5, "Cinco");
		assertEquals(tree.getSize(),4);
		assertEquals(tree.getHeight(), 2);
		assertEquals(tree.getRoot().getLeft().getRight().getValue(),"Cinco");
		assertEquals(tree.getRoot().getLeft().getRight().getKey(),5);
	}
	
	@Test
	void testInsert4() {
		setup4();
		tree.insertE(7, "Siete");
		assertEquals(tree.getSize(),7);
		assertEquals(tree.getHeight(), 3);
		assertEquals(tree.getRoot().getLeft().getRight().getLeft().getValue(),"Siete");
		assertEquals(tree.getRoot().getLeft().getRight().getLeft().getKey(),7);
	}
	
	//Test of method searchE-------------------------------------------------------------
	
		@Test
		void testSearch1() {
			setup1();
			String value = tree.searchE(11);
			assertTrue(value==null);		
		}
		
		@Test
		void testSearch2() {
			setup2();
			String value = tree.searchE(1);
			assertEquals(value, "Uno");		
		}
		
		@Test
		void testSearch3() {
			setup2();
			String value = tree.searchE(4);
			assertEquals(value, null);		
		}
		
		@Test
		void testSearch4() {
			setup3();
			String value = tree.searchE(14);
			assertEquals(value, "Catorce");		
		}
		
		@Test
		void testSearch5() {
			setup4();
			String value = tree.searchE(3);
			assertEquals(value, "Tres");		
		}
	
	//test Remove method----------------------------------------------------------------------

	@Test
	void testRemove1() {
		setup2();
		assertTrue(tree.removeE(1));
		assertEquals(0,tree.getSize());
		assertEquals(tree.getHeight(), 0);
		assertTrue(tree.getRoot()==null);
	}
	
	@Test
	void testRemove2() {
		setup3();
		assertTrue(tree.removeE(4));
		assertEquals(tree.getSize(),2);
		assertEquals(tree.getHeight(), 1);
		assertTrue(tree.getRoot().getLeft()==null);
	}

	@Test
	void testRemove3() {
		setup3();
		assertTrue(tree.removeE(10));
		assertEquals(tree.getSize(),2);
		assertEquals(tree.getHeight(), 1);
		assertEquals(tree.getRoot().getValue(), "Catorce");
		assertEquals(tree.getRoot().getKey(), 14);
	}
	
	@Test
	void testRemove4() {
		setup4();
		assertTrue(tree.removeE(5));
		assertEquals(tree.getSize(),5);
		assertEquals(tree.getHeight(), 2);
		assertEquals(tree.getRoot().getLeft().getLeft().getValue(), "Tres");
		assertEquals(tree.getRoot().getLeft().getLeft().getKey(), 3);
	}
	
	@Test
	void testRemove5() {
		setup4();
		assertTrue(tree.removeE(6));
		assertEquals(tree.getSize(),5);
		assertEquals(tree.getHeight(), 3);
		assertEquals(tree.getRoot().getLeft().getValue(), "Ocho");
		assertTrue(tree.getRoot().getLeft().getRight()==null);
	}
}
