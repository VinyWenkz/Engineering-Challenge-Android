package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import divinegrace.com.myapplication.Adapters.FoodInfoCustomAdapter;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.R;
import divinegrace.com.myapplication.Utils.Keys;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/16/15.
 */
public class FoodInfoFragment extends Fragment{
    private Context mContext;
    private RealmResults<FoodInDB> foodInDbList;
    private Realm mRealm;
    private InFoodController mInFoodController;
    private ListView mFoodInfoListView;
    private FoodInfoCustomAdapter mFoodInfoCustomAdapter;

    @Bind(R.id.listView2)
    ListView mLvFoodInfo;

    public static FoodInfoFragment newInstance(String searchFoodString) {
        Bundle args = new Bundle();
        args.putString(Keys.FOOD_INFO_FRAGMENT_STRING_BUNDLE_CONTENT, searchFoodString);

        FoodInfoFragment foodInfoFragment = new FoodInfoFragment();
        foodInfoFragment.setArguments(args);
        return foodInfoFragment;
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
        String foodSearch = getArguments().getString(Keys.FOOD_INFO_FRAGMENT_STRING_BUNDLE_CONTENT);
        List<FoodInDB> foodInDBList = mInFoodController.getFoodWithExactName(mRealm,
                FoodInDB.class, FoodInDB.nameFieldName, foodSearch);

        mFoodInfoCustomAdapter = new FoodInfoCustomAdapter(mContext, foodInDBList, R.layout.food_info_section_title_cell_layout,
                R.id.tv_food_info_section_header, R.layout.food_info_detail_layout,
                R.id.food_detail_dietary_fiber_value,
                R.id.food_detail_trans_value,
                R.id.food_detail_saturated_value,
                R.id.food_detail_total_carbs_value,
                R.id.food_detail_sodium_value,
                R.id.food_detail_potassium_value,
                R.id.food_detail_polyunsaturated_value,
                R.id.food_detail_calories_value,
                R.id.food_detail_sugar_value,
                R.id.food_detail_total_fats_value,
                R.id.food_detail_monounsaturated_value,
                R.id.food_detail_cholesterol_value,
                R.id.food_detail_protein_value);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_info_fragment_layout, null);
        ButterKnife.bind(this, view);
        mLvFoodInfo.setAdapter(mFoodInfoCustomAdapter);

        return view;
    }
}
