package avl;

import java.util.Scanner;

public class main_cl {
	public static void main(String[] args) {
		
	Scanner scan = new Scanner(System.in);
	
	Tree a = new Tree(0, 0);
	boolean bol = false;
	String s = "";
	while(s != "6"){
		System.out.println("menu");
		System.out.println("1. ��������");
		System.out.println("2. �������");
		System.out.println("3. �����");
		System.out.println("4. ������ �� �������");
		System.out.println("5. ����������");
		System.out.println("6. �����");
		
		      
	    s = scan.nextLine();
        switch(s){
        case "1": 
        	System.out.println("������� ����");
        	s = scan.nextLine();
        	if(bol){
        		a = Tree.insert(a, Integer.valueOf(s), 0);
        	} else {
        		a = new Tree(Integer.valueOf(s) , 0);
        		bol = !bol;
        	}
	        break;
        case "2":
        	System.out.println("������� ����");
	        s = scan.nextLine();
	        a = Tree.remove(a, Integer.valueOf(s));
        	break;
        case "3":
        	System.out.println("������� ����");
	        s = scan.nextLine();
	        Tree b;
	        b = a.find(Integer.valueOf(s));
	        break;
        case "4":
        	System.out.println("������� ����");
	        s = scan.nextLine();
	        Tree c;
	        c = a.find(Integer.valueOf(s));
	        System.out.println(c.unit.data);
        	break;
        case "5":
	    	System.out.println(a.draw());
	    	break;
		case "6":
			scan.close();
	        
			System.exit(0);
	    	break;
	    }
	}
	
	}

}
