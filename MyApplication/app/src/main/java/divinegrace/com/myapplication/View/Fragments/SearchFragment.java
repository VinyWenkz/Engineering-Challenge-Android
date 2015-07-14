package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import divinegrace.com.myapplication.Adapter.FoodInDBBaseAdapter;
import divinegrace.com.myapplication.Adapter.SearchResultsArrayAdapter;
import divinegrace.com.myapplication.CallBacks.DBCallback;
import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.R;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class SearchFragment extends Fragment {
    private Context mContext;
    private InFoodController mInFoodController;
    private NetworkCallback mNetworkCallback;
    private SearchResultsArrayAdapter mSearchResultsArrayAdapter;
    private RealmResults<FoodInDB> mFoodInDbList;
    private List<String> mFoodNames;
    private Realm mRealm;
    private DBCallback mDbCallback;

    private final String LOG_TAG = "SearchFragment";

    @Bind(R.id.actv_search_food)
    AutoCompleteTextView actvSearchFood;

    public static SearchFragment newInstance() {
        SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mNetworkCallback = (NetworkCallback) activity;
        mDbCallback = (DBCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInFoodController = InFoodController.getInstance();
        mRealm = Realm.getInstance(mContext);

        mFoodInDbList = mInFoodController.getAllFood(mRealm, mDbCallback);
        mFoodNames = new ArrayList<String>();

        for (FoodInDB foodInDB: mFoodInDbList) {
            if (!mFoodNames.contains(foodInDB.getName())) {
                mFoodNames.add(foodInDB.getName());
                Log.d(LOG_TAG, foodInDB.getName());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        mSearchResultsArrayAdapter = new SearchResultsArrayAdapter(mContext,
                android.R.layout.simple_list_item_1, android.R.id.text1, mFoodNames);
        actvSearchFood.setAdapter(mSearchResultsArrayAdapter);
        actvSearchFood.setThreshold(1);

        return view;
    }

    @OnClick(R.id.actv_search_food)
    public void searchFoodInRemote() {

    }

    private void setEnterKeyListenerOnActv() {
       
    }
}
