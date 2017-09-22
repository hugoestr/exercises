import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.nio.file.*;
import java.util.stream.*;
import java.lang.*;


/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
    ArrayList<Sentence> result = new ArrayList<Sentence>();
    Path file;

    if (filename == null || filename == ""){
      return result;
    }

    file = Paths.get(filename);
    if ((!Files.exists(file) || Files.isDirectory(file))){
      return result;
    } 

    try (Stream<String> stream = Files.lines(file)) {
      
      stream.forEach( line -> {

        if (wellFormatted(line)){
          Sentence sentence = toSentence(line);
          result.add(sentence); 
        }
      
      });
    }
    catch( Exception e){
      return result;
    }  

		return result;
	}

  private static boolean wellFormatted(String line){
    String[] parts = line.trim().split(" ");
    boolean result = true;

    if (parts.length <= 1){
      result = false;
    }

    ArrayList<String> valid = 
      new ArrayList<String>(Arrays.asList("-2", "-1", "0", "1", "2"));


    if (!valid.contains(parts[0])){
      result = false;
    }

    return result;
  }


  private static Sentence toSentence(String line){
    String[] chomped = line.trim().split(" ");
    int value = Integer.parseInt(chomped[0]);
    
    String text = line.replace(chomped[0], "").trim();
    Sentence result = new Sentence(value, text);


    return result;
  }
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
	
    if (sentences == null || sentences.isEmpty()){
      return new  HashSet<Word>();
    }

    HashMap<String, Word> count = getWords(sentences);
    Set<Word> result = toSet(count);  

		return result; 
	}

  private static HashMap<String, Word> getWords(List<Sentence> sentences){
    HashMap<String,Word> count = new HashMap<String, Word>(); 

    for (Sentence s : sentences){
      if (s != null){
        int score = s.getScore();
        String[] words = s.getText().split(" ");

        for (String t : words){
          String token = t.toLowerCase();

          if (validWord(token)) {
            if (count.containsKey(token)){
              Word w = count.get(token);
              w.increaseTotal(score);
            }else {
              Word newWord = new Word(token);
              newWord.increaseTotal(score);
              count.put(token, newWord);
            }
          }
        }
      }
    }

    return count;
  }

  private static boolean validWord(String input){
    return Character.isAlphabetic(input.charAt(0));
  }

  private static Set<Word> toSet(HashMap<String, Word> input){
    HashSet<Word> result = new HashSet<Word>();

    for (Map.Entry<String, Word> entry : input.entrySet()){
      result.add(entry.getValue());
    }

    return result;
  }
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
    HashMap<String, Double> result  = new HashMap<String, Double>();
	
    if (words == null || words.isEmpty()){
      return result;
    }

    for (Word w : words){
      if (w != null){
        result.put(w.getText(), w.calculateScore());
      }
    }

		return result; 
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
	    double total = 0;
      int count = 0;
      double score = 0;

      if (wordScores == null || wordScores.isEmpty() ||
        sentence == null || sentence.isEmpty() ){
        return total;
      }

      String[] words = sentence.split(" ");

      for (String w : words){
        String word = w.toLowerCase();
     
        if (validWord(word)){
          if (wordScores.containsKey(word)){
            score = wordScores.get(word);
            total += score;
          }

          count++;
        } 
        
      }

      if (count == 0){
        return 0;
      }

		  return total / count; 
	  }
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
