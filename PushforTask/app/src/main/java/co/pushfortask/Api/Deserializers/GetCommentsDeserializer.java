package co.pushfortask.Api.Deserializers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.pushfortask.Repository.Api.entities.ApiBaseClass;
import co.pushfortask.Repository.Api.entities.ApiComment;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;

/**
 * Created by Marios on 10/06/2017.
 */

public class GetCommentsDeserializer extends PlaceHolderDeserializer {

    private static final String TAG = GetPostsDeserializer.class.getName();

    @Override
    public ApiListCommentsForPost deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<ApiComment> comments = null;
        try {
            ApiBaseClass baseClass = super.deserialize(jsonElement, type, jsonDeserializationContext);
            Type typeArrayComments = new TypeToken<List<ApiComment>>() {
            }.getType();
            comments = new Gson().fromJson(baseClass.getJsonElementResult(), typeArrayComments);
        } catch (Exception exc) {
            Log.e(TAG, exc.getMessage());
            exc.printStackTrace();
        }
        return new ApiListCommentsForPost(comments);
    }
}