public class ParticleFactory {
    static Particle createParticle(String type, float x, float y) {
        switch (type) {
            case "Blink":
                return new BlinkingParticle(x, y);
            default:
                return new FadingParticle(x, y);
        }
    }
}
