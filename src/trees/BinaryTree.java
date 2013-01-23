package trees;

import java.lang.Math;
import java.util.LinkedList;
import java.util.Stack;



public class BinaryTree {

	public Node root;
	
	public int found = 0;
	public Node LCA = null;

	
	public BinaryTree() {
		
	}
	
	public void createTree() {
		// Take some random values from 1 - 50. Create tree on the fly.
		root = createNode(0);//createNode((int) (1 + Math.random() * 50) );

		Queue q = new Queue();
		q.enqueue(root);
		
		for ( int i=1 ; i<=19; i++) {
						
			Node temp = (Node) q.dequeue();

			temp.left = createNode(i); //createNode((int) (1 + Math.random() * 50) );
			q.enqueue(temp.left);	
			
			if( i < 20 ) {
				temp.right = createNode(i+1);//createNode((int) (1 + Math.random() * 50) );
				i++;
				q.enqueue(temp.right);
			}

		}		
	}
	
	public void traverseDepthFirst() {
		Stack<Node> s = new Stack<Node> ();
		s.push(root);
		
		System.out.println();
		
		while(!s.empty()) {
			Node temp = s.pop();

			if(temp.left == null) {
				// check right.
				if(temp.right == null) {
					temp.visited = 1;
					System.out.print(temp.value+" ");
				}
				else if(temp.right.visited == 1) {
					temp.visited = 1;
					System.out.print(temp.value+" ");
				}
				else if(temp.right.visited == 0) {
					s.push(temp);
					s.push(temp.right);
				}
 			}
			else {
				if(temp.left.visited == 1) {
					if(temp.right == null) {
						temp.visited = 1;
						System.out.print(temp.value+" ");
					}
					else if(temp.right.visited == 1) {
						temp.visited = 1;
						System.out.print(temp.value+" ");
					}
					else if(temp.right.visited == 0) {
						s.push(temp);
						s.push(temp.right);
					}
				}
				else {
					s.push(temp);
					s.push(temp.left);
				}
			}					
		}		
	}

	public void traverseDepthFirstRecursive(Node n) {

			if(n.left != null) {
				traverseDepthFirstRecursive(n.left);
			}
			
			if(n.right != null) {
				traverseDepthFirstRecursive(n.right);
			}
			
			n.visited = 1;
			System.out.print(n.value+" ");
	}
	
	public void LCARecursive(Node n, int first, int second) {

		if(found == 1) {
			return;
		}
		if(LCA != null && (n.value == first || n.value == second)) {
			found = 1;
		}
		
		if(LCA == null && (n.value == first || n.value == second)) {
			LCA = n;
			System.out.println("LCA Now="+LCA.value);
		}
				
		if(n.left != null) {
			LCARecursive(n.left, first, second);
			if(LCA != null && LCA.value == n.left.value) {
				if(found == 0) {
					LCA = n;
					System.out.println("LCA Now="+LCA.value);
				}
			}
		}
		
		if(n.right != null) {
			LCARecursive(n.right, first, second);
			if(LCA != null && LCA.value == n.right.value) {
				if(found == 0) {
					LCA = n;
					System.out.println("LCA Now="+LCA.value);
				}
			}
		}
		
		n.visited = 1;
		System.out.print(n.value+" ");
	}
	
	public void LCAIterative(int first, int second) {
		Stack<Node> s = new Stack<Node> ();
		s.push(root);

		Node LCAIter = null;
		int found = 0;
		
		System.out.println();
		
		while(!s.empty()) {
			Node temp = s.pop();
			
			if(LCAIter != null && (temp.value == first || temp.value == second)) {
				found = 1;
			}
			
			if(LCAIter == null && (temp.value == first || temp.value == second)) {
				LCAIter = temp;
				System.out.println("LCAIter NOw:"+LCAIter.value);
			}

			if(temp.left == null) {
				// check right.
				if(temp.right == null) {
					temp.visited = 1;
					System.out.print(temp.value+" ");
				}
				else if(temp.right.visited == 1) {
					temp.visited = 1;
					if(found == 0 && LCAIter != null && LCAIter.value == temp.right.value) {
						LCAIter = temp;
						System.out.println("LCAIter NOw:"+LCAIter.value);
					}
					System.out.print(temp.value+" ");
				}
				else if(temp.right.visited == 0) {
					s.push(temp);
					s.push(temp.right);
				}
 			}
			else {
				if(temp.left.visited == 1) {
					if(temp.right == null) {
						temp.visited = 1;
						System.out.print(temp.value+" ");
					}
					else if(temp.right.visited == 1) {
						temp.visited = 1;
						if(found == 0 && LCAIter != null && LCAIter.value == temp.right.value) {
							LCAIter = temp;
							System.out.println("LCAIter NOw:"+LCAIter.value);
						}
						System.out.print(temp.value+" ");
					}
					else if(temp.right.visited == 0) {
						if(found == 0 && LCAIter != null && LCAIter.value == temp.left.value) {
							LCAIter = temp;
							System.out.println("LCAIter NOw:"+LCAIter.value);
						}
						s.push(temp);
						s.push(temp.right);
					}
				}
				else {
					s.push(temp);
					s.push(temp.left);
				}
			}					
		}		
	}
	
	public void printLCA() {
		System.out.print("\nLCA = "+LCA.value);
	}
	
	public void traverseBreadthFirst() {
			
		Queue q = new Queue();		
		q.enqueue(root);
		
		System.out.println();
		while(q.size() != 0) {
			Node current = q.dequeue();
			System.out.print(current.value+" ");
			
			if(current != null && current.left != null) {
				q.enqueue(current.left);
			}
 			
			if(current!= null && current.right != null) {
				q.enqueue(current.right);
			}
		}
	}
	
	
	public Node createNode(int val) {
		Node n = new Node(val);
		System.out.print(val+" ");
		
		return n;
	}
	
	public class Queue {
		
		public LinkedList<Node> l=null;
		
		public Queue() {
			l = new LinkedList<BinaryTree.Node>();
		}
		
		public void enqueue(Node n) {
			l.addLast(n);
		}
		
		public Node dequeue() {
			return l.removeFirst();
		}
		
		public int size() {
			return l.size();
		}
	}
	
	public class Node {
		
		public Node left=null;
		
		public Node right=null;
		
		public int value;
		
		public int visited=0;
		
		public Node(int val) {
			value = val;
		}
	}
}
