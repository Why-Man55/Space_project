public class BlinkingParticle extends Particle {
    float brightness;
    long lifetime;

        BlinkingParticle(float x,float y) {
            super(x, y);
            this.brightness = 100f;
            this.lifetime = 4000;
        }

        public void render() {
            if (isExpired(lifetime)) {
                throw new RuntimeException("Particle lifetime ended");
            }

            long elapsed = System.currentTimeMillis() - this.creationTime;
            float progress = (elapsed / (float)this.lifetime) * 2 - 1;
            this.brightness = 100f * (1 - Math.abs(progress));

        }
}

