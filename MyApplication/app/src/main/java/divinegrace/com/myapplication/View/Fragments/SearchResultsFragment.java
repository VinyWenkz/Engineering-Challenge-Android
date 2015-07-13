package divinegrace.com.myapplication.View.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import divinegrace.com.myapplication.Adapter.SearchResultsArrayAdapter;
import divinegrace.com.myapplication.Model.Food;
import divinegrace.com.myapplication.R;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class SearchResultsFragment extends ListFragment {
    private Context mContext;
    private SearchResultsArrayAdapter mSearchResultsArrayAdapter;
    private List<Food> mFoodList;
    private ListView mListView;

    public static SearchResultsFragment newInstance() {
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
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
        mFoodList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_result_fragment_layout, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    public void repopulateFoodList(List<Food> newFoodList) {
        mSearchResultsArrayAdapter.repopulateFoodList(newFoodList);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchResultsArrayAdapter = new SearchResultsArrayAdapter(mContext,
                android.R.layout.simple_list_item_1, android.R.id.text1, mFoodList);
        mListView.setAdapter(mSearchResultsArrayAdapter);
    }
}
