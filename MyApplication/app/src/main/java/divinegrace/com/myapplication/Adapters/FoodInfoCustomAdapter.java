package divinegrace.com.myapplication.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import divinegrace.com.myapplication.Model.DataInFoodInfoCustomAdapter;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.Model.Nutrient;

/**
 * Created by DGBendicion on 7/16/15.
 */
public class FoodInfoCustomAdapter extends BaseAdapter {
    private Context mContext;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_SEPARATOR = 0;
    private List<DataInFoodInfoCustomAdapter> mData = new ArrayList<DataInFoodInfoCustomAdapter>();
    private LayoutInflater mLayoutInflater;
    private int mSectionTitleLayout;
    private int mSectionTitleTextView;
    private int mDetailLayout;
    private int mDetailNutrientDietaryFibre;
    private int mDetailTrans;
    private int mDetailsSaturated;
    private int mDetailTotalCarbs;
    private int mDetailSodium;
    private int mDetailPotassium;
    private int mDetailPolyunsaturated;
    private int mDetailCalories;
    private int mDetailSugar;
    private int mDetailTotalFats;
    private int mDetailMonounsaturated;
    private int mDetailCholesterol;
    private int mDetailProtien;


    public FoodInfoCustomAdapter(Context context, List<FoodInDB> foodInDBList,
                                 int sectionTitleLayout,
                                 int sectionTitleTextView, int detailLayout,
                                 int dietaryFibreTv,
                                 int transTv,
                                 int saturatedTv,
                                 int totalCarbsTv,
                                 int sodiumTv,
                                 int potassiumTv,
                                 int polyunsaturatedTv,
                                 int caloriesTv,
                                 int sugarTv,
                                 int totalFatsTv,
                                 int monosaturatedTv,
                                 int cholesterolTv,
                                 int protienTv) {
        this.mContext = context;
        this.mSectionTitleLayout = sectionTitleLayout;
        this.mSectionTitleTextView = sectionTitleTextView;
        this.mDetailLayout = detailLayout;
        this.mDetailNutrientDietaryFibre = dietaryFibreTv;
        this.mDetailTrans = transTv;
        this.mDetailsSaturated = saturatedTv;
        this.mDetailTotalCarbs = totalCarbsTv;
        this.mDetailSodium = sodiumTv;
        this.mDetailPotassium = potassiumTv;
        this.mDetailPolyunsaturated = polyunsaturatedTv;
        this.mDetailCalories = caloriesTv;
        this.mDetailSugar = sugarTv;
        this.mDetailTotalFats = totalFatsTv;
        this.mDetailMonounsaturated = monosaturatedTv;
        this.mDetailCholesterol = cholesterolTv;
        this.mDetailProtien = protienTv;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mData = new ArrayList<DataInFoodInfoCustomAdapter>();

        String foodName = "";

        for (FoodInDB foodInDB: foodInDBList) {
            if (!foodInDB.getName().equalsIgnoreCase(foodName)) {
                addSectionHeader(foodInDB.getPortionName());
                foodName = foodInDB.getPortionName();
            }
            addItem(foodInDB);
        }


    }

    public void addItem(final FoodInDB foodInDB) {
        mData.add(new DataInFoodInfoCustomAdapter("", foodInDB,
                DataInFoodInfoCustomAdapter.DataForListType.TYPE_DETAIL));
        notifyDataSetChanged();
    }

