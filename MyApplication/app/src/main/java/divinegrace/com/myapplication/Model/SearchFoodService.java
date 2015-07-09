package divinegrace.com.myapplication.Model;

import java.util.List;

import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class SearchFoodService {

    IHolmuskService mIHolmuskService;

    private final String LOG_TAG = "SearchFoodService";

    public SearchFoodService(IHolmuskService iHolmuskService) {
        this.mIHolmuskService = iHolmuskService;
    }

    public void searchForFoodInformationFromHolmusk(String foodName, final NetworkCallback networkCallback) {
        Callback<List<Portion>> callback = new Callback<List<Portion>>() {
            @Override
            public void success(List<Portion> s, Response response) {
                networkCallback.foodSearchSuccess(s);
            }

            @Override
            public void failure(RetrofitError error) {
                networkCallback.foodSearchError(error);
            }
        };

        mIHolmuskService.searchFood(foodName, callback);
    }
}
