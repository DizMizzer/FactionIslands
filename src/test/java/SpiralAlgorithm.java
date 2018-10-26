import net.redstoneore.legacyfactions.Role;

import static java.lang.Math.abs;

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


    public static Integer[] getNext(int x, int y) {

        //Corners
        if (x == y) {
            //Rechtsboven
            if (x > 0)
                return new Integer[]{x - 1, y};
            //linksonder
            if (x < 0)
                return new Integer[]{x + 1, y};
            //Midden
            if (x == 0)
                return new Integer[]{1, 0};
        }

        if (x == -y) {
            if (x < 0)
                return new Integer[]{x, y - 1};
            if (x > 0)
                return new Integer[]{x + 1, y};
        }

        //Rechtdoor
        if (x - y > 0) {
            if (abs(x) > abs(y)) {
                return new Integer[]{x, y + 1};
            } else {
                return new Integer[]{x + 1, y};
            }
        }
        if (x - y < 0) {
            if (abs(x) > abs(y)) {
                return new Integer[]{x, y - 1};
            } else {
                return new Integer[]{x - 1, y};
            }
        }
        return new Integer[]{0, 0};
    }

    public void main() throws Exception {
        Role a = Role.NORMAL;
        Role b = Role.MODERATOR;
        Role c = Role.ADMIN;
        Role d = Role.COLEADER;
        System.out.println(a.compareTo(a));
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
        System.out.println(a.compareTo(d));

        System.out.println(b.compareTo(a));
        System.out.println(b.compareTo(c));
        System.out.println(b.compareTo(d));

        System.out.println(c.compareTo(d));
        System.out.println(c.compareTo(b));

    }


}
