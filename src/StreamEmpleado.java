import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class StreamEmpleado {
    public static void main(String args[]){
        // Inicialización del array de empleados
        Empleado[] empleados = {
            new Empleado("Juan", "López", 5000, "IT"),
            new Empleado("Almudena", "García", 7600, "IT"),
            new Empleado("Brian", "Hodgson", 1500, "Conexión"),
            new Empleado("Joaquín", "Martínez", 4500.5, "Ventas"),
            new Empleado("Lucas", "Muñoz", 3140, "Marketing"),
            new Empleado("Raúl", "Rodríguez", 7000, "Marketing"),
            new Empleado("Sandra", "Fernández", 6660, "Ventas")
        };

        // Crear un predicado para aquellos que tienen un sueldo superior a 4000€ e inferior a 6000€
        Predicate<Empleado> rango_sueldo = empleado ->
                (empleado.obtenerSueldo() > 4000 && empleado.obtenerSueldo() < 6000);

        // Especificar un comparador para la clase Empleado, por sueldo
        Comparator<Empleado> comparador_sueldo = Comparator.comparing(Empleado::obtenerSueldo);

        // Filtrado + ordenación + mostrado por pantalla
        Arrays.stream(empleados).filter(rango_sueldo).sorted(comparador_sueldo).forEach(System.out::println);
    }
}
