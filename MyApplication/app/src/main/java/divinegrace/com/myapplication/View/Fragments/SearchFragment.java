package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import divinegrace.com.myapplication.CallBacks.DBCallback;
import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.CallBacks.UICallbacks;
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
    private Realm mRealm;
    private DBCallback mDbCallback;
    private List<String> mFoodNames;
    private ArrayAdapter<String> mArrayAdapter;
    private UICallbacks mUiCallback;


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
        mUiCallback = (UICallbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInFoodController = InFoodController.getInstance();
        mRealm = Realm.getInstance(mContext);
        mFoodNames = new ArrayList<String>();
        mArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line,
                android.R.id.text1, mFoodNames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        actvSearchFood.setThreshold(1);
        actvSearchFood.addTextChangedListener(textWatcher);
        actvSearchFood.setAdapter(mArrayAdapter);
        actvSearchFood.setOnEditorActionListener(onEditorActionListener);
        actvSearchFood.setOnItemClickListener(onItemClickListener);

        return view;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            RealmResults<FoodInDB> foodInDbList = mInFoodController.getAllFood(mRealm, mDbCallback);
            for (FoodInDB foodInDB: foodInDbList) {
                if(!mFoodNames.contains(foodInDB.getName())) {
                    mFoodNames.add(foodInDB.getName());
                }
            }
            if (mFoodNames.size() > 0) {
                mArrayAdapter.clear();
                mArrayAdapter.addAll(mFoodNames);
                mArrayAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!actvSearchFood.isPerformingCompletion()) {
                Log.d(LOG_TAG, "No matches found");
            }
        }
    };

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mInFoodController.searchFoodInformation(actvSearchFood.getText().toString(),
                        mNetworkCallback);
                return true;
            }
            return false;
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mUiCallback.selectedFood(mFoodNames.get(position));
            dismissAutocompleteTextView();
        }
    };

    public void dismissAutocompleteTextView() {
        actvSearchFood.dismissDropDown();
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(actvSearchFood.getWindowToken(), 0);
    }




}
