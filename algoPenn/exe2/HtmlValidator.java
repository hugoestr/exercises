import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
    Stack<HtmlTag> result = new Stack<HtmlTag>();
    HtmlTag current; 

    while (!tags.isEmpty()){
      current = tags.remove();
    
      if (current.isOpenTag()){                  // it is open tag
        result.push(current); 
      } else {
        
        if (current.isSelfClosing()) {           //  it is selfcontaining
          continue;
        }  
       
        if (!result.isEmpty()){                  // closing tags with stack
          
          if (result.peek().matches(current)){
            result.pop();
          } else {
            return result;  
          }
        } else {                                // closing tag empty stack
           return null;
        }
      }     
    }
		
		return result;
	}
	

}

