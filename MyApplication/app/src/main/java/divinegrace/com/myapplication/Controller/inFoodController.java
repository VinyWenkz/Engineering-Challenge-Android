package divinegrace.com.myapplication.Controller;

import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Model.HolmuskService;
import divinegrace.com.myapplication.Model.IHolmuskService;
import divinegrace.com.myapplication.Model.SearchFoodService;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class InFoodController {
    SearchFoodService mSearchFoodService;
    IHolmuskService mIHolmuskService;

    public InFoodController() {
        mIHolmuskService = new HolmuskService().getHolmuskService();

        mSearchFoodService = new SearchFoodService(mIHolmuskService);
    }

    public void searchFoodInformation(String foodName, NetworkCallback networkCallback) {
        mSearchFoodService.searchForFoodInformationFromHolmusk(foodName, networkCallback);
    }
}
