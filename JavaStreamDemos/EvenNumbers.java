import java.util.List;
import java.util.Arrays;

public class EvenNumbers {
    
    public static void evenNumbers()
    {

        //Array
          int[] nums = {1,2,3,4,5,6,7,8,9,10};
          System.out.println("Original List of Numbers:");
          for (int num : nums) {
              System.out.print(num + " ");
          }
          System.out.println();

        //Stream to filter even numbers - end result List<Integer> evenNums
            List<Integer> evenNums =  Arrays.stream(nums)
                                        .filter(n -> n % 2 == 0)
                                        .boxed()        //wrap int to Integer
                                        .toList();

            System.out.println("Even Numbers: " + evenNums);
    }
}
