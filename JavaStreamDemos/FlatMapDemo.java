import java.util.List;

public class FlatMapDemo 
{

    public static void flatMapExample()
    {
        //this is a list of lists
        List<List<String>> listOfLists = List.of(
                List.of("apple", "banana"),
                List.of("cherry", "date"),
                List.of("fig", "grape", "kiwi")
        );

        System.out.println("Original list of lists: " + listOfLists);

        // End product: List<String> of all fruits
        List<String> flatMappedList = listOfLists.stream()
                .flatMap(List::stream)
                .toList();

        //We are decoupling the inner lists into a single list
        System.out.println("Flat-mapped list of fruits: " + flatMappedList);
    }

    public static void flatMapExample2()
    {
        List<String> sentences = List.of("Java streams are powerful", "flatMap is important");
        //this is a list of sentences (2 sentaces to be precises, by flatMapping we will get a 
        // list of words)

        //End product: List<String> of all words
        List<String> words = sentences.stream()
                .flatMap(sentence -> List.of(sentence.split(" ")).stream())
                .toList();
        
        System.out.println("Original sentences: " + sentences);
        System.out.println("Flat-mapped list of words: " + words);
    }
    
}

/*
Notes:
What does flatMap do?
flatMap is used to transform each element of a stream into a new stream 
and then flatten those
[ [1,2], [3,4], [5,6] ]
becomes
[1,2,3,4,5,6]
*/