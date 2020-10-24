package datastructure;

public class AVL<K extends Comparable<K>,V> extends BST<K,V> implements IAVL<K,V>{
	
	@Override
	public void insertE(K key, V value) {
		

		Node<K,V> element = new Node<K,V>(key, value);
		
		Node<K,V> current = getRoot();
		
		if(getRoot() == null) {
			setRoot(element);
		}else {
			
			boolean added = false;
			while(!added) {
				
				if(current.getKey().compareTo(element.getKey())>0) {
					if(current.getLeft()==null) {
						current.setLeft(element);
						element.setHead(current);
						added = true;
					}else {
						current = current.getLeft();
					}
				}else {
					if(current.getRight()==null) {
						current.setRight(element);
						element.setHead(current);
						added = true;
					}else {
						current = current.getRight();
					}
				}
			}
		}
		
		refreshHeights(element.getHead(), true);
		rebalance();
	}
	
	@Override
	public boolean removeE(K key) {
		Node<K,V> element = search(key);
		return removeE(element);
	}
		
	private boolean removeE(Node<K,V> element) {
		if(element==null) {
			return false;
		}else if(element.getLeft()==null | element.getRight()==null) {      //Delete element with one child
			if(element==getRoot()) {
				if(element.getLeft()!=null) {
					element.getLeft().setHead(null);
					setRoot(element.getLeft());
				}else {
					element.getRight().setHead(null);
					setRoot(element.getRight());
				}
			}else {
				if(element.getLeft()!=null) {
					element.getLeft().setHead(element.getHead());
					if(element.getHead().getLeft()==element) {
						element.getHead().setLeft(element.getLeft());
					}else {
						element.getHead().setRight(element.getLeft());
					}					
				}else {
					element.getRight().setHead(element.getHead());
					if(element.getHead().getLeft()==element) {
						element.getHead().setLeft(element.getRight());
					}else {
						element.getHead().setRight(element.getRight());
					}
				}				
			}
			refreshHeights(element.getHead(), true);
			return true;
			
			
		}else if(element.getLeft()==null && element.getRight()==null) {     //Delete sheet
			if(element==getRoot()) {
				setRoot(null);
			}else {
				if(element.getHead().getLeft()==element) {
					element.getHead().setLeft(null);
				}else {
					element.getHead().setRight(null);
				}
			}
			refreshHeights(element.getHead(), true);
			return true;
			
			
		}else {				                                             //Delete element with both children
			Node<K,V> min = element.getRight().getMin();
			removeE(min);
			min.setHead(element.getHead());
			min.setRight(element.getRight());
			min.setLeft(element.getLeft());
			element.getLeft().setHead(min);
			if(element.getRight()!=null) {
				element.getRight().setHead(min);
			}
			if(element==getRoot()) {
				setRoot(min);
			}else {
				if(element.getHead().getLeft()==element) {
					element.getHead().setLeft(min);
				}else {
					element.getHead().setRight(min);
				}
			}
			refreshHeights(element.getHead(), true);
			return true;
		}
	}
	
	@Override
	public void rebalance() {
		rebalance(getRoot());
	}
	
	private void rebalance(Node<K,V> node) {
		if(node.getBFactor()==-2) {
			Node<K,V> left = node.getLeft();
			if(left.getBFactor()!=2 && left.getBFactor()!=-2) {
				rightRotate(left);
			}else {
				rebalance(left);
			}
		}else if (node.getBFactor()==2){
			Node<K,V> right = node.getRight();
			if(right.getBFactor()!=2 && right.getBFactor()!=-2) {
				leftRotate(right);
			}else {
				rebalance(right);
			}
		}
	}

	@Override
	public void leftRotate(Node<K, V> node) {
		Node<K,V> father = node.getHead();
		Node<K,V> right = node.getRight();
		
		//Case F
		if(right.getBFactor()==1) { //Became in case A o B
			rightRotate(right);
		}
		
		right = node.getRight();
		
		//Case D o E
		
		//Set left's father element for unbalanced's father.
		
		if(node.equals(getRoot())) {
			right.setHead(null);
			setRoot(right);
		}else {
			right.setHead(node.getHead());
			if(father.getLeft()==node){
				father.setLeft(right);
			}else {
				father.setRight(right);
			}
		}
		
		//if the node of the middle exist change of side.
		Node<K,V> middle = right.getLeft();
		if(middle!=null) {
			node.setRight(middle);
		}
		
		// Turn down the unbalanced node
		right.setLeft(node);
	}

	@Override
	public void rightRotate(Node<K, V> node) {
		Node<K,V> father = node.getHead();
		Node<K,V> left = node.getLeft();
		
		//Case C
		if(left.getBFactor()==1) { //Became in case A o B
			leftRotate(left);
		}
		
		left = node.getLeft();
		
		//Case A o B
		
		//Set left's father element for unbalanced's father.
		if(node.equals(getRoot())) {
			left.setHead(null);
			setRoot(left);
		}else {
			left.setHead(node.getHead());
			if(father.getLeft()==node){
				father.setLeft(left);
			}else {
				father.setRight(left);
			}
		}
		
		//if the node of the middle exist change of side.
		Node<K,V> middle = left.getRight();
		if(middle!=null) {
			node.setLeft(middle);
		}
		
		// Turn down the unbalanced node
		left.setRight(node);
	}
	
	private void refreshHeights(Node<K,V> element, boolean first) {
		if(element!=null) {
			if(first) {
				int height = element.getH();
				
				if(element.getLeft()==null) {
					element.setH1(0);
				}else {
					element.setH1(1);
				}
				if(element.getRight()==null) {
					element.setH2(0);
				}else {
					element.setH2(1);
				}
				
				if(height!=element.getH()){
					refreshHeights(element.getHead(), false);
				}
			}else {
				int height = element.getH();
				
				Node<K,V> left = element.getLeft();
				Node<K, V> right = element.getRight();
				
				if(left!=null) {
					element.setH1(left.getH());
				}if(right!=null) {
					element.setH2(right.getH());
				}
				
				if(height!=element.getH()) {
					refreshHeights(element.getHead(), false);
				}
			}
		}
	}


}