/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
	  ArrayList<String> result = new ArrayList<String>();

    if (movieRatings == null){
      return result;
    }

      for (Map.Entry<String, PriorityQueue<Integer>> item : movieRatings.entrySet()){
        if (item != null && item.getKey() != null && item.getKey() != ""){
          result.add(item.getKey());
        }
      }
		
		return result; 
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		ArrayList<String> result = new ArrayList<String>();

    if (movieRatings == null){
      return result;
    }
  
    PriorityQueue<Integer> queue;

    for (Map.Entry<String, PriorityQueue<Integer>> item : movieRatings.entrySet()){
      if (item != null && item.getKey() != null && item.getKey() != ""){
        queue = item.getValue();

        if (queue.peek() > rating){ 
          result.add(item.getKey());
        }
      }
    }
		
		return result;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
	  TreeMap<String, Integer> result = new TreeMap<String, Integer>();
    ArrayList delete = new ArrayList();  
	
    if (movieRatings == null || movieRatings.isEmpty()){
      return result;
    }

    PriorityQueue<Integer> queue;
    
    for (Map.Entry<String, PriorityQueue<Integer>> item : movieRatings.entrySet()){
      
      if (item != null && 
          item.getKey() != null 
          && item.getKey() != ""){
        
        queue = item.getValue();

        int erased = 0;

        while (queue.size() > 0 && queue.peek() < rating){
          queue.poll();
          erased++;  
        }

        if (erased > 0){
          result.put(item.getKey(), erased);
        }

        if (queue.size() == 0){
          delete.add(item.getKey());
        }
      }
    }

    deleteItems(movieRatings, delete);

		return result; 
	}

  private static void deleteItems(TreeMap<String, PriorityQueue<Integer>> movieRatings, List<String> toDelete){
    for (String key : toDelete){
      movieRatings.remove(key);
    }
  }
  
}
