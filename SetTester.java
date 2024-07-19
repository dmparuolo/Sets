/*
 * Student information for assignment:
 *
 * Number of slip days used: 2
 * Student 1 (Student whose turnin account is being used)
 *  UTEID: dmp3588
 *  email address: dominicparuolo78@gmail.com
 *  Grader name: Nidhi
 *
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: 
 * 
 * 1. I think the orders of SortedSet and UnsortedSet for the two processText methods
 * are O(N^2) and O(NM), while the orders of the HashSet and TreeSet processText methods
 * are O(NlogN) and O(NlogM).
 * 
 * 2. My add methods for both Sorted and Unsorted sets are O(N). I think the add methods
 * for HashSets and TreeSets are O(logN).
 * 
 * 3. The difference between the printing of a TreeSet and a HashSet is that the TreeSet
 * prints everything in a sorted order, in this case alphabetical, while the HashSet
 * is not sorted.
 * 
 * SortedSet
 * File         Size (kb)  Inc.    Total Words  Inc.    Unique Words  Inc.    Actual Time  Inc.
 * Book4.txt    84         -       14518        -       12407         -       0.13 sec     -
 * Book2.txt    286        3.4x    49839        3.4x    9447          0.7x    0.93 sec     7.1x
 * Book3.txt    351        1.2x    61314        1.2x    10077         1.1x    1.11 sec     1.2x
 * Book1.txt    660        1.8x    106290       1.7x    22593         2.2x    7.40 sec.    6.6x
 * 
 * UnsortedSet
 * File         Size (kb)  Inc.    Total Words  Inc.    Unique Words  Inc.    Actual Time  Inc.
 * Book4.txt    84         -       14518        -       4077          -       0.19 sec     -
 * Book2.txt    286        3.4x    49839        3.4x    9447          0.7x    0.83 sec     4.3x
 * Book3.txt    351        1.2x    61314        1.2x    10077         1.1x    1.16 sec     1.4x
 * Book1.txt    660        1.8x    106290       1.7x    22593         2.2x    5.12 sec.    4.4x
 * 
 * HashSet
 * File         Size (kb)  Inc.    Total Words  Inc.    Unique Words  Inc.    Actual Time  Inc.
 * Book4.txt    84         -       14518        -       4077          -       0.04 sec     -
 * Book2.txt    286        3.4x    49839        3.4x    9447          0.7x    0.05 sec     1.2x
 * Book3.txt    351        1.2x    61314        1.2x    10077         1.1x    0.07 sec     1.4x
 * Book1.txt    660        1.8x    106290       1.7x    22593         2.2x    0.16 sec.    2.3x
 * 
 * TreeSet
 * File         Size (kb)  Inc.    Total Words  Inc.    Unique Words  Inc.    Actual Time  Inc.
 * Book4.txt    84         -       14518        -       4077          -       0.03 sec     -
 * Book2.txt    286        3.4x    49839        3.4x    9447          0.7x    0.09 sec     3x
 * Book3.txt    351        1.2x    61314        1.2x    10077         1.1x    0.10 sec     1.1x
 * Book1.txt    660        1.8x    106290       1.7x    22593         2.2x    0.15 sec.    1.5x
 * 
 * 
 * 
 * CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class: It would be unwise
 * to implement all three methods in the AbstractSet class because in order to implement any one
 * of the three methods, you must make calls to the other two methods. Therefore, if all three
 * were implemented, one method would call another method just for that method to call the
 * original method again even though none of them can be actually fully independently implemented
 * so it would just turn into an infinite loop of calls between the methods. For example, if the
 * Abstract class had all three methods implemented and the union method was called, the union
 * methods code calls the intersect method which would then internally call the union method which
 * calls the intersect method and so on forever.
 */

public class SetTester {

