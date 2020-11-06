

// ¡Read the lines 354 before to run!

package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTest {
	
	private AVL<Integer, String> tree;
	
	
	//Setups----------------------------------------------------------------------------------------------
	void setup1() {
		tree = new AVL<>();
		Node<Integer, String> once = new Node<>(11, "once");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> doce = new Node<>(12, "doce");
		Node<Integer, String> cinco = new Node<>(5, "cinco");
		Node<Integer, String> tres = new Node<>(3, "tres");
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> quince = new Node<>(15, "quince");
		
		tree.setRoot(once);
		
		once.setLeft(seis);
		once.setRight(doce);
		seis.setHead(once);
		doce.setHead(once);
		
		seis.setLeft(cinco);
		seis.setRight(ocho);
		cinco.setHead(seis);
		ocho.setHead(seis);
		
		cinco.setLeft(tres);
		tres.setHead(cinco);
		
		doce.setRight(quince);
		quince.setHead(doce);
		
		once.setH1(3);
		once.setH2(2);
		seis.setH1(2);
		seis.setH2(1);
		doce.setH2(1);
		cinco.setH1(1);
	}
	
	void setupA1() {
		tree = new AVL<>();
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> cuatro = new Node<>(4, "cuatro");
		Node<Integer, String> seis = new Node<>(6, "seis");
		
		tree.setRoot(dos);
		dos.setRight(cuatro);
		cuatro.setRight(seis);
		seis.setHead(cuatro);
		cuatro.setHead(dos);
		
		dos.setH2(2);
		cuatro.setH2(1);
	}
	
	void setupA2() {
		tree = new AVL<>();
		Node<Integer, String> uno = new Node<>(1, "uno");
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> tres = new Node<>(3, "tres");
		Node<Integer, String> cuatro = new Node<>(4, "cuatro");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> siete = new Node<>(7, "siete");
		
		tree.setRoot(dos);
		dos.setLeft(uno);
		dos.setRight(cuatro);
		uno.setHead(dos);
		cuatro.setHead(dos);
		
		cuatro.setLeft(tres);
		cuatro.setRight(seis);
		tres.setHead(cuatro);	
		seis.setHead(cuatro);
		
		seis.setRight(siete);
		siete.setHead(seis);
		
		dos.setH1(1);
		dos.setH2(3);
		cuatro.setH1(1);
		cuatro.setH2(2);
		seis.setH2(1);
	}
	
	void setupB() {
		tree = new AVL<>();
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> tres = new Node<>(3, "tres");
		Node<Integer, String> cuatro = new Node<>(4, "cuatro");
		Node<Integer, String> seis = new Node<>(6, "seis");
		
		tree.setRoot(dos);
		dos.setRight(cuatro);
		cuatro.setHead(dos);
		
		cuatro.setLeft(tres);
		cuatro.setRight(seis);
		tres.setHead(cuatro);
		seis.setHead(cuatro);
		
		dos.setH2(2);
		cuatro.setH1(1);
		cuatro.setH2(1);
	}
	
	void setupC() {
		tree = new AVL<>();
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> tres = new Node<>(3, "tres");
		Node<Integer, String> cuatro = new Node<>(4, "cuatro");
		
		tree.setRoot(dos);
		dos.setRight(cuatro);
		cuatro.setHead(dos);
		
		cuatro.setLeft(tres);
		tres.setHead(cuatro);
		
		dos.setH2(2);
		cuatro.setH1(1);
	}
	
	void setupD1() {
		tree = new AVL<>();
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> seis = new Node<>(6, "seis");
		
		tree.setRoot(ocho);
		ocho.setLeft(seis);
		seis.setLeft(dos);
		
		seis.setHead(ocho);
		dos.setHead(seis);
		
		ocho.setH1(2);
		seis.setH1(1);
	}
	
	void setupD2() {
		tree = new AVL<>();
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> uno = new Node<>(1, "uno");
		Node<Integer, String> nueve= new Node<>(9, "nueve");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> siete = new Node<>(7, "siete");
		
		tree.setRoot(ocho);
		ocho.setLeft(seis);
		ocho.setRight(nueve);
		seis.setHead(ocho);
		nueve.setHead(ocho);
		
		seis.setLeft(dos);
		dos.setHead(seis);
		seis.setRight(siete);
		siete.setHead(seis);
		
		dos.setLeft(uno);
		uno.setHead(dos);
		
		ocho.setH2(1);
		ocho.setH1(3);
		seis.setH2(1);
		seis.setH1(2);
		dos.setH1(1);
	}
	
	void setupE() {
		tree = new AVL<>();
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> dos = new Node<>(2, "dos");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> siete = new Node<>(7, "siete");
		
		tree.setRoot(ocho);
		ocho.setLeft(seis);
		seis.setHead(ocho);
		
		seis.setLeft(dos);
		dos.setHead(seis);
		seis.setRight(siete);
		siete.setHead(seis);
		
		ocho.setH1(2);
		seis.setH1(1);
		seis.setH2(1);
	}
	
	void setupF() {
		tree = new AVL<>();
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> siete = new Node<>(7, "siete");
		
		tree.setRoot(ocho);
		ocho.setLeft(seis);
		seis.setRight(siete);
		
		seis.setHead(ocho);
		siete.setHead(seis);
		
		seis.setH2(1);
		ocho.setH1(2);
	}
	
	//Test----------------------------------------------------------------------------------------------
	
	@Test
	void testLeftRotateCaseA1() {
		setupA1();
		tree.leftRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		
		assertEquals(4, root.getLeft().getHead().getKey());
		assertTrue(root.getHead()==null);
		
		assertEquals(4,root.getKey());
		assertEquals(2, root.getLeft().getKey());
		assertEquals(6, root.getRight().getKey());
	}
	
	@Test
	void testLeftRotateCaseA2() {
		
		setupA2();
		tree.leftRotate(tree.getRoot());
		
		Node<Integer, String> root = tree.getRoot();
		Node<Integer, String> left = root.getLeft();
		Node<Integer, String> right = root.getRight();
		
		assertTrue(root.getHead()==null);
		assertEquals(4,root.getKey());
		assertEquals(2,left.getKey());
		assertEquals(6,right.getKey());
		
		assertEquals(1,left.getLeft().getKey());
		assertEquals(3,left.getRight().getKey());
		assertEquals(7,right.getRight().getKey());
		assertTrue(right.getLeft()==null);
		assertEquals(4, left.getHead().getKey());
	}
	
	@Test
	void testLeftRotateCaseB() {
		setupB();
		tree.leftRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		assertTrue(root.getHead()==null);
		assertTrue(root.getLeft().getLeft()==null);
		
		assertEquals(4,root.getKey());
		assertEquals(2,root.getLeft().getKey());
		assertEquals(6,root.getRight().getKey());
		assertEquals(3,root.getLeft().getRight().getKey());
		assertEquals(4,root.getLeft().getHead().getKey());		
	}
	
	void testRightRotateCaseD1() {
		setupD1();
		tree.rightRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		
		assertEquals(6, root.getLeft().getHead().getKey());
		assertTrue(root.getHead()==null);
		
		assertEquals(6,root.getKey());
		assertEquals(2, root.getLeft().getKey());
		assertEquals(8, root.getRight().getKey());
	}
	
	void testLeftRotateCaseD2() {
		setupD2();
		tree.leftRotate(tree.getRoot());
		
		Node<Integer, String> root = tree.getRoot();
		Node<Integer, String> left = root.getLeft();
		Node<Integer, String> right = root.getRight();
		
		assertTrue(root.getHead()==null);
		assertEquals(6,root.getKey());
		assertEquals(2,left.getKey());
		assertEquals(8,right.getKey());
		
		assertEquals(1,left.getLeft().getKey());
		assertEquals(7,right.getLeft().getKey());
		assertEquals(9,right.getRight().getKey());
		assertTrue(left.getRight()==null);
		assertEquals(4, left.getHead().getKey());
	}
	
	@Test
	void testRightRotateCaseE() {
		setupE();
		tree.rightRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		assertTrue(root.getHead()==null);
		assertTrue(root.getRight().getRight()==null);
		
		assertEquals(6,root.getKey());
		assertEquals(2,root.getLeft().getKey());
		assertEquals(8,root.getRight().getKey());
		assertEquals(7,root.getRight().getLeft().getKey());
		assertEquals(6,root.getLeft().getHead().getKey());		
	}
	
	//Refresh balance factor---------------------------------------------------------
	
	
	//Rotations with Balance factor update-------------------------------------------
	
	@Test
	void testLeftRotateCaseC() {
		setupC();
		tree.leftRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		assertTrue(root.getHead()==null);
		assertEquals(3,root.getKey());
		assertEquals(2,root.getLeft().getKey());
		assertEquals(4,root.getRight().getKey());
		assertTrue(root.getLeft().getRight()==null);		
	}
	
	@Test
	void testRightRotateCaseF() {
		setupF();
		tree.rightRotate(tree.getRoot());
		Node<Integer, String> root = tree.getRoot();
		assertTrue(root.getHead()==null);
		assertEquals(7,root.getKey());
		assertEquals(6,root.getLeft().getKey());
		assertEquals(8,root.getRight().getKey());
		assertTrue(root.getRight().getLeft()==null);		
	}
	
	//Test insert-delete  + Refresh Heights--------------------------------------------
	
	/*This test only functions commenting the lines: 40, 46 of the class AVL, where method balance is the called.
							(This method is tested in the next section)*/
	
	@Test
	void testInsertAndRefresh() {
		setup1();
		tree.insertE(4, "cuatro");
		Node<Integer, String> current;
		
		current = tree.getRoot();
		assertEquals(-2, current.getBFactor());
		current = current.getLeft();
		assertEquals(-2, current.getBFactor());
		current = current.getLeft();
		assertEquals(-2, current.getBFactor());
		current = current.getLeft();
		assertEquals(1, current.getBFactor());
		current = current.getRight();
		assertEquals(0, current.getBFactor());
	}
	
	@Test
	void testRemoveAndRefresh() {
		setup1();
		tree.removeE(11);
		Node<Integer, String> root = tree.getRoot();
		
		assertEquals(12, root.getKey());
		assertEquals(-2, root.getBFactor());
		assertEquals(15, root.getRight().getKey());
		assertTrue(root.getRight().getRight()==null);
	}
	
	//Test insert-delete  + Balance--------------------------------------------
		
	@Test
	void testInsertAndBalance() {
		setup1();
		tree.insertE(4, "cuatro");
		tree.rebalance();
		
		Node<Integer, String> current;
		
		current = tree.getRoot();
		assertEquals(11, current.getKey());
		assertEquals(-1, current.getBFactor());
		
		current = current.getLeft();
		assertEquals(-1, current.getBFactor());
		assertEquals(6, current.getKey());
		
		current = current.getLeft();
		assertEquals(0, current.getBFactor());
		assertEquals(4, current.getKey());
		
		assertEquals(0, current.getLeft().getBFactor());
		assertEquals(3, current.getLeft().getKey());
		
		assertEquals(0, current.getRight().getBFactor());
		assertEquals(5, current.getRight().getKey());
		
		current = tree.getRoot().getRight();
		assertEquals(1, current.getBFactor());
		assertEquals(12, current.getKey());
		
		current = current.getRight();
		assertEquals(0, current.getBFactor());
		assertEquals(15, current.getKey());
	}
	
	@Test
	void testRemoveAndBalance() {
		setup1();
		tree.removeE(11);
		tree.rebalance();
		
		Node<Integer, String> current;
		
		current = tree.getRoot();
		assertEquals(0, current.getBFactor());
		assertEquals(6, current.getKey());
		
		current = current.getLeft();
		assertEquals(-1, current.getBFactor());
		assertEquals(5, current.getKey());
		
		current = current.getLeft();
		assertEquals(0, current.getBFactor());
		assertEquals(3, current.getKey());
		
		current = tree.getRoot().getRight();
		assertEquals(0, current.getBFactor());
		assertEquals(12, current.getKey());
		
		assertEquals(0, current.getLeft().getBFactor());
		assertEquals(8, current.getLeft().getKey());
		
		assertEquals(0, current.getRight().getBFactor());
		assertEquals(15, current.getRight().getKey());
	}
}
