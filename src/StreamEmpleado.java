import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEmpleado {
    public static void main(String args[]){
        // Inicialización del array de empleados
        Empleado[] empleados = {
            new Empleado("Juan", "López", 5000, "IT"),
            new Empleado("Almudena", "García", 7600, "IT"),
            new Empleado("Brian", "Hodgson", 1500, "Conexión"),
            new Empleado("Joaquín", "Martínez", 4500.5, "Ventas"),
            new Empleado("Lucas", "Martínez", 3140, "Marketing"),
            new Empleado("Raúl", "García", 7000, "Marketing"),
            new Empleado("Sandra", "Fernández", 6660, "Ventas")
        };

        // Crear un predicado para aquellos que tienen un sueldo superior a 4000€ e inferior a 6000€
        Predicate<Empleado> rango_sueldo = empleado ->
                (empleado.obtenerSueldo() > 4000 && empleado.obtenerSueldo() < 6000);

        // Especificar un comparador para la clase Empleado, por sueldo
        Comparator<Empleado> comparador_sueldo = Comparator.comparing(Empleado::obtenerSueldo);

        // Filtrado + ordenación + mostrado por pantalla
        Arrays.stream(empleados).filter(rango_sueldo).sorted(comparador_sueldo).forEach(System.out::println);

        // Obtener primer elemento que cumple una condición
        Empleado empleado_obtenido = Arrays.stream(empleados).filter(rango_sueldo).sorted(comparador_sueldo).findFirst().get();

        // Referencias funcionales
        Function<Empleado, String> refNombre = Empleado::obtenerNombre;
        Function<Empleado, String> refPrimerApellido = Empleado::obtenerPrimerApellido;

        // Crear un comparador a partir de dichas referencias funcionales
        Comparator<Empleado> comparador_nombres = Comparator.comparing(refPrimerApellido).thenComparing(refNombre);

        // Ordenación de empleados utilizando este criterio/comparador
        System.out.println("Empleados ordenados alfabéticamente:");
        Arrays.stream(empleados).sorted(comparador_nombres).forEach(System.out::println);

        // Mismo caso anterior, pero en orden inverso
        System.out.println("Empleados ordenados alfabéticamente inverso:");
        Arrays.stream(empleados).sorted(comparador_nombres.reversed()).forEach(System.out::println);

        // Obtener apellidos pero sin repetidos
        Stream<String> apellidos = Arrays.stream(empleados).map(Empleado::obtenerPrimerApellido);
        // Se eliminan repetidos
        System.out.println("Apellidos de los empleados, sin repetidos:");
        apellidos.distinct().sorted().forEach(System.out::println);

        // Almacén en lista
        Arrays.stream(empleados).map(Empleado::obtenerPrimerApellido).distinct().collect(Collectors.toList());

        // Obtener empleados agrupados por departamento
        System.out.println("Empleados agrupdos por departamento:");
        // Utilizando GroupingBy se genera un mapa con los departamentos como clave y todos los empleados
        // que pertenecen a dicho departamento formando parte de una lista como valor
        Map<String, List<Empleado>> mapa = Arrays.stream(empleados).collect(Collectors.groupingBy(Empleado::obtenerDepartamento));
        // Este flujo almacena entradas del map
        Stream<Map.Entry<String, List<Empleado>>> flujo_entradas_mapa = mapa.entrySet().stream();
        // Recorrido de entradas (key=departamento, value=lista)
        flujo_entradas_mapa.forEach(entrada -> {
            System.out.println(entrada.getKey());
            entrada.getValue().forEach(System.out::println);
        });

        // Implementar esta tarea SIN programación funcional
        Map<String, List<Empleado>> agrupamiento = new HashMap<>();
        String departamento;
        List<Empleado> listaEmpleados;
        // Considerar cada empleado de la colección
        for (int i=0; i < empleados.length; ++i){
            // Obtener su departamento
            departamento = empleados[i].obtenerDepartamento();

            // Comprobar si existe esa clave en el mapa
            listaEmpleados = agrupamiento.get(departamento);

            // Si no existe lista para ese departamento, hay que crearla
            if (listaEmpleados == null){
                listaEmpleados = new LinkedList<>();
                listaEmpleados.add(empleados[i]);

                // Meto la lista en el mapa
                agrupamiento.put(departamento,listaEmpleados);
            }

            // Si ya existía la lista
            else{
                listaEmpleados.add(empleados[i]);
            }
        }
        // Se itera sobre la colección para imprimir los datos
        Iterator<String> claves = agrupamiento.keySet().iterator();
        String clave;
        while (claves.hasNext()){
            clave = claves.next();
            System.out.println(clave);

            // Listado completo de empleados de la clave
            listaEmpleados = agrupamiento.get(clave);

            // Se itera sobre la colección
            for (Empleado e : listaEmpleados){
                System.out.println(e);
            }
        }
        //****************************************************
        // Mucho más complejo que en programación funcional!!!
        //****************************************************

        //-----------------------------------------------------------------------------------

        // Agrupamiento con funcionalidad completa (especificando tipo de mapa que quiero crear)
        System.out.println("Departamentos y su número de empleados:");
        // Para este caso quiero un map de departamentos con el número de empleados por departamento
        // Segundo argumento de groupingBy: cómo es el mapa resultante
        // Tercer argumento de groupingBy: cómo reducir la lista
        // Resultado devuelto: TreeMap<String,Long>
        TreeMap<String, Long> contadorDepartamentos = Arrays.stream(empleados).
                collect(Collectors.groupingBy(Empleado::obtenerDepartamento, TreeMap::new, Collectors.counting()));

        // Iteración sobre la colección
        contadorDepartamentos.entrySet().stream().forEach(entrada -> {
            System.out.println(entrada.getKey() + " tiene " + entrada.getValue() + " empleados");
        });
        
        // Sumar los sueldos de todos los empleados
        double suma_sueldos = Arrays.stream(empleados).mapToDouble(Empleado::obtenerSueldo).sum();

        // Obtener la media de sueldos
        double media_sueldos = Arrays.stream(empleados).mapToDouble(Empleado::obtenerSueldo).average().getAsDouble();


    }
}
