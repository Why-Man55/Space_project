class Poligon extends GameObject {
    Vector2[] points;
    String type;

    Poligon(Vector2[] points, String type) {
        this.points = points;
        this.type = type;
    }

    Poligon(Vector2[] points) {
        this(points, "DEFAULT");
    }

    Poligon() {}

    public void render() {
        GameContext.getScreen().drawLines(this.points, this.type);
    }
}