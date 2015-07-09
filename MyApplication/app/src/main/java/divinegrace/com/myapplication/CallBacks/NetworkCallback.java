package divinegrace.com.myapplication.CallBacks;

import java.util.List;

import divinegrace.com.myapplication.Model.Portion;
import retrofit.RetrofitError;

/**
 * Created by DGBendicion on 7/9/15.
 */
public interface NetworkCallback {
    public void foodSearchSuccess(List<Portion> portionList);
    public void foodSearchError(RetrofitError error);
}
