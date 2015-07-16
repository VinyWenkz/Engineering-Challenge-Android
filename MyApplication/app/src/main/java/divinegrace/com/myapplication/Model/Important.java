package divinegrace.com.myapplication.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class Important {
    @SerializedName("dietary_fibre")
    public Nutrient dietaryFiber;

    public Nutrient trans;

    public Nutrient saturated;

    @SerializedName("total_carbs")
    public Nutrient totalCarbs;

    public Nutrient sodium;

    public Nutrient polyunsaturated;

    public Nutrient calories;

    public Nutrient sugar;

    @SerializedName("total_fats")
    public Nutrient totalFats;

    public Nutrient monosaturated;

    public Nutrient cholesterol;

    public Nutrient potassium;

    public Nutrient protien;
}
