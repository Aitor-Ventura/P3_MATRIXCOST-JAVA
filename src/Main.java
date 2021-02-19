import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        // Muestra el mensaje de ayuda
        if (AppUtils.contains(args, "-h", "--help")) AppUtils.displayHelp();
        /*
         * Variables de control
         * tbF, mmF, checkF booleanas que controlan el flujo de hacer tabulation
         *      memoization, o check (ambas)
         */
        boolean tbF, mmF, checkF;
        tbF = false; mmF = false; checkF = false;
        
        // Control de metodología
        if      (Arrays.stream(args).anyMatch("-check"::equals )){ tbF = false; mmF = false; checkF = true ; }
        else if (AppUtils.contains(args, "-sm", "--memoization")){ tbF = false; mmF = true ; checkF = false; }
        else if (AppUtils.contains(args, "-st", "--tabulation" )){ tbF = true ; mmF = false; checkF = false; }

        // Declaración de variables para indicar si es un directorio o un fichero
        // isDirectory self explanatory
        // input variable que se procesará
        boolean isDirectory = false; String input = "";

        // Se indica que es un directorio y se asigna input al directorio pasado por línea de órdenes
        if (AppUtils.contains(args, "-d", "--directory")){ isDirectory = true; input = args[AppUtils.indexOf(args, "-d", "--directory") + 1]; }

        // Se indica que es un fichero y se asigna input al fichero pasado por línea de órdenes
        else if (AppUtils.contains(args, "-f", "--file")){                     input = args[AppUtils.indexOf(args, "-f", "--file")      + 1]; }

        // Procesamiento de directorios
        if (isDirectory){
            // Se ordena alfanuméricamente
            File file = new File(input);
            String[] cont = file.list();
            Comparator<String> numericalOrder = AlphanumericSortComparator.NUMERICAL_ORDER;
            Arrays.sort(cont, numericalOrder);

            for (String fich : cont){
                int[][] matrix = AppUtils.fromDataToMatrix(input + "/" + fich);
                // Check - Se comprueba que el resultado por Tabulation y Memoization es el mismo
                if (checkF){
                    int[][] tabulation  = AppUtils.deepCopy(matrix);
                    int[][] memoization = AppUtils.deepCopy(matrix);

                    Tabulation.countPaths_Tabulation(tabulation, GlobalVariables.getCost());
                    int totalPathsTab = GlobalVariables.getPaths();

                    Memoization.countPaths_Memoization(memoization, GlobalVariables.getCost());
                    int totalPathsMem = GlobalVariables.getPaths();

                    if (totalPathsTab == totalPathsMem){ System.out.print("Check: True - Number of paths is the same through Tabulation and Memoization | " + input + "/" + fich + "\t"); AppUtils.printOptions(args); }
                    else System.out.print("[!!! ERROR !!!] Check: False - Number of paths is NOT the same through Tabulation and Memoization");
                }

                // Tabulation - Se resuelve el problema por Tabulation
                else if (tbF){
                    int[][] tabulation = AppUtils.deepCopy(matrix);
                    Tabulation.countPaths_Tabulation(tabulation, GlobalVariables.getCost());
                    System.out.print("Tabulation:   " + input + "/" + fich + "\t");
                    AppUtils.printOptions(args);
                }

                // Memoization - Se resuelve el problema por Memoization
                else if (mmF){
                    int[][] memoization = AppUtils.deepCopy(matrix);
                    Memoization.countPaths_Memoization(memoization, GlobalVariables.getCost());
                    System.out.print("Memoization:  " + input + "/" + fich + "\t");
                    AppUtils.printOptions(args);
                }
            }
        }

        // Procesamiento de ficheros
        else {
            int[][] matrix = AppUtils.fromDataToMatrix(input);
            // Check - Se comprueba que el resultado por Tabulation y Memoization es el mismo
            if (checkF){
                int[][] tabulation  = AppUtils.deepCopy(matrix);
                int[][] memoization = AppUtils.deepCopy(matrix);

                Tabulation.countPaths_Tabulation(tabulation, GlobalVariables.getCost());
                int totalPathsTab = GlobalVariables.getPaths();

                Memoization.countPaths_Memoization(memoization, GlobalVariables.getCost());
                int totalPathsMem = GlobalVariables.getPaths();

                if (totalPathsTab == totalPathsMem){ System.out.print("Check: True - Number of paths is the same through Tabulation and Memoization | " + input + "\t"); AppUtils.printOptions(args); }
                else System.out.print("[!!! ERROR !!!] Check: False - Number of paths is NOT the same through Tabulation and Memoization");
            }

            // Tabulation - Se resuelve el problema por Tabulation
            else if (tbF){
                int[][] tabulation = AppUtils.deepCopy(matrix);
                Tabulation.countPaths_Tabulation(tabulation, GlobalVariables.getCost());
                System.out.print("Tabulation:   " + input + "\t");
                AppUtils.printOptions(args);
            }

            // Memoization - Se resuelve el problema por Memoization
            else if (mmF){
                int[][] memoization = AppUtils.deepCopy(matrix);
                Memoization.countPaths_Memoization(memoization, GlobalVariables.getCost());
                System.out.print("Memoization:  " + input + "\t");
                AppUtils.printOptions(args);
            }
        }
    }
}
