package divinegrace.com.myapplication.Services;

import divinegrace.com.myapplication.CallBacks.DBCallback;
import divinegrace.com.myapplication.Model.FoodInDB;
import divinegrace.com.myapplication.Model.Portion;
import divinegrace.com.myapplication.Utils.Utils;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class DatabaseService {

    public void saveOrUpdateFoodItemInDb(Realm realm, String name, Portion portion, DBCallback dbCallback) {
        realm.beginTransaction();

        FoodInDB foodInDB;

        foodInDB = realm.where(FoodInDB.class).equalTo(FoodInDB.nameFieldName, name)
                .equalTo(FoodInDB.portionNameFieldName, portion.name).findFirst();

        if (foodInDB == null) {
            foodInDB = realm.createObject(FoodInDB.class);
            foodInDB.setName(name);
            foodInDB.setPortionName(portion.name);
            foodInDB.setId(Utils.getNextKey(realm, FoodInDB.idFieldName, FoodInDB.class));
        }

        if (portion.nutrients.important.dietaryFiber != null) {
            foodInDB.setDietaryFiber(portion.nutrients.important.dietaryFiber.value +
                    portion.nutrients.important.dietaryFiber.unit);
        }

        if (portion.nutrients.important.trans != null) {
            foodInDB.setTrans(portion.nutrients.important.trans.value +
                    portion.nutrients.important.trans.unit);
        }

        if (portion.nutrients.important.saturated != null) {
            foodInDB.setSaturated(portion.nutrients.important.saturated.value +
                    portion.nutrients.important.saturated.unit);
        }

        if (portion.nutrients.important.totalCarbs != null) {
            foodInDB.setTotalCarbs(portion.nutrients.important.totalCarbs.value +
                    portion.nutrients.important.totalCarbs.unit);
        }

        if (portion.nutrients.important.sodium != null) {
            foodInDB.setSodium(portion.nutrients.important.sodium.value +
                    portion.nutrients.important.sodium.unit);
        }

        if (portion.nutrients.important.polyunsaturated != null) {
            foodInDB.setPolyunsaturated(portion.nutrients.important.polyunsaturated.value +
                    portion.nutrients.important.polyunsaturated.unit);
        }

        if (portion.nutrients.important.calories != null) {
            foodInDB.setCalories(portion.nutrients.important.calories.value +
                    portion.nutrients.important.calories.unit);
        }

        if (portion.nutrients.important.sugar != null) {
            foodInDB.setSugar(portion.nutrients.important.sugar.value +
                    portion.nutrients.important.sugar.unit);
        }

        if (portion.nutrients.important.totalFats != null) {
            foodInDB.setTotalFats(portion.nutrients.important.totalFats.value +
                    portion.nutrients.important.totalFats.unit);
        }

        if (portion.nutrients.important.monosaturated != null) {
            foodInDB.setMonosaturated(portion.nutrients.important.monosaturated.value +
                    portion.nutrients.important.monosaturated.unit);
        }

        if (portion.nutrients.important.cholesterol != null) {
            foodInDB.setCholesterol(portion.nutrients.important.cholesterol.value +
                    portion.nutrients.important.cholesterol.unit);
        }

        if (portion.nutrients.important.protien != null) {
            foodInDB.setProtien(portion.nutrients.important.protien.value +
                    portion.nutrients.important.protien.unit);
        }

        realm.commitTransaction();

        dbCallback.recordSaved(FoodInDB.class);
    }

    public RealmResults<FoodInDB> getAllFoodInDb(Realm realmInstance, DBCallback dbCallback) {
        RealmResults<FoodInDB> realmResults;
        realmInstance.beginTransaction();
        realmResults = realmInstance.where(FoodInDB.class).findAll();
        realmInstance.commitTransaction();
        return realmResults;
    }
}
