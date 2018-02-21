import java.util.function.*;

public class PruebaInterfacesFuncionales {
    public static void main(String args[]){
        IntBinaryOperator operador = (int x, int y) -> {return x+y;};

        System.out.println("Resultado: " + (operador.applyAsInt(3, 10)));

        IntBinaryOperator operadorV1 = new IntBinaryOperator() {
            @Override
            public int applyAsInt(int i, int i1) {
                return i+i1;
            }
        };
    }
}
