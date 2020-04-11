/**
 * Created by Rafael on 20/04/2017.
 */
public class Question32 {

    // Imprimir um tabuleiro so com essas intruçoes
    /*
        System.out.print(" *");
        System.out.print(" ");
        System.out.println();
     */
    public static void main(String[] args) {

        int i=0, j=0;

        while(j < 4) {
            while (i < 8) {
                System.out.print(" *");
                i++;
            }
            i = 0;
            System.out.println();
            System.out.print(" ");
            while (i < 8) {
                System.out.print(" *");
                i++;
            }
            i = 0;
            System.out.println();
            j++;
        }
    }

}
