// Recurrencia
// path(m, n, cost) = path(m    , n - 1, cost - M[m][n])     if m == 0
//                  = path(m - 1, n    , cost - M[m][n])     if n == 0
//                  = path(m - 1, n    , cost - M[m][n])
//                  + path(m    , n - 1, cost - M[m][n])     otherwise
// 
// path(0, 0, cost) = 1                                      if M[m][n] == cost
//                  = 0                                      otherwise
// 
// O(m * n * cost)

import java.util.HashMap;
import java.util.Map;

public class Memoization {
    
    /**
     * @param mat - matriz con todos los elementos
     * @param cost - coste máximo para hallar el camino
     */
    public static void countPaths_Memoization(int[][] mat, int cost){
        long startTime = System.nanoTime();
        // Declaración de diccionario
        Map<String, Integer> lookup = new HashMap<>();
        GlobalVariables.setPaths(_countPaths(mat, mat.length - 1, mat[0].length - 1, cost, lookup));
        GlobalVariables.setTime(String.format("%.2f", (double) (System.nanoTime() - startTime)/1_000_000_000));
    }

    /**
     * @param mat - matriz con todos los elementos
     * @param m - número de filas
     * @param n - número de columnas
     * @param cost - coste restante para hallar el camino
     * @param lookup - diccionario
     * @return numero de caminos para alcanzar celda (m, n)
     */
    private static int _countPaths(int[][] mat, int m, int n, int cost, Map<String, Integer> lookup){
        // El coste no puede ser negativo
        if (cost < 0) return 0;
        // Si estamos en la primera celda (0, 0), si el coste de la celda coincide con el coste, hay
        // 1 camino posible
        if (m == 0 && n == 0){
            if (mat[0][0] - cost == 0) return 1;
            else return 0;
        }

        String key = m + "|" + n + "|" + cost;
        // Si no se ha calculado el sub-problema con anterioridad
        if (! lookup.containsKey(key) ){
            // Si estamos en la primera fila, solo podremos ir hacia la izquierda
            if      (m == 0) lookup.put(key, _countPaths(mat, 0    , n - 1, cost - mat[m][n], lookup));
            // Si estamos en la primera columna, solo podremos ir hacia arriba
            else if (n == 0) lookup.put(key, _countPaths(mat, m - 1, 0    , cost - mat[m][n], lookup));
            // Calcular número de caminos posibles yendo en ambas direcciones
            else             lookup.put(key, _countPaths(mat, m - 1, n    , cost - mat[m][n], lookup) + 
                                             _countPaths(mat, m    , n - 1, cost - mat[m][n], lookup));
        }
        // Retornar número de caminos posibles para llegar a la celda (m, n)
        return lookup.get(key);
    }
}