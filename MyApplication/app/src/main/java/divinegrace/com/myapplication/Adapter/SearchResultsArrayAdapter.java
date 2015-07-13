package divinegrace.com.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import divinegrace.com.myapplication.Model.Food;
import divinegrace.com.myapplication.R;

/**
 * Created by DGBendicion on 7/13/15.
 */
public class SearchResultsArrayAdapter extends ArrayAdapter<Food> {
    private Context mContext;
    private ArrayList<Food> mFoodList;
    private static LayoutInflater mInflater = null;

    public SearchResultsArrayAdapter(Context context, int resource, int textViewResourceId, List<Food> objects) {
        super(context, resource, textViewResourceId, objects);

        this.mContext = context;
        this.mFoodList = (ArrayList<Food>) objects;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public Food getItem(int position) {
        return mFoodList.get(position);
    }

    public void repopulateFoodList(List<Food> newFoodList) {
        mFoodList = null;
        mFoodList = (ArrayList<Food>) newFoodList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View convertViewCopy = convertView;

        if (convertView == null) {
            convertViewCopy = mInflater.inflate(R.layout.food_listview_cell_item_layout, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertViewCopy.findViewById(R.id.tv_search_results);
            convertViewCopy.setTag(holder);
        } else {
            holder = (ViewHolder) convertViewCopy.getTag();
        }
        holder.textView.setText(mFoodList.get(position).toString());

        return convertViewCopy;
    }

    class ViewHolder {
        public TextView textView;
    }
}
