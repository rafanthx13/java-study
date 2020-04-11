/**
 * Created by Rafael on 24/04/2017.
 */

/*
5.24 (Diamond Printing Program) Write an application that prints the following diamond
shape. You may use output statements that print a single asterisk (*), a single space or a single newline
character. Maximize your use

            *
           ***
          *****
         *******
        *********
         *******
          *****
           ***
            *
			
 */
public class Question24 {

    public static void main(String[] args) {

        int blank, spot, controler = 1;
		
        for(int count = 0; count < 5; count++){
          
            for(blank = 4; blank >= count; blank--){
                System.out.print(" ");
            }
            for(spot = 0;  spot < ((count*2)+1); spot++){
                System.out.print("*");
            }
            System.out.println();
            
        
        }
		
		for(int count = 1; count < 5; count++){
          
            for(blank = 0; blank < count+1; blank++){
                System.out.print(" ");
            }
            
			
            for(spot = 1;  spot < 9 -  (((count-1)*2)+1); spot++){
                System.out.print("*");
            }
            System.out.println();
        }
        
		
		



    }
}
