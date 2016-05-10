package graphs;

import java.util.*;
import java.io.*;

class edge implements Serializable {

    int vleft, vright;
    int cost;

    public edge(int l, int r, int c) {
        vleft = l;
        vright = r;
        cost = c;
    }

    public edge() {
    }

    public void display() {
        System.out.println("Vertex 1: " + vleft + " Vertex 2: " + vright + " Cost of edge: " + cost);
    }
}

class graph {

    int noe;
    int nov;
    ArrayList<Integer> vertices = new ArrayList<>();
    ArrayList<edge> edges = new ArrayList<>();

    public graph() {
        noe = 0;
        nov = 0;
    }

    public void addedge(edge e) {
        edges.add(e);
        noe++;
        int i;
        for (i = 0; i < nov; ++i) {
            if (vertices.get(i) == e.vleft) {
                break;
            }
        }
        if (i == nov) {
            vertices.add(e.vleft);
            nov++;
        }
        for (i = 0; i < nov; ++i) {
            if (vertices.get(i) == e.vright) {
                break;
            }
        }
        if (i == nov) {
            vertices.add(e.vright);
            nov++;
        }
    }

    public void dispgraph() {
        //System.out.println(nov+" "+noe);
        System.out.print("The vertices are: ");
        for (int i = 0; i < nov; ++i) {
            System.out.print(vertices.get(i) + " ");
        }
        System.out.println("\nThe edges are: ");
        for (int i = 0; i < noe; ++i) {
            edges.get(i).display();
        }
    }

    public boolean isneigh(Integer u, Integer v) {
        for (edge e : edges) {
            if ((e.vleft == u && e.vright == v) || (e.vleft == v && e.vright == u)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getneighbours(int cur, ArrayList<Integer> notvisited) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (Integer i : notvisited) {
            if (isneigh(cur, i)) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }
}

public class Graphs {

    public static Integer minDistance(ArrayList<Integer> Q, ArrayList<Integer> dist) {
        Integer small = dist.get(Q.get(0));
        Integer temp = Q.get(0);
        for (int i = 1; i < Q.size(); i++) {
            if (dist.get(Q.get(i)) < small) {
                small = dist.get(Q.get(i));
                temp = Q.get(i);
            }
        }
        return temp;
    }
    static ArrayList<Integer> prev = new ArrayList<>();

    public static ArrayList<Integer> dijkstra(graph g) {
        ArrayList<Integer> dist = new ArrayList<>();
        Integer[][] c = new Integer[g.nov][g.nov];
        for (int i = 0; i < g.nov; i++) {
            for (int j = 0; j < g.nov; j++) {
                c[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < g.edges.size(); i++) {
            c[g.edges.get(i).vleft][g.edges.get(i).vright] = g.edges.get(i).cost;
            c[g.edges.get(i).vright][g.edges.get(i).vleft] = g.edges.get(i).cost;
        }
        for (int i = 0; i < g.vertices.size(); i++) {
            dist.add(i, c[0][i]);
        }
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < g.vertices.size(); ++i) {
            prev.add(0);
            Q.add(g.vertices.get(i));
        }
        dist.remove((int) 0);
        dist.add((int) 0, 0);
        Integer u, alt, v;
        while (!Q.isEmpty()) {
            u = minDistance(Q, dist);
            for (int i = 0; i < Q.size(); i++) {
                if (Q.get(i) == u) {
                    Q.remove(i);
                    break;
                }
            }
            neighbours = g.getneighbours(u, Q);
            for (int i = 0; i < neighbours.size(); i++) {
                v = neighbours.get(i);
                Integer len = 0;
                for (edge ed : g.edges) {
                    if ((ed.vleft == u && ed.vright == v) || (ed.vleft == v && ed.vright == u)) {
                        len = ed.cost;
                    }
                }
                alt = dist.get((int) u) + len;
                if (alt < dist.get((int) v)) {
                    dist.remove((int) v);
                    dist.add((int) v, alt);
                    prev.remove((int) v);
                    prev.add((int) v, u);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        /*edge e1=new edge(1,5,30);
        edge e2=new edge(1,4,20);
	edge e3=new edge(2,3,50);
	edge e4=new edge(3,5,10);
	edge e5=new edge(5,2,20);
	edge e6=new edge(3,4,30);
	edge e7=new edge(3,1,10);*/
 /* edge e1=new edge(1,2,10);
        edge e2=new edge(2,3,50);
	edge e3=new edge(4,3,20);
	edge e4=new edge(4,5,60);
	edge e5=new edge(1,5,100);
	edge e6=new edge(1,4,30);
	edge e7=new edge(3,5,10);*/
        edge e1 = new edge(0, 1, 10);
        edge e2 = new edge(1, 2, 50);
        edge e3 = new edge(3, 2, 20);
        edge e4 = new edge(3, 4, 60);
        edge e5 = new edge(0, 4, 100);
        edge e6 = new edge(0, 3, 30);
        edge e7 = new edge(2, 4, 10);
        graph g = new graph();
        try {
            FileOutputStream fos = new FileOutputStream("dijkstrain.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
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
            FileInputStream fis = new FileInputStream("dijkstrain.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int k = 0;
            while (ois.available() >= 0 && k < 7) {
                edge temp = new edge();
                temp = (edge) ois.readObject();
                temp.display();
                g.addedge(temp);
                ++k;
            }
            g.dispgraph();
            ArrayList<Integer> dist = dijkstra(g);
            for (int i = 0; i < g.nov; ++i) {
                System.out.println("Distance from source vertex to " + g.vertices.get(i) + " is: " + dist.get(i));
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