    public void addSectionHeader(final String sectionHeaderItem) {
        mData.add(new DataInFoodInfoCustomAdapter(sectionHeaderItem, null,
                DataInFoodInfoCustomAdapter.DataForListType.TYPE_TITLE));
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).mDataForListType.getValue();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        int rowType = getItemViewType(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch(rowType) {
                case TYPE_SEPARATOR: {
                    convertView = mLayoutInflater.inflate(mSectionTitleLayout, null);
                    viewHolder.sectionTitleTextView = (TextView)
                            convertView.findViewById(mSectionTitleTextView);
                    break;
                }

                default:{
                    convertView = mLayoutInflater.inflate(mDetailLayout, null);
                    viewHolder.dietaryFibre = (TextView) convertView.findViewById(mDetailNutrientDietaryFibre);
                    viewHolder.saturated = (TextView) convertView.findViewById(mDetailsSaturated);
                    viewHolder.trans = (TextView) convertView.findViewById(mDetailTrans);
                    viewHolder.totalCarbs = (TextView) convertView.findViewById(mDetailTotalCarbs);
                    viewHolder.sodium = (TextView) convertView.findViewById(mDetailSodium);
                    viewHolder.potassium = (TextView) convertView.findViewById(mDetailPotassium);
                    viewHolder.polyunsaturated = (TextView) convertView.findViewById(mDetailPolyunsaturated);
                    viewHolder.calories = (TextView) convertView.findViewById(mDetailCalories);
                    viewHolder.sugar = (TextView) convertView.findViewById(mDetailSugar);
                    viewHolder.totalFats = (TextView) convertView.findViewById(mDetailTotalFats);
                    viewHolder.monosaturated = (TextView) convertView.findViewById(mDetailMonounsaturated);
                    viewHolder.cholesterol = (TextView) convertView.findViewById(mDetailCholesterol);
                    viewHolder.protien = (TextView) convertView.findViewById(mDetailProtien);
                    break;
                }
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DataInFoodInfoCustomAdapter dataInFoodInfoCustomAdapter = mData.get(position);
        if (mData.get(position).mFoodInDb == null) {
            viewHolder.sectionTitleTextView.setText(dataInFoodInfoCustomAdapter.mTitle);
        } else {
            FoodInDB foodInDB = dataInFoodInfoCustomAdapter.mFoodInDb;
            if (foodInDB.getDietaryFiber() != null) {
                viewHolder.dietaryFibre.setText(foodInDB.getDietaryFiber().toString());
            }
            if (foodInDB.getSaturated() != null) {
                viewHolder.saturated.setText(foodInDB.getSaturated().toString());
            }
            if (foodInDB.getTrans() != null) {
                viewHolder.trans.setText(foodInDB.getTrans().toString());
            }
            if (foodInDB.getTotalCarbs() != null) {
                viewHolder.totalCarbs.setText(foodInDB.getTotalFats().toString());
            }
            if (foodInDB.getSodium() != null) {
                viewHolder.sodium.setText(foodInDB.getSodium().toString());
            }
            if (foodInDB.getPotassium() != null) {
                viewHolder.potassium.setText(foodInDB.getPotassium().toString());
            }
            if (foodInDB.getPolyunsaturated() != null) {
                viewHolder.polyunsaturated.setText(foodInDB.getPolyunsaturated().toString());
            }
            if (foodInDB.getCalories() != null) {
                viewHolder.calories.setText(foodInDB.getCalories().toString());
            }
            if (foodInDB.getSugar() != null) {
                viewHolder.sugar.setText(foodInDB.getSugar().toString());
            }
            if (foodInDB.getTotalFats() != null) {
                viewHolder.totalFats.setText(foodInDB.getTotalFats().toString());
            }
            if (foodInDB.getMonosaturated() != null) {
                viewHolder.monosaturated.setText(foodInDB.getMonosaturated().toString());
            }
            if (foodInDB.getCholesterol() != null) {
                viewHolder.cholesterol.setText(foodInDB.getCholesterol().toString());
            }
            if (foodInDB.getProtien() != null) {
                viewHolder.protien.setText(foodInDB.getProtien().toString());
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView sectionTitleTextView;
        public TextView dietaryFibre;
        public TextView saturated;
        public TextView trans;
        public TextView totalCarbs;
        public TextView sodium;
        public TextView potassium;
        public TextView polyunsaturated;
        public TextView calories;
        public TextView sugar;
        public TextView totalFats;
        public TextView monosaturated;
        public TextView cholesterol;
        public TextView protien;
    }


}
