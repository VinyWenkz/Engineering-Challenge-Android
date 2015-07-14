package divinegrace.com.myapplication.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import divinegrace.com.myapplication.Model.Food;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.R;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/13/15.
 */
public class SearchResultsArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    List<String> mFoodList;
    private static LayoutInflater mInflater = null;
    private int mCellLayout;
    private int mTextViewId;

    public SearchResultsArrayAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);

        this.mContext = context;
        this.mFoodList = objects;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCellLayout = resource;
        this.mTextViewId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public String getItem(int position) {
        return mFoodList.get(position);
    }

    public void repopulateFoodList(List<String> newFoodList) {
        mFoodList = null;
        mFoodList = newFoodList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View convertViewCopy = convertView;

        if (convertView == null) {
            convertViewCopy = mInflater.inflate(mCellLayout, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertViewCopy.findViewById(mTextViewId);
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
