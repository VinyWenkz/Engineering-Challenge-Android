package divinegrace.com.myapplication.Model;

import java.util.List;

import divinegrace.com.myapplication.Utils.EndPoints;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by DGBendicion on 7/9/15.
 */
public interface IHolmuskService {

    @GET(EndPoints.SEARCH_FOOD)
    void searchFood(@Query("q") String foodName,
                    Callback<List<Portion>> callback);
}
