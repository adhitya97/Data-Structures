import java.io.*;
import java.util.*;
class department implements Serializable{
	int departmentid,noofstudents;
	String departmentname;
	public department()
	{}
}
public class queue{
	public static void main(String[] args) throws Exception{
		int front=-1,rear=-1;
		department[] d=new department[10];
		Random ran=new Random();
		Scanner inp=new Scanner(System.in);
		int ch;
		char c;
		try{
			FileOutputStream fos1=new FileOutputStream("quein.dat");
			ObjectOutputStream oos1=new ObjectOutputStream(fos1);
			department e=new department();
			for(int i=0;i<100;++i)
			{
			e.departmentid=(i+1);
			e.departmentname= "Dept "+(i+1);
			e.noofstudents= ran.nextInt(1000);
			oos1.writeObject((department)e);
			oos1.reset();
			}
			oos1.flush();
			oos1.close();

			FileInputStream fis=new FileInputStream("quein.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			FileOutputStream fos2=new FileOutputStream("queout.dat");
			ObjectOutputStream oos2= new ObjectOutputStream(fos2);

			do{
				System.out.println("Press 1 to add an element to the queue");
				System.out.println("Press 2 to remove an element from the queue");
				System.out.print("Enter choice: ");
				ch=inp.nextInt();
				if(ch==1){
					if(ois.available()>=0){
						if(rear==9){
							System.out.println("Queue is full!");
							break;
						}
					else {
						if(rear==-1)
							front++;
						rear++;
						d[rear]=(department)ois.readObject();
						System.out.println("Added object: "+d[rear].departmentid+" "+d[rear].departmentname+" "+d[rear].noofstudents);
						}
					}
				}
				else if(ch==2){
					if(rear==-1){
						System.out.println("Queue is empty!");
							}
					else{
						System.out.println("The removed Object: "+d[front].departmentid+" "+d[front].departmentname+" "+d[front].noofstudents);
						oos2.writeObject((department)d[front]);
						oos2.reset();
						if(rear==front)
							front=rear=-1;
						else 
							front++;
						}
				}
				System.out.print("Do you want to continue? ");
				c=inp.next().charAt(0);
			}while(c=='y');
		ois.close();
		oos2.flush();
		oos2.close();
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
