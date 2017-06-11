package co.pushfortask.Repository.Api.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Created by Marios on 10/06/2017.
 */

public class ApiBaseClass {

    private JsonArray mJsonElementResult;
    private JsonElement mJsonElementError;

    public ApiBaseClass(JsonArray jsonElementResult, JsonElement jsonElementError) {
        this.mJsonElementResult = jsonElementResult;
        this.mJsonElementError = jsonElementError;
    }

    public ApiBaseClass() {
    }

    public JsonArray getJsonElementResult() {
        return mJsonElementResult;
    }

    public JsonElement getJsonElementError() {
        return mJsonElementError;
    }

    @Override
    public String toString() {
        return "ApiBaseClass{" +
                "mJsonElementResult=" + mJsonElementResult +
                ", mJsonElementError=" + mJsonElementError +
                '}';
    }
}

