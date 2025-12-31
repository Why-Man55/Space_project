public class FadingParticle extends Particle{
    float brightness;
    long lifetime;

    FadingParticle(float x, float y) {
        super(x, y);
        this.brightness = 100f;
        this.lifetime = 5000;
    }

    public void render() {
        if (isExpired(lifetime)) {
            long elapsed = System.currentTimeMillis() - this.creationTime;
            this.brightness = 100f - (elapsed / (float)this.lifetime) * 100f;
            throw new RuntimeException("Particle lifetime ended");
        }
    }

}

