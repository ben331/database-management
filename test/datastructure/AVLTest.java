package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTest {
	
	private AVL<Integer, String> tree;

	void setup1() {
		tree = new AVL<>();
	}
	
	void setup2() {
		tree = new AVL<>();
		Node<Integer, String> uno = new Node<>(1, "uno");
		tree.setRoot(uno);
	}
	
	void setup3() {
		tree = new AVL<>();
		Node<Integer, String> diez = new Node<>(10, "diez");
		Node<Integer, String> cuatro = new Node<>(4, "cuatro");
		Node<Integer, String> catorce = new Node<>(14, "catorce");
		
		tree.setRoot(diez);
		diez.setLeft(cuatro);
		diez.setRight(catorce);
		cuatro.setHead(diez);
		catorce.setHead(diez);
		
		diez.setH1(1);
		diez.setH2(1);
	}
		
	void setup4() {
		tree = new AVL<>();
		Node<Integer, String> once = new Node<>(11, "once");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> doce = new Node<>(12, "doce");
		Node<Integer, String> cinco = new Node<>(5, "cinco");
		Node<Integer, String> tres = new Node<>(3, "tres");
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		
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
		
		seis.setLeft(siete);
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
	
	void 
	@Test
	void test() {
		tree = new AVL<>();
		Node<Integer, String> ocho = new Node<>(8, "ocho");
		Node<Integer, String> seis = new Node<>(6, "seis");
		Node<Integer, String> siete = new Node<>(7, "siete");
		
		tree.setRoot(ocho);
		ocho.set
	}

}
