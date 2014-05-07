package avl;

public class Tree {

	int height;
	Node unit;
	///////constructor
	Tree(int key){
		unit = new Node(key, null);
		height = 1;	
	}
	Tree(int key, Object data){
		unit = new Node(key, data);
		height = 1;
	}
	Tree(){
		height = 0;
		unit = null;
	}
	////get a right height of a tree
	public static int height(Tree a){
		int h;
		if(a == null){
			h = 0;
		} else {
			h = a.height;
		}
		return h;
	}
	
	////////rotate right
	private static Tree rotate_right(Tree a){
		Tree b = a.unit.left_t;
		a.unit.left_t = b.unit.right_t;
		b.unit.right_t = a;
		b.newheight();
		return b;
	}
	////////rotate left
	private static Tree rotate_left(Tree a){
		Tree b = a.unit.right_t;
		a.unit.right_t = b.unit.left_t;
		b.unit.left_t = a;
		b.newheight();
		return b;
	}

	
	//////Make balance
	private static Tree balance(Tree a){
		///left rotate
		if( balancefactor(a) >= 2){
			if(balancefactor(a.unit.right_t) < 0 ){
				a.unit.right_t = rotate_right(a.unit.right_t);
			}
			a = rotate_left(a);
		}
		///right rotate
		if( balancefactor(a) <= -2){
			if(balancefactor(a.unit.left_t) > 0 ){
				a.unit.left_t = rotate_left(a.unit.left_t);
			}
			a = rotate_right(a);
		}		
		return a;
	}
	
	/////////Balance factor
	private static int balancefactor(Tree a){
		int lh;
		int rh;
		if(a.unit.left_t != null){
			lh = a.unit.left_t.height;
		} else {
			lh = 0;
		}
		if(a.unit.right_t != null){
			rh = a.unit.right_t.height;
		} else {
			rh = 0;
		}
		int d = rh - lh;
		return d;
	}
	
	
	//////////////Insert into the tree
	public static Tree insert(Tree t,int key1, Object data){
			if(key1 < t.unit.key){
				if(t.unit.left_t == null){
					t.unit.left_t = new Tree(key1, data);
				} else {
					t.unit.left_t = insert(t.unit.left_t, key1, data);
				}
			} else {
				if(t.unit.right_t == null){
					t.unit.right_t = new Tree(key1, data);
				} else {
					t.unit.right_t = insert(t.unit.right_t, key1, data);
				}
			}
		
		
		t.newheight();
		if(Math.abs(balancefactor(t)) > 1){
			Tree a = balance(t);
			t = a;
		}
		return t;
	}
	///////////////////////////////////////////////search
	public Tree find(int key){
		Tree a = null;
		if(key < this.unit.key){
			if(this.unit.left_t != null){		
				a = this.unit.left_t.find(key);
			}
		} 
		if(key > this.unit.key){
			if(this.unit.right_t != null){
				a = this.unit.right_t.find(key);
			}
		}
		if(key == this.unit.key){
			a = new Tree(key);
			a.unit.left_t = this.unit.left_t;
			a.unit.right_t = this.unit.right_t;
		}
		return a;
	}
	/////////////////////////////////////////////////////////////
	
	
	///////////Count new height
	private void newheight(){
		int lh;
		int rh;
		if(this.unit.left_t == null){
			lh = 0;
		} else {
			this.unit.left_t.newheight();
			lh = this.unit.left_t.height;
		}
		if(this.unit.right_t == null){
			rh = 0;
		} else {
			this.unit.right_t.newheight();
			rh = this.unit.right_t.height;
		}
		this.height = 1 + Math.max(lh, rh);
		
	}
	////////find min
	public static int minfind(Tree a){
		int b;
		if(a.unit.left_t != null){
			b = minfind(a.unit.left_t);
		} else {
			b = a.unit.key; 
		} 
		return b;
	}
	public static Object minfind_o(Tree a){
		Object b;
		if(a.unit.left_t != null){
			b = minfind_o(a.unit.left_t);
		} else {
			b = a.unit.data; 
		} 
		return b;
	}
	//////remove 
	public static Tree remove(Tree a, int key){
		if(key < a.unit.key){
			if(a.unit.left_t != null){		
				a.unit.left_t = remove(a.unit.left_t, key);
			} else {
				return a;
			}
		} 
		if(key > a.unit.key){
			if(a.unit.right_t != null){
				a.unit.right_t = remove(a.unit.right_t, key);
			} else {
				return a;
			}
		}
		if(key == a.unit.key){
			if(a.unit.right_t == null){
				a = a.unit.left_t;
			} else {
				int b = minfind(a.unit.right_t);
				a = remove(a, b);
				a.newheight();
				a.unit.key = b;
				a.unit.data = minfind_o(a.unit.right_t);
			}
			if(a != null){
				if(Math.abs(balancefactor(a)) > 1){
					Tree b = balance(a);
					a = b;
				}
			}
		}
		return a;
	}
	/////////////////draw
	public String draw(){
		String a[] = new String[100];
		String b = "";
		a = this.draw_st();
		int i = 0;
		do{
			if(a[i] != null && !a[i].matches("[^0-9]*")){
				b +=a[i]+"\n";
			}
			i++;
			
		}while(i < 100);
		return b;
	}
	private String[] draw_st(){
		String a[] = new String[100];
		String b[] = new String[100];
		String c[] = new String[100];
		if(this.unit.left_t != null){
			b = this.unit.left_t.draw_st();
		} else {
			b[0] = " ";
		}
		if(this.unit.right_t != null){
			c = this.unit.right_t.draw_st();
		} else {
			c[0] = " ";
		}
		String str = String.valueOf(this.unit.key);
		a[0] = "";
		int max = 0;
		int max1 = 0;
		
		do{
			max++;
		} while(b[max-1] != null);
		//max--;
		do{
			max1++;
		} while(c[max1-1] != null);
		//max1--;
		max = Math.max(max, max1);
		int l = max + max1 ;
		int f = (int)Math.round(l/2)+1;
		int s = l - f - str.length();
		if(max > 1){
			for(int j = 1; j < f; j++){
				a[0] += " "; 
			}
			a[0] += " "+str;
			for(int k= 1; k < s; k++){
				a[0] += " "; 
			}
		} else {
			a[0] = str;
		}
		for(int i = 1; i < max; i++){
			if(b[i-1] != null){
				a[i] = b[i-1];
			} else {
				a[i] = " ";
			}
			a[i] += "  ";
			if(c[i-1] != null){
				a[i] += c[i-1];
			} else {
				a[i] += " ";
			}
		}
		return a;
	}
	/////////show inline
	public void show(){
		if(this.unit.left_t != null){
			this.unit.left_t.show(); 
		}
		System.out.println(this.unit.key);
		if(this.unit.right_t != null){
			this.unit.right_t.show();
		}
	}
	
}
