import java.util.Random;
import java.util.Scanner;

public class HeapMake {
    private int[] Heap;
    private int size;
    private int maxsize; 
    private static final int FRONT = 1;
    public HeapMake(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    } 
    private int parent(int pos)
    {
        return pos / 2;
    } 
    private int leftChild(int pos)
    {
        return (2 * pos);
    } 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    } 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        { 
            return true;
        }
        return false;
    } 
    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    } 
    private void minHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            if ( Heap[pos] > Heap[leftChild(pos)]  || Heap[pos] > Heap[rightChild(pos)])
            {
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)])
                {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
     public void insert(int element)
    {
        Heap[++size] = element;
        int current = size;
 
        while (Heap[current] < Heap[parent(current)])
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    }
 
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i] 
                + " RIGHT CHILD :" + Heap[2 * i  + 1]);
            System.out.println();
        } 
    }
 
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }
    public static void main(String[] args) {
		int[] a=new int[100];
		char c;
		Random ran=new Random(1234L);
		HeapMake hm=new HeapMake(100);
		Scanner inp=new Scanner(System.in);
		for(int i=0;i<100;++i)
			a[i]=ran.nextInt(150);
		System.out.print("The array before forming the heap is: ");
		for(int i=0;i<100;++i)
			System.out.print(a[i]+" ");
		int i=0;
		System.out.println("\nHeap build begins!");
		do{
			hm.insert(a[i]);
			++i;
			}while(i<90);
                        hm.print();
               //	System.out.print("After iteration "+(++i)+": ");
		//	hm.viewheap(hm.root);
	//		System.out.print("\nDo you want to add an element to the heap? ");
	//		c=inp.next().charAt(0);
	//	}while(c=='y'||c=='Y');
        
    }
    
}
