import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlujoLineas {
    public static void main(String args[]) throws IOException{
        Pattern patron = Pattern.compile("\\s+");

        // Obtener líneas del fichero
        Stream<String> lineas = Files.lines(Paths.get("alicia.txt"), StandardCharsets.ISO_8859_1);

        // Trabajo con flujo de líneas, contador de palabras
        TreeMap<String, Long> contador_palabras = lineas.map(linea -> linea.replaceAll("(?!')\\p{Punct}", "")).
                flatMap(linea -> patron.splitAsStream(linea)).filter(palabra -> !palabra.isEmpty()).
                collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));

        contador_palabras.entrySet().stream().forEach(entrada -> {
                    System.out.println(entrada.getKey() + " " + entrada.getValue());
        });

        // Obtener una estructura con las letras y aquellas palabras que las tienen como inicial
        TreeMap<Character, List<Map.Entry<String, Long>>> indice_iniciales = contador_palabras.entrySet().stream().
                collect(Collectors.groupingBy(entrada -> entrada.getKey().charAt(0), TreeMap::new, Collectors.toList()));

    }
}
