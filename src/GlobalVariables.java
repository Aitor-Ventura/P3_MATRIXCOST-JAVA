public class GlobalVariables {
    private static int dimension1;
    private static int dimension2;
    private static int cost;
    private static int paths;
    private static String time;

    public static int getDimension1(){ return GlobalVariables.dimension1; }
    public static int getDimension2(){ return GlobalVariables.dimension2; }
    public static int getCost(){ return GlobalVariables.cost; }
    public static int getPaths(){ return GlobalVariables.paths; }
    public static String getTime(){ return GlobalVariables.time; }

    public static void setDimension1(int dimension1){ GlobalVariables.dimension1 = dimension1; }
    public static void setDimension2(int dimension2){ GlobalVariables.dimension2 = dimension2; }
    public static void setCost(int cost){ GlobalVariables.cost = cost; }
    public static void setPaths(int paths){ GlobalVariables.paths = paths; }
    public static void setTime(String time){ GlobalVariables.time = time; }
}
