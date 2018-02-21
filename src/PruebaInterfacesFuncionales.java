import java.util.function.*;

public class PruebaInterfacesFuncionales {
    public static void main(String args[]){
        // Binary operator, que realiza una operación con dos parámetros, en este caso int
        IntBinaryOperator operador = (int x, int y) -> {return x+y;};

        System.out.println("Operador 1 - Resultado: " + (operador.applyAsInt(3, 10)));

        // Definición del operador anterior sin utilizar funciones lambda
        IntBinaryOperator operadorV1 = new IntBinaryOperator() {
            @Override
            public int applyAsInt(int i, int i1) {
                return i+i1;
            }
        };

        System.out.println("Operador 1.1 - Resultado: " + (operadorV1.applyAsInt(3, 10)));

        // Mismo caso anterior pero con parámetros de tipo double
        DoubleBinaryOperator operador2 = (double x, double y) -> {return x+y;};

        System.out.println("Operador 2 - Resultado: " + (operador2.applyAsDouble(3.4, 5.1)));

        // Consumer que recibe un dato y no devuelve nada
        Consumer<Integer> operador3 = valor -> System.out.println("Mensaje de operador 3");

        operador3.accept(15);

        // Expresión lambda sin argumentos ni devolución
        Runnable operador4 = () -> System.out.println("Mensaje de operador 4");

        operador4.run();
    }
}
