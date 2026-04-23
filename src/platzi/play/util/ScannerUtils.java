package platzi.play.util;

import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in); // el static indica que este atributo es propio de esta clase
                                                                // static final indica que no cambiara constante

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje){
        System.out.println(mensaje + ": ");
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDouble(String mensaje){
        System.out.println(mensaje + ": ");
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
}
