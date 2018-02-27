import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamString {
    public static void main(String args[]){
        String[] colores = {"Rojo", "azul", "verde", "Amarillo", "indigo", "Rosa"};

        List<String> colores_mayuscula = Arrays.stream(colores).
                map(cadena -> cadena.toUpperCase()).collect(Collectors.toList());
        // También se puede indicar la referencia funcional a toUpperCase como String::toUpperCase

        // Filtrar para quedarnos con las cadenas por encima de m
        List<String> colores_ordenados = Arrays.stream(colores).filter(cadena -> cadena.compareToIgnoreCase("m") > 0).
                sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
    }
}
