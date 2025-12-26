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

}
