package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.Model.Food;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.R;
import divinegrace.com.myapplication.Utils.Keys;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class SearchResultsFragment extends Fragment {
    private Context mContext;
    private RealmResults<FoodInDB> mFoodList;
    private Realm mRealm;
    private final String LOG_TAG = "SearchResultsFragment";
    private ArrayAdapter<String> arrayAdapter;
    private List<String> mFoodNames;
    private InFoodController mInFoodController;

    @Bind(R.id.listView)
    ListView mListView;


    public static SearchResultsFragment newInstance(String foodSearch) {
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putString(Keys.SEARCH_FRAGMENT_STRING_BUNDLE_CONTENT, foodSearch);
        searchResultsFragment.setArguments(args);
        return searchResultsFragment;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getInstance(mContext);
        mInFoodController = InFoodController.getInstance();
        String foodSearch = getArguments().getString(Keys.SEARCH_FRAGMENT_STRING_BUNDLE_CONTENT);
        if (!TextUtils.isEmpty(foodSearch)) {
            mFoodList = mInFoodController.getFoodWithNameContaining(mRealm, FoodInDB.class,
                    FoodInDB.nameFieldName, foodSearch);
            Log.d(LOG_TAG, foodSearch);
        }

        mFoodNames = new ArrayList<String>();
        if (mFoodList != null) {
            for (FoodInDB foodInDB: mFoodList) {
                if (!mFoodNames.contains(foodInDB.getName())) {
                    mFoodNames.add(foodInDB.getName());
                }
            }
        }

        arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line,
                android.R.id.text1, mFoodNames);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_result_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(onItemClickListener);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(mContext, mFoodNames.get(position), Toast.LENGTH_SHORT).show();
        }
    };
}
