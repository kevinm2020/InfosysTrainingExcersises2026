import java.util.List;

public class UpperCaseStrings 
{

    public static void upperCaseStrings() 
    {
        List<String> strings = List.of("apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "lemonade");
        System.out.println("Original strings: " + strings);
       
        //End product: List<String> of uppercase strings
        List<String> uppercased = strings.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("Uppercase strings: " + uppercased);
    }

}

