package divinegrace.com.myapplication.View.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import divinegrace.com.myapplication.CallBacks.DBCallback;
import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.Model.Food;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.Model.Portion;
import divinegrace.com.myapplication.R;
import divinegrace.com.myapplication.View.Fragments.SearchResultsFragment;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.RetrofitError;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class MainActivity extends Activity implements NetworkCallback, DBCallback {
    InFoodController inFoodController;
    Realm mRealm;

    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        inFoodController = InFoodController.getInstance();

        mRealm = Realm.getInstance(this);
    }

    @Override
    public void foodSearchSuccess(List<Food> foodList) {
//        for (Food food: foodList) {
//            for (Portion portion: food.portions) {
//                saveOrUpdateFoodItems(food.name,
//                        portion);
//            }
//        }
//
//
//        RealmResults<FoodInDB> query  = mRealm.allObjects(FoodInDB.class);
//        for (FoodInDB foodInDB: query) {
//            Log.d("Realm", foodInDB.getName() + " " + foodInDB.getPortionName()
//                    + " " + foodInDB.getCalories());
//        }
        SearchResultsFragment searchResultsFragment = findSearchResultsFragment();
        if (searchResultsFragment != null) {
            searchResultsFragment.repopulateFoodList(foodList);
        }
        Log.d(LOG_TAG, "foodSearchSuccess");

    }

    private void saveOrUpdateFoodItems(String name, Portion portion) {
        inFoodController.saveOrUpdateFoodItemInDb(mRealm, name, portion, MainActivity.this);
    }

    @Override
    public void foodSearchError(RetrofitError error) {

    }

    @Override
    public void recordSaved(Class zz) {
//        Toast.makeText(this, "Food information saved in DB", Toast.LENGTH_SHORT).show();
    }

    private SearchResultsFragment findSearchResultsFragment() {
        return (SearchResultsFragment)getFragmentManager().findFragmentById(R.id.search_results_fragment);
    }
}