    public static void main(String[] args) {    	
    	
    	//Unsorted Tests
    	
    	//empty constructor Test
    	ISet<String> a1 = new UnsortedSet<>();
    	ISet<String> a2 = new UnsortedSet<>();
    	ISet<String> a3 = new UnsortedSet<>();
    	if (a1.toString().equals("()")) {
    		System.out.println("Passed Unsorted empty constructor test.");
    	} else {
    		System.out.println("Failed Unsorted empty constructor test.");
    	}
    	
    	//add Test
    	a1.add("G");
    	if (a1.toString().equals("(G)")) {
    		System.out.println("Passed Unsorted add test.");
    	} else {
    		System.out.println("Failed Unsorted add test.");
    	}
    	
    	//addAll Test
    	a2.add("E");
    	a2.add("G");
    	a2.add("N");
    	a2.add("I");
    	a2.add("E");
    	a1.addAll(a2);
    	if (a1.toString().equals("(G, E, N, I)")) {
    		System.out.println("Passed Unsorted addAll test.");
    	} else {
    		System.out.println("Failed Unsorted addAll test.");
    	}
    	
    	//clear Test
    	a2.clear();
    	if (a2.toString().equals("()")) {
    		System.out.println("Passed Unsorted clear test.");
    	} else {
    		System.out.println("Failed Unsorted clear test.");
    	}
    	
    	//contains Test
    	if (a1.contains("I")) {
    		System.out.println("Passed Unsorted contains test.");
    	} else {
    		System.out.println("Failed Unsorted contains test.");
    	}
    	
    	//containsAll Test
    	a2.add("I");
    	a2.add("E");
    	if (a1.containsAll(a2)) {
    		System.out.println("Passed Unsorted containsAll test.");
    	} else {
    		System.out.println("Failed Unsorted containsAll test.");
    	}
    	
    	//difference Test
    	a3 = a1.difference(a2);
    	if (a3.toString().equals("(G, N)")) {
    		System.out.println("Passed Unsorted difference test.");
    	} else {
    		System.out.println("Failed Unsorted difference test.");
    	}
    	
    	//equals Test
    	a2.clear();
    	a2.add("G");
    	a2.add("N");
    	if (a2.equals(a2)) {
    		System.out.println("Passed Unsorted equals test.");
    	} else {
    		System.out.println("Failed Unsorted equals test.");
    	}
    	
    	//intersection Test
    	a3 = a1.intersection(a2);
    	if (a3.toString().equals("(G, N)")) {
    		System.out.println("Passed Unsorted intersection test.");
    	} else {
    		System.out.println("Failed Unsorted intersection test.");
    	}
    	
    	//iterator Test
    	Iterator<?> a1It = a1.iterator();
    	if (a1It.next().equals("G")) {
    		System.out.println("Passed Unsorted iterator test.");
    	} else {
    		System.out.println("Failed Unsorted iterator test.");
    	}
    	
    	//remove Test
    	a1.remove("N");
    	if (a1.toString().equals("(G, E, I)")) {
    		System.out.println("Passed Unsorted remove test.");
    	} else {
    		System.out.println("Failed Unsorted remove test.");
    	}
    	
    	//size Test
    	if (a1.size() == 3) {
    		System.out.println("Passed Unsorted size test.");
    	} else {
    		System.out.println("Passed Unsorted size test.");
    	}
    	
    	//union Test
    	a3 = a1.union(a2);
    	if (a3.toString().equals("(G, E, I, N)")) {
    		System.out.println("Passed Unsorted union test.");
    	} else {
    		System.out.println("Passed Unsorted union test.");
    	}
    	
    	//Sorted Tests
    	
    	//empty constructor Test
    	a1 = new SortedSet<>();
    	if (a1.toString().equals("()")) {
    		System.out.println("Passed Sorted empty constructor test.");
    	} else {
    		System.out.println("Failed Sorted empty constructor test.");
    	}
    	
    	//constructor with parameter test
    	a2 = new SortedSet<>(a3);
    	if (a2.toString().equals("(E, G, I, N)")) {
    		System.out.println("Passed Sorted constructor with parameter test.");
    	} else {
    		System.out.println("Failed Sorted constructor with parameter test.");
    	}
    	
    	SortedSet<String> a4 = new SortedSet<>();
    	
    	//min Test
    	a4.add("N");
    	a4.add("A");
    	if (a4.min().equals("A")) {
    		System.out.println("Passed Sorted min test.");
    	} else {
    		System.out.println("Failed Sorted min test.");
    	}
    	
    	//max Test
    	if (a4.max().equals("N")) {
    		System.out.println("Passed Sorted max test.");
    	} else {
    		System.out.println("Failed Sorted max test.");
    	}
    	
    	//add Test
    	a1.add("O");
    	a1.add("A");
    	a1.add("E");
    	if (a1.toString().equals("(A, E, O)")) {
    		System.out.println("Passed Sorted add test.");
    	} else {
    		System.out.println("Failed Sorted add test.");
    	}
    	
    	//addAll Test
    	a1.addAll(a4);
    	if (a1.toString().equals("(A, E, N, O)")) {
    		System.out.println("Passed Sorted addAll test.");
    	} else {
    		System.out.println("Failed Sorted addAll test.");
    	}
    	
    	//clear Test
    	a2 = new SortedSet<>();
    	a2.add("R");
    	a2.add("F");
    	a2.clear();
    	if (a2.toString().equals("()")) {
    		System.out.println("Passed Sorted clear test.");
    	} else {
    		System.out.println("Failed Sorted clear test.");
    	}
    	
    	//contains Test
    	if (a1.contains("N")) {
    		System.out.println("Passed Sorted contains test.");
    	} else {
    		System.out.println("Failed Sorted contains test.");
    	}
    	
    	//containsAll Test
    	if (a1.containsAll(a4)) {
    		System.out.println("Passed Sorted containsAll test.");
    	} else {
    		System.out.println("Failed Sorted containsAll test.");
    	}
    	
    	//difference Test
    	a2 = a1.difference(a4);
    	if (a2.toString().equals("(E, O)")) {
    		System.out.println("Passed Sorted difference test.");
    	} else {
    		System.out.println("Failed Sorted difference test.");
    	}
    	
    	//equals Test
    	a4.addAll(a2);
    	if (a4.equals(a1)) {
    		System.out.println("Passed Sorted equals test.");
    	} else {
    		System.out.println("Failed Sorted equals test.");
    	}
    	
    	//intersection Test
    	a4.add("Z");
    	a2 = a1.intersection(a4);
    	if (a2.toString().equals("(A, E, N, O)")) {
    		System.out.println("Passed Sorted intersection test.");
    	} else {
    		System.out.println("Failed Sorted intersection test.");
    	}
    	
    	//iterator Test
    	Iterator<?> a1it = a1.iterator();
    	if (a1it.next().equals("A")) {
    		System.out.println("Passed Sorted iterator test.");
    	} else {
    		System.out.println("Failed Sorted iterator test.");
    	}
    	
    	//remove Test
    	a1.remove("O");
    	if (a1.toString().equals("(A, E, N)")) {
    		System.out.println("Passed Sorted remove test.");
    	} else {
    		System.out.println("Failed Sorted remove test.");
    	}
    	
    	//size Test
    	if (a1.size() == 3) {
    		System.out.println("Passed Sorted size test.");
    	} else {
    		System.out.println("Failed Sorted size test.");
    	}
    	
    	//union Test
    	a2 = a1.union(a4);
    	if (a2.toString().equals("(A, E, N, O, Z)")) {
    		System.out.println("Passed Sorted union test.");
    	} else {
    		System.out.println("Failed Sorted union test.");
    	}
    	

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
//         try {
//         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         }
//         catch(Exception e) {
//         System.out.println("Unable to change look and feel");
//         }
//         Scanner sc = new Scanner(System.in);
//         String response = "";
//         do {
//         largeTest();
//         System.out.print("Another file? Enter y to do another file: ");
//         response = sc.next();
//         } while( response != null && response.length() > 0
//         && response.substring(0,1).equalsIgnoreCase("y") );

    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}