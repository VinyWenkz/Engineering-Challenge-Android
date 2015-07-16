package divinegrace.com.myapplication.Controller;

import android.provider.ContactsContract;

import divinegrace.com.myapplication.CallBacks.DBCallback;
import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.Model.HolmuskService;
import divinegrace.com.myapplication.Model.IHolmuskService;
import divinegrace.com.myapplication.Model.Portion;
import divinegrace.com.myapplication.Model.SearchFoodService;
import divinegrace.com.myapplication.Services.DatabaseService;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class InFoodController {
    private static InFoodController inFoodController;
    SearchFoodService mSearchFoodService;
    DatabaseService mDatabaseService;
    IHolmuskService mIHolmuskService;

    private InFoodController() {
        mIHolmuskService = new HolmuskService().getHolmuskService();

        mSearchFoodService = new SearchFoodService(mIHolmuskService);

        mDatabaseService = new DatabaseService();
    }

    public static InFoodController getInstance() {
        if (inFoodController == null) {
            inFoodController = new InFoodController();
        }
        return inFoodController;
    }

    public void searchFoodInformation(String foodName, NetworkCallback networkCallback) {
        mSearchFoodService.searchForFoodInformationFromHolmusk(foodName, networkCallback);
    }

    public void saveOrUpdateFoodItemInDb(Realm realm, String name, Portion portion, DBCallback dbCallback) {
        mDatabaseService.saveOrUpdateFoodItemInDb(realm, name, portion, dbCallback);
    }

    public RealmResults<FoodInDB> getAllFood(Realm realm, DBCallback dbCallback) {
        return mDatabaseService.getAllFoodInDb(realm, dbCallback);
    }

    public RealmResults<FoodInDB> getFoodWithNameContaining(Realm realm, Class zz,
                                                            String fieldName, String stringSearch) {
        return mDatabaseService.getRecordsContaining(realm, zz, fieldName, stringSearch);
    }
}
