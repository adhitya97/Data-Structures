import java.util.*;
import java.io.*;

class student implements Serializable{
	String dob,pob;
	int idno;
}
class studentarray implements Serializable{
	student[] details=new student[10];
	student[] tempdet=new student[10];
	public void display(){
		for(int qw=0;qw<this.details.length;++qw)
			System.out.println("ID: "+this.details[qw].idno+ " DOB: "+this.details[qw].dob+" Place of Birth: "+this.details[qw].pob);
	}
	public void mergeso(int l,int h){
		if(l<h){
			int m= (l+h)/2;
			mergeso(l,m);
			mergeso(m+1,h);
			merge(l,m,h);
			}
	}
	public void merge(int low,int mid,int high){
		int h=low,j=mid+1,i=low;
		int k;
		while(h<=mid && j<=high){
			if(details[h].idno<=details[j].idno)
				{
					tempdet[i]=details[h];
					h++;
				}
			else{
				tempdet[i]=details[j];
				j++;
			}
			++i;
		}
		if(h>mid){
			for(k=j;k<=high;++k)
				tempdet[i++]=details[k];
		}
		else{
			for(k=h;k<=mid;++k)
				tempdet[i++]=details[k];
		}
		for(k=low;k<=high;++k)
			details[k]=tempdet[k];
	}
}
public class mergesort{
	public static void main(String[] args) throws Exception{
		Scanner inp=new Scanner(System.in);
		Random ran=new Random();
		try{
			FileOutputStream fos=new FileOutputStream("min.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			student temp=new student();
			for(int i=0;i<100;++i){
				temp.idno=ran.nextInt(1000);
				temp.dob="10-1-"+(2000+i);
				temp.pob="Place "+(i+1);
				oos.writeObject(temp);
				oos.reset();
			}
			oos.flush();
			oos.close();

			FileInputStream fis=new FileInputStream("min.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			studentarray s=new studentarray();
			int i=0; 
			System.out.println("The ID numbers of the the unsorted array: ");
			while(ois.available()>=0&&i<10){
					s.details[i]=(student)ois.readObject();
					++i;
				}
			s.display();
			System.out.println("Sorting begins!");
			int h=s.details.length;
			s.mergeso(0,h-1);
			System.out.println("The sorted array: ");
			s.display();
		}
		catch(IOException|ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
}
