import java.util.List;

//This class will cube each number in a list
public class CubeNumbers 
{
    
    public static void cubeList()
    {
      
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println("Original List of Numbers:" + numbers);
        
        List<Integer> cubedNumbers = numbers.stream()
                                            .map(n -> n * n * n)
                                            .toList();
        System.out.println("Cubed Numbers: " + cubedNumbers);
    }
}
