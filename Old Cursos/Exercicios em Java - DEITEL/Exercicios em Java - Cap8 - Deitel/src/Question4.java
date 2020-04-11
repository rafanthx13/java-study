/**
 * Created by Rafael on 15/05/2017.
 */

/*  Question 4 - Chapter 8
(Rectangle Class) Create a class Rectangle with attributes length and width, each of which
defaults to 1. Provide methods that calculate the rectangle’s perimeter and area. It has set and get
methods for both length and width. The set methods should verify that length and width are each
floating-point numbers larger than 0.0 and less than 20.0. Write a program to test class Rectangle.
 */

import java.util.Scanner;
public class Question4 {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int op, var;
        Rectangle ret = new Rectangle();

        do{
            System.out.printf("\tManipular Retandulo Criado\n  1 - Mudar largura\n  2 - Mudar Comprimento\n");
            System.out.printf("  3 - Calcular Area\n  4 - Calcular perimetro\n  5 - Status do Retangulo\n  -1 - Pra SAIR\n\t==> ");
            op = Question4.input.nextInt();
            if(op == 1){
                System.out.printf("Digite a largura < 20.0 e > 0.0:\n==> ");
                var = Question4.input.nextInt();
                ret.setWidth(var);
            }
            if(op == 2){
                System.out.printf("Digite o comprimneto < 20.0 e > 0.0:\n==> ");
                var = Question4.input.nextInt();
                ret.setLength(var);
            }
            if(op == 3){
                System.out.printf("Area = %f\n", ret.area());
            }
            if(op == 4){
                System.out.printf("Perimentro = %f", ret.perimetro());
            }
            if(op == 5){
                System.out.printf(" ==> compr: %f\n ==> largu: %f", ret.getLength(),ret.getWidth());
            }
            System.out.println("\n");
        }while (op != -1);
        System.out.println("END\n");
    }


}

// Tem 2 classe, entao o Rectangle tem que ser Nao public
class Rectangle {

    private double width = 1;
    private double length = 1;

    public double area(){
        return this.getWidth()* this.getLength();
    }

    public double perimetro(){
        return  (2*this.getWidth() + 2*this.getLength());
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width > 0.0 && width < 20.0){
            this.width = width;
        }
    }

    public double getLength() {

        return length;
    }

    public void setLength(double length) {
        if(length > 0.0 && length < 20.0){
            this.length = length;
        }
    }
}


