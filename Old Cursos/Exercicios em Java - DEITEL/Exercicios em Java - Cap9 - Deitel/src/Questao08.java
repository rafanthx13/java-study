/**
 * Created by Rafael on 27/05/2017.
 */

/*
9.8 (Hierarquia de herança Quadrilateral) Escreva uma hierarquia de herança para as classes Quadrilateral, Trapezoid,
Parallelogram, Rectangle e Square. Utilize Quadrilateral como a superclasse da hierarquia. Crie e use uma classe Point
para representar os pontos em cada forma. Faça a hierarquia o mais profunda possível (isto é, com muitos níveis). Especifique as variáveis
de instância e os métodos para cada classe. As variáveis de instância private de Quadrilateral devem ser os pares de coordenadas
x-y para os quatro pontos que delimitam o Quadrilateral. Escreva um programa que instancia objetos de suas classes e gera saída da
área de cada objeto (exceto Quadrilateral).
 */

import java.util.Scanner;

class Trapezoid extends Quadrilateral{

}

class Parallelogram extends Quadrilateral{

}

class Rectangle extends Quadrilateral{

}

class Losango extends Parallelogram{

}


public class Questao08 {

    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


        Square square = new Square();


    }
}

class Point{

    private int x;
    private int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Quadrilateral{

    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    static public int deltax(Point p1, Point p2){
        return Math.abs(p1.getX() - p2.getX());
    }

    static public int deltay(Point p1, Point p2){
        return Math.abs(p1.getY() - p2.getY());
    }

    static public double delta(Point p1, Point p2){
        return Math.sqrt( Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }



    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public Point getP4() {
        return p4;
    }

    public void setP4(Point p4) {
        this.p4 = p4;
    }
}

class Square extends Quadrilateral{


    public int distancia_pontos_quadrado(Point p1, Point p2){
        if(p1.getX() == p2)

    }

    public boolean inserir_square(Point p1, Point p2, Point p3, Point p4){

        if(Quadrilateral.deltax(p1,p2) == Quadrilateral.deltax(p1,p3) && Quadrilateral.deltax(p1,p4) = Quadrilateral.deltax(p1,p3)){

        }




    }


    public void inserir_pontos(){
            System.out.printf("Digite a codenada do eixo x para P1\n==> ");
            int x1 = Questao08.input.nextInt();
            System.out.printf("Digite a codenada do eixo y para P1\n==> ");
            int y1 = Questao08.input.nextInt();
            System.out.printf("Digite a codenada do eixo x para P2\n==> ");
            int x2 = Questao08.input.nextInt();
            System.out.printf("Digite a codenada do eixo y para P2\n==> ");
            int y2 = Questao08.input.nextInt();
    }

    public boolean inserir_square(int x1, int y1, int x2, int y2)

}


