public class PrintSystemVar {
    public static final String APP_HOME = "APP_HOME";

    public static void main(String[] args) {
        System.out.println(PrintSystemVar.APP_HOME
            + " + " + getAppHome());
    }

    public static String getAppHome() {
        return System.getProperty(PrintSystemVar.APP_HOME, ".");
    }
}