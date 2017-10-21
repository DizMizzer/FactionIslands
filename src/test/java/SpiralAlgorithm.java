import org.junit.Test;

/**
 * Created by DizMizzer.
 * Users don't have permission to release
 * the code unless stated by the Developer.
 * You are allowed to copy the source code
 * and edit it in any way, but not distribute
 * it. If you want to distribute addons,
 * please use the API. If you can't access
 * a certain thing in the API, please contact
 * the developer in contact.txt.
 */
public class SpiralAlgorithm {

    public static void Spiraal(int X, int Y) {
        int x = 0, y = 0, dx = 0, dy = -1;
        int t = Math.max(X, Y);
        int maxI = t * t;

        for (int i = 0; i < maxI; i++) {
            if ((-X / 2 <= x) && (x <= X / 2) && (-Y / 2 <= y) && (y <= Y / 2)) {
                System.out.println(x + "," + y);
                //DO STUFF
            }

            if ((x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1 - y))) {
                t = dx;
                dx = -dy;
                dy = t;
            }
            x += dx;
            y += dy;
        }
    }

    public void print(Object... a) {
        String c = "";
        for (Object ab : a) {
            c = c + ab + " ";
        }
        System.out.println(c);

    }

    @Test
    public void main() {
        int[] begin = {1, 0};
        int x = -6 * 256;
        int z = 0 * 256;
        for (int i = -5; i < 5; i++)
            for (int xx = 0; xx < 256; xx = xx + 16) {
                for (int zz = 0; zz < 256; zz = zz + 16) {
                    print(xx + x + 1, zz + z + 1);
                    x = i * 256;

                }
            }

    }

    int[] Spiral(int max) {
        int x, y, dx, dy;
        x = y = dx = 0;
        dy = -1;
        int t = max;
        int maxI = t * t;
        for (int i = 0; i < maxI; i++) {
            if ((x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1 - y))) {
                t = dx;
                dx = -dy;
                dy = t;
            }
            x += dx;
            y += dy;
            if (i == maxI - 1) {
                return new int[]{x, y};
            }
        }
        return new int[]{0, 0};
    }
}
