import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

    public void obtenerMaximo(){
        // Igual que para mínimo. Ahora nos vamos a asegurar de que es un entero
        OptionalInt maximo = IntStream.of(valores).max();
        if (maximo.isPresent()){
            int entero_maximo = maximo.getAsInt();
            System.out.printf("Máximo: %d", entero_maximo);
        }
    }

    // Suma mediante operación reduce
    public void obtenerSumaReduce(){
        // Elemento identidad es 0 para que la primera operación sea sumar 0 al primer elemento
        int suma = IntStream.of(valores).reduce(0, (x, y) -> {
            System.out.println("Valores de x e y: " + x + " " + y);
            return x + y;
        });
        System.out.println("Suma total: " + suma);
    }

    // Obtención de producto con reduce
    public void obtenerProductoReduce(){
        int producto = IntStream.of(valores).reduce(1, (x, y) -> {
            System.out.println("Valores de x e y: " + x + " " + y);
            return x * y;
        });
        System.out.println("Producto total: " + producto);
    }

    // Obtención de producto ampliado (para evitar desbordamiento)
    public void obtenerProductoAmpliado(){
        double producto = IntStream.of(valores).asDoubleStream().reduce(1,(x, y) -> {
            System.out.println("Valores de x e y: " + x + " " + y);
            return x * y;
        });
        System.out.println("Producto total: " + producto);
    }

    // Suma de valores al cuadrado
    public void obtenerSumaCuadrados(){
        double suma = IntStream.of(valores).asDoubleStream().reduce(0, (x, y) -> {
            y = y*y;
            System.out.println("Valores de x² e y²: " + x + " " + y);
            return x + y;
        });
        System.out.println("Suma total de cuadrados: " + suma);
    }

    // Obtención de la media
    public void obtenerMedia(){
        OptionalDouble media = IntStream.of(valores).average();
        System.out.println("Media: " + media.getAsDouble());
    }

    // Filtrado de valores pares
    public void filtrarValoresPares(){
        int[] filtrado = IntStream.of(valores).filter(x -> x % 2 == 0).toArray();
    }

    // Filtrar valores pares y ordenar
    public void filtrarValoresParesYOrdenar(){
        int[] filtrado_y_ordenado = IntStream.of(valores).filter(x -> x % 2 == 0).sorted().toArray();
    }

    // Filtrar pares y mayores que un valor
    public void filtrarValoresParesMayoresQue(int valor){
        IntPredicate par = x -> x % 2 == 0;
        IntPredicate mayor_que = x -> x > valor;
        IntStream.of(valores).filter(par.and(mayor_que)).sorted().forEach(System.out::println);
    }

    // Convertir colección
    public void convertirColección(){
        double[] resultado = IntStream.of(valores).filter(x -> x % 2 == 0).mapToDouble(x -> x*1.5).toArray();
    }

    public static void main(String args[]){
        OperacionesIntStream objetos = new OperacionesIntStream(20);
        objetos.filtrarValoresParesMayoresQue(20);
    }
}
