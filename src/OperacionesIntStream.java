import java.util.OptionalInt;
import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;

public class OperacionesIntStream {
    private int valores[];

    public OperacionesIntStream(int numeroValores){
        valores = new int[numeroValores];
        Random generador = new Random();
        for(int i=0; i < numeroValores; ++i){
            valores[i] = generador.nextInt(101);
        }
    }

    // Método para mostrar los valores (paradigma imperativo)
    public void mostrarValoresImperativo(){
        for(int i=0; i < valores.length; ++i){
            System.out.printf("%d", valores[i]);
        }
        System.out.println();
    }

    // Método para mostrar valores (paradigma funcional)
    public void mostrarValoresFuncional(){
        // Definir el flujo, y con el método "of" se indica sobre qué colección se genera
        IntStream flujo = IntStream.of(valores);

        // Definir la operación a realizar
        IntConsumer operacion = valor -> System.out.printf("%d", valor);

        // Se realiza la operación
        flujo.forEach(operacion);

        System.out.println();
    }

    // Otra forma más directa de definir la función anterior según el paradigma funcional
    public void mostrarValoresFuncional2(){
        IntStream.of(valores).forEach(valor -> System.out.printf("%d", valor));
        System.out.println();
    }

    public void obtenerMinimo(){
        // El tipo OptionalInt actúa como wrapper para el caso en el que la colección esté vacía
        OptionalInt minimo = IntStream.of(valores).min();
        int entero_minimo = minimo.getAsInt();
    }

    public static void main(String args[]){
        OperacionesIntStream objetos = new OperacionesIntStream(1000);
    }
}
