public class FadingParticle extends Particle{
    float brightness;
    long lifetime;

    FadingParticle(int x, int y) {
        super(x, y);
        this.brightness = 100f;
        this.lifetime = 5000;
    }

    @Override
    public void checkActive() {
        if
    }

    public void render() {
        if(isExpired(lifetime)){

        }


    }
}
