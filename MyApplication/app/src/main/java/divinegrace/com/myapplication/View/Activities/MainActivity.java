package divinegrace.com.myapplication.View.Activities;

import android.app.Activity;
import android.app.Fragment;
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
import divinegrace.com.myapplication.View.Fragments.SearchFragment;
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
    String foodStringSearch = "";

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
        foodStringSearch = foodList.get(0).name;
        for (Food food: foodList) {
            for (Portion portion: food.portions) {
                saveOrUpdateFoodItems(food.name,
                        portion);
            }
        }

        loadFullScreenFragment(SearchResultsFragment.newInstance(foodStringSearch));

        SearchFragment searchFragment = findSearchFragment();
        if (searchFragment != null) {
            searchFragment.dismissAutocompleteTextView();
        }
    }

    private void saveOrUpdateFoodItems(String name, Portion portion) {
        inFoodController.saveOrUpdateFoodItemInDb(mRealm, name, portion, MainActivity.this);
    }

    @Override
    public void foodSearchError(RetrofitError error) {
        SearchFragment searchFragment = findSearchFragment();
        if (searchFragment != null) {
            searchFragment.dismissAutocompleteTextView();
        }
    }

    @Override
    public void recordSaved(Class zz) {
        if (zz == FoodInDB.class) {
        }
    }

    private SearchFragment findSearchFragment() {
        return (SearchFragment) getFragmentManager().findFragmentById(R.id.search_fragment);
    }

    private void loadFullScreenFragment(Fragment fragment) {
       if (fragment != null) {
           FragmentManager fragmentManager = getFragmentManager();
           fragmentManager.beginTransaction()
                   .replace(R.id.fullscreen_overlay_layout_view, fragment).commit();
       }
    }
}
