/**
 * Created by Rafael on 28/04/2017.
 */

/*
6.24 (Perfect Numbers) An integer number is said to be a perfect number if its factors, including
1 (but not the number itself), sum to the number. For example, 6 is a perfect number, because 6 =
1 + 2 + 3. Write a method isPerfect that determines whether parameter number is a perfect number.
Use this method in an application that displays all the perfect numbers between 1 and 1000. Display
the factors of each perfect number to confirm that the number is indeed perfect. Challenge the computing
power of your computer by testing numbers much larger than 1000. Display the results.
 */

public class Question24 {

    public static void main(String[] args) {
        int sum;
        System.out.println("Numeros Perfeitos:");
        for(int perf = 1; perf <= 1000; perf++){
            sum = 0;
            for(int i = 1; i < perf; i++){
                if(perf % i == 0){
                    sum += i;
                }
            }
            if(sum == perf){
                System.out.printf("\t%4d eh PERFEITO\n", perf);
            }
        }
        System.out.println();


    }
}
