/**
 * Created by Rafael on 28/04/2017.
 */

/*      Chapter 6 - Question 10
(Rounding Numbers) To round numbers to specific decimal places, use a statement like
    y = Math.floor(x * 10 + 0.5) / 10;
which rounds x to the tenths position (i.e., the first position to the right of the decimal point), or
    y = Math.floor(x * 100 + 0.5) / 100;
which rounds x to the hundredths position (i.e., the second position to the right of the decimal
point). Write an application that defines four methods for rounding a number x in various ways:
    a) roundToInteger(number)
    b) roundToTenths(number)
    c) roundToHundredths(number)
    d) roundToThousandths(number)
For each value read, your program should display the original value, the number rounded to the
nearest integer, the number rounded to the nearest tenth, the number rounded to the nearest hundredth
and the number rounded to the nearest thousandth.
 */

public class Question_10_Round {

    public double roundToInteger(double num){
        return Math.floor(num);
    }

    public double roundToTenths(double num){
        return Math.floor( (num * 10 + 0.5)) / 10;
    }

    public double roudToHundredths(double num){
        return Math.floor( (num * 100 + 0.5)) / 100;
    }

    public double roundToThousandths(double num){
        return Math.floor( (num * 1000 + 0.5)) / 1000;
    }

    public double roundToPersonalizado(double num, int qtd){
        return  Math.floor((num * (Math.pow(10.0,qtd)) + 0.5)) / (Math.pow(10.0,qtd));
    }


}
