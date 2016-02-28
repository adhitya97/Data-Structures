import java.util.Scanner;
import java.io.*;
class Publisher implements Serializable{
	int publisherid,pincode;
	String publishername;
	public Publisher(){}
}
public class stacks{
	public static void main(String[] args) throws Exception{
		Publisher p[]= new Publisher[5];
		int top=-1;
		Scanner inp=new Scanner(System.in);
		try{
			ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream("publisherin.dat"));
				Publisher temp= new Publisher();
				for(int i=0;i<10;++i)
				{
					temp.publisherid=i+1;
					temp.pincode=601+i;
					temp.publishername="Dealer"+(i+1);
					op.writeObject(temp);
					op.reset();
				}
				op.flush();
				op.close();
			
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("publisherin.dat"));
				while(input.available()>=0)
				{
					if(top==4){
						System.out.println("Stack overflow!");
						break;
						}
					else{
						p[++top]=(Publisher)(input.readObject());
					}
				}
				input.close();
			
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("publisherout.dat"));
				char c;
				do{
					if(top==-1)
						System.out.println("Stack underflow!");
					else{
						System.out.println("Popped Object: "+p[top].publisherid+" "+p[top].pincode+" "+p[top].publishername);
						oos.writeObject((Publisher)(p[top]));
						System.out.println("Object written onto file!");
						top--;
						oos.reset();
					}
			
					System.out.print("Do you  want to continue popping? ");
					c= inp.next().charAt(0);
				}while(c=='y');
				oos.flush();
				oos.close();
			}
			catch(IOException | ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
	}

}
