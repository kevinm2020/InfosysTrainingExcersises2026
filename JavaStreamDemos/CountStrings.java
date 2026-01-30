import java.util.List;

public class CountStrings 
{
    public static void countStrings() 
    {
        List<String> strings = List.of("apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "lemonade");
        System.out.println("Original strings: " + strings);
       
        //End product: long count of strings longer than 5 characters
        long count = strings.stream()
                .filter(s -> s.length() > 5)
                .count();

        System.out.println("Number of strings longer than 5 characters: " + count);
    }
    
}
