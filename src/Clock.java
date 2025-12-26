public class Clock {
    public static void sleep(long time) {
        long time0 = System.currentTimeMillis();
        while (System.currentTimeMillis() - time0 < time) {
        }
    }
}