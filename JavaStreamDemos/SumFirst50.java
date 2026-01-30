import java.util.stream.*;

//this class will count from 0-49 and sum those numbers
//then print the result to the console

public class SumFirst50 
{

    public static void sumFirst50()
    {
        //end goal: aggreagte sum into a single int variable
        int sum = Stream.iterate(0, n -> n + 1)
                        .limit(50)
                        .reduce(0, Integer::sum);

        System.out.println("The sum of the first 50 natural numbers is: " + sum);
    }
    
}

/*

*/
