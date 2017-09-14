
import java.util.Iterator;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
    if (list == null){
      return;
    }

    int i = 0;

    for (int current : list){
      if (current >= value){
        break;
      }

      i++;
    } 

    list.add(i, value);
	}
	
  	private static void insertSortedString(LinkedList<String> list, String value) {
    if (list == null){
      return;
    }

    int i = 0;

    for (String current : list){
      if (value.compareTo(current) >= 0){
        break;
      }

      i++;
    } 

    list.add(i, value);
	}

	public static void removeMaximumValues(LinkedList<String> list, int N) {
    if (list == null || N <= 0){
      return;
    }

    LinkedList<String> topN = new LinkedList<String>();

    String top = list.peekFirst();
    topN.add(top);


    for (String current : list){
      String limit = topN.peekLast();
      
      if (current.compareTo(limit) >= 0 && !topN.contains(current)){
        insertSortedString(topN, current);

        if(topN.size() > N){
          topN.removeLast();
        }
      }  
    }

    while(topN.size() > 0){
      String current = topN.remove();

      while (list.contains(current)){
        list.remove(current);
      }
    }
	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
    boolean result = false;

    if (one == null || two == null || two.size() == 0) {
       return result; 
    }

    Iterator<Integer> iter = two.iterator();
    int compare;

    for (int current : one){

      if (iter.hasNext()){
        compare = iter.next();
      } else {
        result = true;
        break;
      }

      if (current != compare){
        iter = two.iterator();
      }
     
    }

    if (!iter.hasNext()){
      result = true;
    }
		
		return result;
	}
}
