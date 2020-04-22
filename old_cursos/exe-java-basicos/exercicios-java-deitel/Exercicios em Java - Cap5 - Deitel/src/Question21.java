/**
 * Created by Rafael on 24/04/2017.
 */

/*  Question 21 - Chpater 5
(Pythagorean Triples) A right triangle can have sides whose lengths are all integers. The set
of three integer values for the lengths of the sides of a right triangle is called a Pythagorean triple.
The lengths of the three sides must satisfy the relationship that the sum of the squares of two of the
sides is equal to the square of the hypotenuse. Write an application that displays a table of the
Pythagorean triples for side1, side2 and the hypotenuse, all no larger than 500. Use a triple-nested
for loop that tries all possibilities. This method is an example of “brute-force” computing. You’ll
learn in more advanced computer science courses that for many interesting problems there’s no
known algorithmic approach other than using sheer brute force.
 */

public class Question21 {

    public static void main(String[] args) {
        int l1 = 0, l2 = 0, hip = 0;

        System.out.println("Lado1 Lado2 Hipotenusa\n");
        for(int i=1; i<500; i++){
            for(int j=1; j<500; j++){
                for(int h=1; h<500; h++){
                    if( ((i*i) + (j*j)) == (h*h)){
                        System.out.printf("%-3d %-3d %-3d\n",i,j,h);
                    }
                }
            }
        }

    }
}
