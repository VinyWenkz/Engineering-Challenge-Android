package divinegrace.com.myapplication.View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import divinegrace.com.myapplication.CallBacks.NetworkCallback;
import divinegrace.com.myapplication.Controller.InFoodController;
import divinegrace.com.myapplication.Model.Portion;
import retrofit.RetrofitError;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class MainActivity extends Activity implements NetworkCallback {
    InFoodController inFoodController = new InFoodController();

    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inFoodController.searchFoodInformation("apple", MainActivity.this);
    }

    @Override
    public void foodSearchSuccess(List<Portion> portionList) {
        for (Portion portion: portionList) {
            Log.d(LOG_TAG, portion.name);
        }
    }

    @Override
    public void foodSearchError(RetrofitError error) {

    }
}
