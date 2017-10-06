/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
	 TreeMap<String, PriorityQueue<Integer>> result = new	TreeMap<String, PriorityQueue<Integer>>();
		
   if (allUsersRatings == null || allUsersRatings.isEmpty()){
    return result;
   }
   
   PriorityQueue<Integer> priority;

   for (UserMovieRating review : allUsersRatings){
      if (review == null || 
          review.getMovie() == null || 
          review.getMovie() == "" ||
          review.getUserRating() < 0){
          continue;
      }

      String title = review.getMovie().toLowerCase();

      if (!result.containsKey(title)){
         priority = new PriorityQueue<Integer>();
         priority.add(review.getUserRating());

         result.put(title, priority);
      } else {
         priority = result.get(title);
         priority.add(review.getUserRating());
      }
   }
		
		return result; 
	}

}
