import java.util.List;
import java.util.regex.*;

public class Main {

    public static void removeDuplicatesAndSort()
    {
        List<Integer> numbers = List.of(5, 3, 8, 3, 9, 1, 5, 7, 8);
        System.out.println("Original numbers: " + numbers);

        List<Integer> distinctSortedNumbers = numbers.stream()
                                                     .distinct()
                                                     .sorted()
                                                     .toList();

        System.out.println("Distinct and Sorted numbers: " + distinctSortedNumbers);
    }

    public static void checkPattern(String pattern, String input)
    {

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        if(matcher.matches())
        {
            System.out.println("input: " +  input + " matches the Regex Pattern for valid email");
        }
        else
        {
            System.out.println("input: " +  input + " DOES NOT match the Regex Pattern for valid email");
        }
    }


    public static void main(String[] args) 
    {
        System.out.println("Hello, Welcome to the Java Stream & Regex Demos!");

        System.out.println("---- START : Regex pattern matching demo ----");
      
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String emailInput1 = "kevinm2020@icloud.com";
        String emailInput2 = "kevinm2020@icloud";
        checkPattern(emailPattern, emailInput1);
        checkPattern(emailPattern, emailInput2);

       

        System.out.println("---- START : Remove Duplicates and Sort ----");
        removeDuplicatesAndSort();

        System.out.println("---- START : Cubing Numbers in a List ----");
        CubeNumbers.cubeList();

        System.out.println("---- START : Summing First 50 Natural Numbers ----");
        SumFirst50.sumFirst50();

        System.out.println("---- START : Even Numbers from an array ----");
        EvenNumbers.evenNumbers();

        System.out.println("---- START : Square numbers and get results who square > 20 ----");
        SquareAndFilter.squareAndFilter();

        System.out.println("---- START : Count string longer than 5 characters----");
        CountStrings.countStrings();

        System.out.println("---- START : Convert list of strings to Uppercase----");
        UpperCaseStrings.upperCaseStrings();

        System.out.println("---- START : flatMap Demo ----");
        FlatMapDemo.flatMapExample();
        FlatMapDemo.flatMapExample2();

        System.out.println("---- START : Find the maximum number using reduce() ----");
        MaxUsingReduce.maxUsingReduce();
        
    }
}

/*
Notes: 

With Collection objects, it's simple to create a stream 
using the .stream() method.
Example:
         strings.stream() //creates a stream from the list


map() changes values — flatMap() changes structure.

*/