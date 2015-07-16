package divinegrace.com.myapplication.Model;

/**
 * Created by DGBendicion on 7/16/15.
 */
public class DataInFoodInfoCustomAdapter {
    public DataInFoodInfoCustomAdapter(String title, FoodInDB foodInDB, DataForListType dataForListType) {
        mTitle = title;
        mDataForListType = dataForListType;
        mFoodInDb = foodInDB;
    }

    public String mTitle;
    public FoodInDB mFoodInDb;
    public DataForListType mDataForListType;

    public enum DataForListType {
        TYPE_TITLE(0),
        TYPE_DETAIL(1);

        private int _value;

        DataForListType(int Value) {
            this._value = Value;
        }

        public int getValue() {
            return _value;
        }

        public static DataForListType fromInt(int i) {
            for (DataForListType b : DataForListType .values()) {
                if (b.getValue() == i) { return b; }
            }
            return null;
        }
    }
}


