import java.util.*;
import java.io.*;

class node{
	int el;
	node lc,rc;
	public node(int e,node l,node r){
		el=e;
		lc=l;
		rc=r;
	}
}

class mheap{
	node root=new node(0,null,null);
	int non;
	public mheap(){
		non=0;
	}
	public void addel(int e,node p){
		if(non==0){
			root=p;
			root.el=e;
			non++;
		}
		else{
			if(e<=p.el){
				if(p.lc!=null)
					addel(e,p.lc);
				else
					p.lc=new node(e,p.lc,null);
			}
			else if(e>p.el){
				if(p.rc!=null)
					addel(e,p.rc);
				else
					p.rc=new node(e,null,p.rc);
			}
			non++;
		}
	}
	public void viewheap(node p){
		System.out.print(p.el+" ");
		if(p.lc!=null)
			viewheap(p.lc);
		if(p.rc!=null)
			viewheap(p.rc);
	}
}

public class heap{
	public static void main(String[] args){
		int[] a=new int[20];
		char c;
		Random ran=new Random(1234L);
		mheap h=new mheap();
		Scanner inp=new Scanner(System.in);
		for(int i=0;i<20;++i)
			a[i]=ran.nextInt(50);
		System.out.print("The array before forming the heap is: ");
		for(int i=0;i<20;++i)
			System.out.print(a[i]+" ");
		int i=0;
		System.out.println("\nHeap build begins!");
		do{
			h.addel(a[i],h.root);
			System.out.print("After iteration "+(++i)+": ");
			h.viewheap(h.root);
			System.out.print("\nDo you want to add an element to the heap? ");
			c=inp.next().charAt(0);
		}while(c=='y'||c=='Y');

	}
}
