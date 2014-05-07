package avl;

public class Node {

	int key;
	Object data;
	Tree left_t = null;
	Tree right_t = null;
	
	Node(int key, Object data){
		this.key = key;
		this.data = data;
	}
	public Object get_data(){
		return this.data;
	}
	public void push_data(Object data){
		this.data = data;
	}
}
