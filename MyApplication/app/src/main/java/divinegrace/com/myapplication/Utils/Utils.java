package divinegrace.com.myapplication.Utils;

import io.realm.Realm;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class Utils {
    public static int getNextKey (Realm realm, String fieldName, Class zz) {
        return (int) (realm.where(zz).maximumInt(fieldName) + 1);
    }
}
