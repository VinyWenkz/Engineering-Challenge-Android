package divinegrace.com.myapplication.CallBacks;

import java.util.List;

import divinegrace.com.myapplication.Model.Food;
import retrofit.RetrofitError;

/**
 * Created by DGBendicion on 7/9/15.
 */
public interface NetworkCallback {
    void foodSearchSuccess(List<Food> portionList);
    void foodSearchError(RetrofitError error);
}
