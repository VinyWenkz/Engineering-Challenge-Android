package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.R;
import divinegrace.com.myapplication.Utils.Keys;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class SearchFragment extends Fragment {
    private Context mContext;
    private InFoodController mInFoodController;
    private NetworkCallback mNetworkCallback;

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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInFoodController = InFoodController.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.actv_search_food)
    public void searchFoodInRemote() {
        mInFoodController.searchFoodInformation(actvSearchFood.getText().toString(),
                mNetworkCallback);
    }

    private void setEnterKeyListenerOnActv() {
       
    }
}
