import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInteger {
    public static void main(String args[]){
        Integer[] valores = {2,9,5,0,3,7,1,4,8,6};

        // Creación del flujo
        Stream<Integer> flujo = Arrays.stream(valores);

        flujo.forEach(System.out::println);

        List<Integer> lista = Arrays.stream(valores).sorted().collect(Collectors.toList());

        // Crear un flujo a partir de una lista
        List<Integer> lista_ordenada = lista.stream().sorted().collect(Collectors.toList());
    }
}
