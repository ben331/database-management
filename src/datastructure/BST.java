package datastructure;

public class BST<K extends Comparable<K>, V> implements IBST<K,V>{
	
	private Node<K,V> root;
	
	public BST() {}
	
	@Override
	public Node<K,V> getRoot() {
		return root;
	}
	
	public void setRoot(Node<K,V> root) {
		this.root = root;
	}
	
	@Override
	public void insertE(K key, V value) {
		
		Node<K,V> element = new Node<K,V>(key, value);
		
		Node<K,V> current = root;
		
		if(root == null) {
			root = element;
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
	}

	protected Node<K,V> search(K key) {
		
		Node<K,V> current = root;
		boolean wasFounded=false;
		
		while(current!=null && !wasFounded) {
			if(current.getKey().compareTo(key)>0) {
				current = current.getLeft();
			}else if(current.getKey().compareTo(key)<0) {
				current= current.getRight();
			}else {
				wasFounded=true;
			}
		}
		return current;
	}
	
	@Override
	public V searchE(K key) {
		Node<K, V> node = search(key);
		return node!=null? node.getValue():null;
	}

	@Override
	public int getSize() {
		return getSize(root);
	}
	
	private int getSize(Node<K,V> current) {
		
		if(current!=null) {
			if(current.isSheet()){
				return 1;
			}else {
				int sizeLeft=getSize(current.getLeft());
				int sizeRight=getSize(current.getRight());
				return sizeLeft + sizeRight + 1;
			}
		}
		return 0;
	}

	
	public int getHeight() {

        if(root != null) {
            return getHeight(root, 0);
        }else {
            return 0;
        }

    }
	
	public int getHeight(Node<K,V> current, int height) {
		
		int heightLeft=height;
		int heightRigth=height;
		
        if(current.getLeft() != null) {
        	
            heightLeft=getHeight(current.getLeft(), height + 1);
            
        }if (current.getRight() != null){
        	
        	heightRigth=getHeight(current.getRight(), height + 1);
        }

        if(heightLeft < heightRigth) {
            return heightRigth;
        }else {
            return heightLeft;
        }
    }

	@Override
	public String inOrden() {
		return inOrden(root);
	}
	
	private String inOrden(Node<K,V> current) {
		if(current==null) {
			return "";
		}else {
			String contentLeft = inOrden(current.getLeft());
			String contentRight = inOrden(current.getRight());
			
			return contentLeft + current.toString() + "\n" + contentRight;
		}
	}

	@Override
	public String postOrden() {
		return postOrden(root);
	}
	
	private String postOrden(Node<K,V> current) {
		if(current==null) {
			return "";
		}else {
			String contentLeft = postOrden(current.getLeft());
			String contentRight = postOrden(current.getRight());
			
			return contentLeft + contentRight + current.toString() + "\n";
		}
	}

	@Override
	public String preOrden() {
		return preOrden(root);
	}
	
	private String preOrden(Node<K,V> current) {
		if(current==null) {
			return "";
		}else {
			String contentLeft = preOrden(current.getLeft());
			String contentRight = preOrden(current.getRight());
			
			return current.toString() + "\n" +contentLeft + contentRight;
		}
	}
	
	@Override
	public boolean removeE(K key) {
		Node<K,V> element = search(key);
		return removeE(element);
	}
		
	private boolean removeE(Node<K,V> element) {
		if(element==null) {
			return false;
		}else if(element.getLeft()!=null && element.getRight()!=null) {      //Delete element with both children
			
			Node<K,V> min = element.getRight().getMin();
			removeE(min);
			min.setHead(element.getHead());
			min.setRight(element.getRight());
			min.setLeft(element.getLeft());
			element.getLeft().setHead(min);
			if(element.getRight()!=null) {
				element.getRight().setHead(min);
			}
			if(element==root) {
				root=min;
			}else {
				if(element.getHead().getLeft()==element) {
					element.getHead().setLeft(min);
				}else {
					element.getHead().setRight(min);
				}
			}
			return true;
			
		}else if(element.getLeft()==null && element.getRight()==null) {     //Delete sheet
			if(element==root) {
				root=null;
			}else {
				if(element.getHead().getLeft()==element) {
					element.getHead().setLeft(null);
				}else {
					element.getHead().setRight(null);
				}
			}
			return true;
			
			
		}else {				                                             //Delete element with one child
			if(element==root) {
				if(element.getLeft()!=null) {
					element.getLeft().setHead(null);
					root=element.getLeft();
				}else {
					element.getRight().setHead(null);
					root=element.getRight();
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
			return true;
			//---
			
		}
	}
}
