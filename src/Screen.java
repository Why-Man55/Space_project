import java.util.Arrays;

public class Screen {
    int width, height;
    Point[][] canvas;

    public Screen(int width,int height){
        this.height = height;
        this.width = width;
        this.canvas = new Point[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.canvas[i][j] = new Point();
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


