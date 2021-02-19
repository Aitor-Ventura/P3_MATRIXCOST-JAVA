import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class AppUtils {
    // Muestra el mensaje de ayuda y finaliza la ejecución
    public static void displayHelp(){
        System.out.println("usage: main.py [-h] [-d [DIRECTORY] | -f [FILE]] [-p] [-t] [-sm | -st | -check]\n\n" +
        "optional arguments:\n" +
        "    -h, --help                                  show this help message and exit\n" +
        "    -d [DIRECTORY], --directory [DIRECTORY]     process many files in a directory\n" +
        "    -f [FILE], --file [FILE]                    process a single file\n" +
        "    -dd, --dimension                             display square matrix dimension\n" +
        "    -c, --cost                                  display cost\n" +
        "    -p, --paths                                 display total paths that can be done with given cost\n" +
        "    -t, --time                                  display execution time\n" +
        "    -sm, --memoization                          count number of paths in a matrix with given cost to reach\n" +
        "                                                    destination cell through Memoization\n" +
        "    -st, --tabulation                           count number of paths in a matrix with given cost to reach\n" +
        "                                                    destination cell through Tabulation\n" +
        "    -check                                      check that the number of paths in a matrix with given cost\n" +
        "                                                    is the same through Tabulation and Memoization");
        System.exit(0);
    }

    /**
     * @param argv - Array de Strings que contiene los argumentos de la línea de órdenes
     * @param s1 - Se comprueba si s1 se encuentra en argv
     * @param s2 - Se comprueba si s2 se encuentra en argv
     * @return verdadero si se encuentra alguna, falso si no
     */
    public static boolean contains(String[] argv, String s1, String s2){
        boolean f = false;
        for (String a : argv){
            if (a.equals(s1) || a.equals(s2)){ f = true; break; }
        }
        return f;
    }

    /**
     * @param argv - Array de Strings que contiene los argumentos de la línea de órdenes
     * @param s1 - Se comprueba si en el índice i de argv se encuentra s1
     * @param s2 - Se comprueba si en el índice i de argv se encuentra s2
     * @return el índice de donde se encuentre cualquiera de las Strings
     */
    public static int indexOf(String[] argv, String s1, String s2){
        for (int i = 0; i < argv.length; i++){
            if (s1.equals(argv[i]) || s2.equals(argv[i])) return i;
        }
        return 0;
    }

    public static int[][] fromDataToMatrix(String input) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(input)));
        int[][] mat = new int[0][0];
        while (sc.hasNextInt()) {
            GlobalVariables.setDimension1(sc.nextInt());
            GlobalVariables.setDimension2(sc.nextInt());
            GlobalVariables.setCost(sc.nextInt());

            mat = new int[GlobalVariables.getDimension1()][GlobalVariables.getDimension2()];

            for (int i = 0; i < GlobalVariables.getDimension1(); i++){
                for (int j = 0; j < GlobalVariables.getDimension2(); j++){
                    mat[i][j] = sc.nextInt();
                }
            }
        }
        return mat;
    }

    /**
     * @param args - Array de Strings con los argumentos
     * Printa por pantalla el resultado de las opciones pasadas por línea de órdenes
     */
    public static void printOptions(String[] args){
        if (AppUtils.contains(args, "-dd", "--dimension")) System.out.print(Integer.toString(GlobalVariables.getDimension1()) + "x" +
                                                                            Integer.toString(GlobalVariables.getDimension2()) + "\t");
        if (AppUtils.contains(args, "-c", "--cost"      )) System.out.print(Integer.toString(GlobalVariables.getCost())       + " cost \t");
        if (AppUtils.contains(args, "-p", "--paths"     )) System.out.print(Integer.toString(GlobalVariables.getPaths())      + " paths \t");
        if (AppUtils.contains(args, "-t", "--time"      )) System.out.print(GlobalVariables.getTime()                         + "s\t");
        System.out.println();
    }

    /**
     * @param matrix - matriz a copiar
     * @return nueva matriz con copia profunda, no copia a referencias
     */
    public static int[][] deepCopy(int[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
