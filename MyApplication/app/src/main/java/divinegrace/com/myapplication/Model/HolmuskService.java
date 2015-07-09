package divinegrace.com.myapplication.Model;

import divinegrace.com.myapplication.Utils.Constants;
import retrofit.RestAdapter;

/**
 * Created by DGBendicion on 7/9/15.
 */
public class HolmuskService {
    private IHolmuskService mService;


    public HolmuskService() { }

    public IHolmuskService getHolmuskService() {

        if (mService != null) {
            return mService;
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.HOLMUSK_URL)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        mService = restAdapter.create(IHolmuskService.class);

        return mService;
    }
}
