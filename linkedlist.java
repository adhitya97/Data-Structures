import java.util.*;
import java.io.*;

class studentdetails implements Serializable{
	int studidno;
	String studentname;
	String dob;
	public studentdetails(){}
}
class Node {
	Node next;
	Node prev;
	studentdetails s;
	public Node(){
		prev=null;
		next=null;
	}
}
public class linkedlist{
	public static void main(String[] args) throws Exception{
		Scanner inp=new Scanner(System.in);
		char c;
		try{
			FileOutputStream fos=new FileOutputStream("dllin.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			studentdetails temp=new studentdetails();
			Node first=null;
			Node last=null;

			for(int i=0;i<100;++i){
				temp.studidno=(i+1);
				temp.studentname="Student "+i;
				temp.dob="10-1-"+(2000)+i;
				oos.writeObject(temp);
				oos.reset();
			}
			oos.flush();
			oos.close();

			FileInputStream fis=new FileInputStream("dllin.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			do{
				System.out.println("Press 1 to add element to the linked list");
				System.out.println("Press 2 to delete an element from the linked list");
				System.out.println("Press 3 to print contents of list from first element");
				System.out.println("Press 4 to print contents of list from last element");
				System.out.print("Enter your choice: ");
				int ch=inp.nextInt();
				if(ch==1){
					Node t=new Node();
					if(t==null)
						System.out.println("New object can not be created");
					else{
						temp=(studentdetails)ois.readObject();
						t.s=temp;
						if(first==null&&last==null){
							first=last=t;
						}
						else{
							t.prev=last;
							last.next=t;
							last=t;
							System.out.println(t.s.studidno);
						}
					}
				}
				else if(ch==2){
					System.out.println("Enter the ID number to be deleted: ");
					int x=inp.nextInt();
					for(Node i=first;;i=i.next){
						if(i.s.studidno==x){
							if(i==first){
								first=first.next;
								break;
							}
							else if(i==last){
								last=last.prev;
								break;
							}
							else{
								(i.prev).next=i.next;
								(i.next).prev=i.prev;
								break;
							}
						}
						if(i.next==null)
							break;
					}
				}
				else{
					if(first==null&&last==null)
						System.out.println("Linked list is empty");
					else{
						if(ch==3){
							Node p=first;
							for(;;p=p.next)
							{
								System.out.println(p.s.studidno+" "+p.s.studentname+" "+p.s.dob);
								if(p.next==null)
									break;
							}
						}
						else if(ch==4){
							Node q=last;
							for(;;q=q.prev){
								System.out.println(q.s.studidno+" "+q.s.studentname+" "+q.s.dob);
								if(q.prev==null)
									break;
							}
						}
					}
				}
			System.out.print("Do you want to continue? ");
			c=inp.next().charAt(0);
			}while(c=='y');
			ois.close();
		}
		catch(IOException|ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
}
