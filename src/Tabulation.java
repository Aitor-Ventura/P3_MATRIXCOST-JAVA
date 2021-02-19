// O(m * n * cost)
public class Tabulation {
    /**
     * @param mat - matriz con todos los elementos
     * @param cost - coste máximo para hallar el camino
     */
    public static void countPaths_Tabulation(int[][] mat, int cost){
        long startTime = System.nanoTime();

        int tab[][][] = new int[mat.length][mat[0].length][cost + 1];
        // El coste no puede ser negativo
        if (mat[0][0] > cost) GlobalVariables.setPaths(0);
        tab[0][0][mat[0][0]] = 1;

        int sum = 0;
        for (int i = 0; i < mat.length; i++){
            sum += mat[i][0];
            if (sum <= cost) tab[i][0][sum] = 1;
        }

        sum = 0;
        for (int i = 0; i < mat[0].length; i++){
            sum += mat[0][i];
            if (sum <= cost) tab[0][i][sum] = 1;
        }

        // Tabulation
        for (int i = 1; i < mat.length; i++){
            for (int j = 1; j < mat[0].length; j++){
                for (int k = 0; k <= cost; k++){
                    if (k - mat[i][j] >= 0) tab[i][j][k] = tab[i - 1][j][k - mat[i][j]] + tab[i][j - 1][k - mat[i][j]];
                }
            }
        }

        GlobalVariables.setPaths(tab[mat.length - 1][mat[0].length - 1][cost]);
        GlobalVariables.setTime(String.format("%.2f", (double) (System.nanoTime() - startTime)/1_000_000_000));
    }
}
