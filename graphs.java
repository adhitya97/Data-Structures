import java.util.*;
import java.io.*;
class edge implements Serializable{
	int vleft,vright;
	float cost;
	public edge(int l,int r,float c){
		vleft=l;
		vright=r;
		cost=c;
	}
	public edge(){}
	public void display(){
		System.out.println("Vertex 1: "+vleft+" Vertex 2: "+vright+" Cost of edge: "+cost);
	}
}
class graph{
	int noe;
	int nov;
	ArrayList<Integer> vertices=new ArrayList<>();
	ArrayList<edge> edges=new ArrayList<>();
	public graph(){
		noe=0;
		nov=0;
	}
	public void addedge(edge e){
		edges.add(e);
		noe++;
		int i;
		for(i=0;i<nov;++i)
			if(vertices.get(i)==e.vleft)
				break;
		if(i==nov){
			vertices.add(e.vleft);
			nov++;
		}
		for(i=0;i<nov;++i)
			if(vertices.get(i)==e.vright)
				break;
		if(i==nov){
			vertices.add(e.vright);
			nov++;
	 	}
	}
	public void dispgraph(){
		System.out.print("The vertices are: ");
		for(int i=0;i<nov;++i)
			System.out.print(vertices.get(i)+" ");
		System.out.println("\nThe edges are: ");
		for(int i=0;i<noe;++i)
			edges.get(i).display();
	}
}
public class graphs{
	public float[] dijkstra(graph g){
		float[] dist=new float[g.nov];
		int[] visited=new int[g.nov];
		int[] notvisited=new int[g.nov];
		int nv=1;
		int nnv=4;
		visited[0]=1;
		for(int i=0;i<nv;++i);
		float[][] c=new float[g.nov][g.nov];
		for(int i=0;i<g.nov;++i)
			for(int j=0;j<g.nov;++j)
				c[i][j]=Integer.MAX_VALUE;
		for(int i=0;i<g.noe;++i){
			c[g.edges.get(i).vleft][g.edges.get(i).vright]=g.edges.get(i).cost;
		}
		for(int i=0;i<g.nov;++i)
			dist[i]=-1;
		for(int i=2;i<g.nov;++i)
			dist[i]=c[1][i];
		for(int i=0;i<g.nov-1;++i){
			
		}
		return dist;
	}
	public static void main(String[] args){
		edge e1=new edge(1,2,10);
		edge e2=new edge(2,3,50);
		edge e3=new edge(4,3,20);
		edge e4=new edge(4,5,60);
		edge e5=new edge(1,5,100);
		edge e6=new edge(1,4,30);
		edge e7=new edge(3,5,10);
		graph g=new graph();
		try{
			FileOutputStream fos=new FileOutputStream("dijkstrain.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(e1);
			oos.flush();
			oos.reset();
			oos.writeObject(e2);
			oos.flush();
			oos.reset();
			oos.writeObject(e3);
			oos.flush();
			oos.reset();
			oos.writeObject(e4);
			oos.flush();
			oos.reset();
			oos.writeObject(e5);
			oos.flush();
			oos.reset();
			oos.writeObject(e6);
			oos.flush();
			oos.reset();
			oos.writeObject(e7);
			oos.flush();
			oos.reset();
			oos.close();

			FileInputStream fis=new FileInputStream("dijkstrain.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			int k=0;
			while(ois.available()>=0&&k<7){
				edge temp=new edge();
				temp=(edge)ois.readObject();
				temp.display();
				g.addedge(temp);
				++k;
			}
			g.dispgraph();
		}
		catch(IOException|ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
}
