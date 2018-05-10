package mylibs;
/**
 * import this class into your class
 * create object of this class
 * use setlabel(int,String) - setlabel(vertex, name) to set name to vertex.
 * use print() to print to print each path from each nodes.
 * dijkstra_class_init(object, int) - dijkstra_class_init(object, start_node) => return type int[].
 * getpath(object ,int[], startnode, target) - return type => arraylist
 * printpath(object, int[], startnode, target) => print into console.
 * 
 * @author Nikhil Tanni
 */
import java.util.ArrayList;
public class Dijkstra_class {
	
	private int [][]  edges;  // adjacency matrix
    private Object [] labels;
    
	public Dijkstra_class(int n)
	{
		edges  = new int [n][n];
	    labels = new Object[n];
	}
	
	Dijkstra d;
	public int[] Dijkstra_class_init(Dijkstra_class t, int s)
	{
		return Dijkstra.dijkstra(t,s);
	}
	
	public int size() { return labels.length; }
	
	public void   setLabel (int vertex, Object label) { labels[vertex]=label; }
	public Object getLabel (int vertex)               { return labels[vertex]; }

	public void    addEdge    (int source, int target, int w)  { edges[source][target] = w; }
	public boolean isEdge     (int source, int target)  { return edges[source][target]>0; }
	public void    removeEdge (int source, int target)  { edges[source][target] = 0; }
	public int     getWeight  (int source, int target)  { return edges[source][target]; }
	
	public int [] neighbors (int vertex) {
	      int count = 0;
	      for (int i=0; i<edges[vertex].length; i++) {
	         if (edges[vertex][i]>0) count++;
	      }
	      final int[]answer= new int[count];
	      count = 0;
	      for (int i=0; i<edges[vertex].length; i++) {
	         if (edges[vertex][i]>0) answer[count++]=i;
	      }
	      return answer;
	   }
	
	public void print () {
	      for (int j=0; j<edges.length; j++) {
	         System.out.print (labels[j]+": ");
	         for (int i=0; i<edges[j].length; i++) {
	            if (edges[j][i]>0) System.out.print (labels[i]+":"+edges[j][i]+" ");
	         }
	         System.out.println ();
	      }
	   }
	
	public ArrayList getpath(Dijkstra_class G, int [] pred, int s, int e)
	{
		return Dijkstra.getpath(G, pred, s, e);
	}

}
