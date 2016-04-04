import java.util.*;
import java.io.*;
class elements implements Serializable{
	String elmt;
}
public class hashtester{
	public static void main(String[] args){
		Scanner inp=new Scanner(System.in);
		Random ran = new Random();
		try{
			FileOutputStream fos=new FileOutputStream("hash.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			elements temp=new elements();
			for(int i=0;i<10;++i){
				temp.elmt=((char)(65+ran.nextInt(26)))+""+((char)(48+ran.nextInt(10)))+""+((char)(97+ran.nextInt(26)))+"";
				oos.writeObject(temp);
				System.out.println(temp.elmt);
				oos.reset();
			}
			oos.flush();
			oos.close();

			FileInputStream fis=new FileInputStream("hash.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			int k=0;
			while(ois.available()>=0&&k<10){
				temp=(elements)ois.readObject();
				int hashvalue=0;
				char[] ch=temp.elmt.toCharArray();
				for(int j=0;j<ch.length;++j)
				{
					char c=ch[j];
					if((c>='A'&&c<='Z')||c>='a'&&c<='z')
						hashvalue+=((int)c);
					else 
						hashvalue+=2*((int)c);
				}
				hashvalue=((hashvalue*17)+5)%5;
				System.out.println("Hashvalue of "+temp.elmt+" is: "+hashvalue);
				++k;
			}
		}
		catch(IOException | ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
}
