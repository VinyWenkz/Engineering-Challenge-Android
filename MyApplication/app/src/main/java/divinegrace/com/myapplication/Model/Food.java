package divinegrace.com.myapplication.Model;

import java.util.List;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class Food {
    public String name;
    public List<Portion> portions;

    @Override
    public String toString() {
        return name;
    }
}
