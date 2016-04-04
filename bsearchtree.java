import java.io.*;
import java.util.*;
class node{
	int element;
	node parent;
	node childl,childr;
	public node(int v,node cl,node cr,node p){
		element=v;
		childl=cl;
		childr=cr;
		parent=p;
	}
}
class tree{
	node root=new node(0,null,null,null);
	int non;
	public tree(){
		non=0;
	}
	public void addnode(int value,node p){
		if(non==0){
			root.element = value;
			non++;
			root.childr=root.childl=null;
		}
		else{
			if(value<p.element)
				if(p.childl!=null){
					addnode(value,p.childl);
				}
				else{
					p.childl=new node(value,null,null,p);
				}
			else if(value>p.element)
				if(p.childr!=null){
					addnode(value,p.childr);
				}
				else{
					p.childr=new node(value,null,null,p);
				}
				non++;
		}
	}
	public void removenode(int value,node p){
	//	if(value==p.element)
	}
	public void viewtree(node p){
		System.out.print(p.element+" ");
		if(p.childl!=null)
			viewtree(p.childl);
		if(p.childr!=null)
			viewtree(p.childr);
	}
}
public class bsearchtree{
	public static void main(String[] args){
		tree t=new tree();
		Scanner inp=new Scanner(System.in);
		int ch=0;
		char c;
		do{
			System.out.println("Press 1 to add a node");
			System.out.println("Press 2 to remove a node");
			System.out.println("Press 3 to view the tree");
			System.out.print("Enter choice: ");
			ch=inp.nextInt();
			if(ch==1){
				System.out.print("Enter the value of element to be inserted: ");
				int value=inp.nextInt();
				t.addnode(value,t.root);
			}
			else if(ch==2){
				System.out.print("Enter the value to be deleted: ");
				int value=inp.nextInt();
				t.removenode(value,t.root);
			}
			else if(ch==3)
				t.viewtree(t.root);
			System.out.print("Do you want to continue? ");
			c=inp.next().charAt(0);
		}while(c=='y'||c=='Y');
	}
}
