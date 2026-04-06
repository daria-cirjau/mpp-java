package eu.ase.io.lambda;

public class MainLambda {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        System.out.println(addition.operation(10, 5));
        MathOperation substraction = (a, b) -> a - b;
        System.out.println(addition.operation(10, 5));
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        MathOpClass tester = new MathOpClass();

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, substraction));
        System.out.println("10 * 5 = " + tester.operate(10, 5, multiplication));

        System.out.println("10 / 5 = " + tester.operate(10, 5, (x1, x2) -> x1 / x2));

        MathOperation additionVerbose = (a, b) -> {
            int result = a + b;
            return result;
        };
    }
}
