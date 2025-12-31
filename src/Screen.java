public class Screen {
    int width, height;
    Point[][] canvas;

    public Screen(int width,int height){
        this.height = height;
        this.width = width;
        this.canvas = new Point[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.canvas[y][x] = new Point();
            }
        }
    }
    public void reset() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++){
                canvas[y][x].reset();
            }
        }
    }


    public void print() {
        for(Point[] s:this.canvas) {
            for(Point p:s){
                System.out.print(p.render());
            }
            System.out.println();
        }
    }
    public void drawLine(Vector2 p1, Vector2 p2, String type) {
        int x1 = (int)p1.x, y1 = (int)p1.y;
        int x2 = (int)p2.x, y2 = (int)p2.y;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (GameContext.isInBounds(x1, y1)) {
                GameContext.addParticle(ParticleFactory.createParticle(type, x1, y1));
            }

            if (x1 == x2 && y1 == y2) break;

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void drawLines(Vector2[] ps, String type) {
        for (int i = 0; i < ps.length - 1; i++) {
            this.drawLine(ps[i], ps[i + 1], type);
        }
        if (ps.length > 2) {
            this.drawLine(ps[ps.length - 1], ps[0], type);
        }
    }
}


