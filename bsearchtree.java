import java.io.*;
import java.util.*;
class node{
	int element;
	String name;
	float gpa;
	node parent;
	node childl,childr;
	public node(int v,String n,float g,node cl,node cr,node p){
		element=v;
		childl=cl;
		name=n;
		gpa=g;
		childr=cr;
		parent=p;
	}
}
class tree{
	node root=new node(0,"",0,null,null,null);
	int non;
	public tree(){
		non=0;
	}
	public void addnode(int value,String n,int gp,node p){
		if(non==0){
			root.element = value;
			root.name=n;
			root.gpa=gp;
			non++;
			root.childr=root.childl=null;
		}
		else{
			if(value<p.element)
				if(p.childl!=null){
					addnode(value,n,gp,p.childl);
				}
				else{
					p.childl=new node(value,n,gp,null,null,p);
				}
			else if(value>p.element)
				if(p.childr!=null){
					addnode(value,n,gp,p.childr);
				}
				else{
					p.childr=new node(value,n,gp,null,null,p);
				}
				non++;
		}
	}
	public node min_elmt(node p){
		while(p.childl!=null)
			p=p.childl;
		return p;
	}
	public void removenode(int value,node p){
		if(value<p.element)
			removenode(value,p.childl);
		else if (value>p.element)
			removenode(value,p.childr);
		else{
			--non;
			if(p.childl!=null && p.childr!=null){
				node temp = min_elmt(p);
				p.element=temp.element;
				p.gpa=temp.gpa;
				p.name=temp.name;
				removenode(temp.element,temp);
			}
			else if(p.childl!=null && p.childr==null)
				p=p.childl;
			else if(p.childr!=null && p.childl==null)
				p=p.childr;
			else{
				if(p==p.parent.childl)
					p.parent.childl=null;
				else 
					p.parent.childr=null;
			}
		}
	}
	public void viewtreepre(node p){
		System.out.println(p.element+" "+p.name+" "+p.gpa);
		if(p.childl!=null)
			viewtreepre(p.childl);
		if(p.childr!=null)
			viewtreepre(p.childr);
	}
	public void viewtreepost(node p){
		if(p.childl!=null)
			viewtreepost(p.childl);
		if(p.childr!=null)
			viewtreepost(p.childr);
		System.out.println(p.element+" "+p.name+" "+p.gpa);
	}
	public void viewtreein(node p){
		if(p.childl!=null)
			viewtreein(p.childl);
		System.out.println(p.element+" "+p.name+" "+p.gpa);
		if(p.childr!=null)
			viewtreein(p.childr);
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
			System.out.println("Press 3 to view the tree in pre-order");
			System.out.println("Press 4 to view the tree in post-order");
			System.out.println("Press 5 to view the tree in in-order");
			System.out.print("Enter choice: ");
			ch=inp.nextInt();
			if(ch==1){
				System.out.print("Enter the value of element to be inserted: ");
				int value=inp.nextInt();
				System.out.print("Enter the name: ");
				String n=inp.next();
				System.out.print("Enter the gpa: ");
				int gp=inp.nextInt();
				t.addnode(value,n,gp,t.root);
			}
			else if(ch==2){
				System.out.print("Enter the value to be deleted: ");
				int value=inp.nextInt();
				t.removenode(value,t.root);
			}
			else if(ch==3)
				t.viewtreepre(t.root);
			else if(ch==4)
				t.viewtreepost(t.root);
			else if(ch==5)
				t.viewtreein(t.root);
			System.out.print("Do you want to continue? ");
			c=inp.next().charAt(0);
		}while(c=='y'||c=='Y');
	}
}
