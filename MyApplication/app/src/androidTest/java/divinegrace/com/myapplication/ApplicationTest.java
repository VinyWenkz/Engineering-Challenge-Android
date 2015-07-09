package divinegrace.com.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import divinegrace.com.myapplication.Model.IHolmuskService;
import divinegrace.com.myapplication.Model.SearchFoodService;
import retrofit.Callback;
import retrofit.http.Query;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        IHolmuskService iHolmuskService = new IHolmuskService() {
            @Override
            public void searchFood(@Query("q") String foodName, Callback<String> callback) {

            }
        };

        SearchFoodService searchFoodService = new SearchFoodService(iHolmuskService);

        searchFoodService.searchForFoodInformationFromHolmusk("apple");
    }
}