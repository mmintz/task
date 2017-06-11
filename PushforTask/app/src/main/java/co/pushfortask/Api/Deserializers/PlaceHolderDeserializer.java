package co.pushfortask.Api.Deserializers;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import co.pushfortask.Repository.Api.entities.ApiBaseClass;

/**
 * Created by Marios on 10/06/2017.
 */

public class PlaceHolderDeserializer implements JsonDeserializer {

    private static String TAG = PlaceHolderDeserializer.class.getName();

    @Override
    public ApiBaseClass deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        ApiBaseClass baseClass;
        try{
            final JsonArray elementResult = jsonElement.getAsJsonArray();
            final JsonElement elementError = null;
            baseClass = new ApiBaseClass(elementResult,elementError);
        }
        catch(Exception exc)
        {
            Log.e(TAG,exc.getMessage());
            exc.printStackTrace();
            throw exc;
        }

        return baseClass;
    }
}
