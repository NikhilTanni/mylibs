package test;


import java.util.ArrayList;

import mylibs.Dijkstra_class;
public class Dijkstra_test {
	   
    

   public static void main (String args[]) {
      final Dijkstra_class t = new Dijkstra_class (4);
      t.setLabel (0, "A");
      t.setLabel (1, "B");
      t.setLabel (2, "C");
      t.setLabel (3, "D");
      
      t.addEdge (0,1,4);
      t.addEdge (1,0,4);
      t.addEdge(1, 2, 1);
      t.addEdge(2, 1, 1);
      t.addEdge(2, 0, 1);
      t.addEdge(2, 3, 3);
      t.addEdge(3, 0, 1);
      t.addEdge(0, 2, 1);
      t.print();

      final int [] pred = t.Dijkstra_class_init(t, 0);
      ArrayList<String> tm=t.getpath(t, pred, 0, 3);
      System.out.println("Path : ");
      for(int i=0;i<tm.size();i++)
      {
    	  System.out.println(tm.get(i));
      }
      
      
//		printing from 1-2 1-3 ... 1-n path      
//      for (int n=0; n<6; n++) {
//         Dijkstra.printPath (t, pred, 0, n);
//      }
   }

}