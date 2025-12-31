abstract public class Particle extends GameObject {
    float x, y;
    long creationTime;

        Particle(float x,float y) {
            this.x = x;
            this.y = y;
            creationTime = System.currentTimeMillis();
        }
    protected boolean isExpired(long life){
        return System.currentTimeMillis() - creationTime > life;
    }
}
