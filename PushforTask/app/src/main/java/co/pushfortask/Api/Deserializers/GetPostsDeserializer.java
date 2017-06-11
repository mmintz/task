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
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Repository.Api.entities.ApiPost;

/**
 * Created by Marios on 10/06/2017.
 */

public class GetPostsDeserializer extends PlaceHolderDeserializer{

    private static final String TAG = GetPostsDeserializer.class.getName();

    @Override
    public ApiListPosts deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<ApiPost> posts = null;
        try{
            ApiBaseClass baseClass = super.deserialize(jsonElement, type , jsonDeserializationContext);
            Type typeArrayPosts = new TypeToken<List<ApiPost>>(){
            }.getType();
            posts = new Gson().fromJson(baseClass.getJsonElementResult(),typeArrayPosts);
        }
        catch (Exception exc)
        {
            Log.e(TAG,exc.getMessage());
            exc.printStackTrace();
        }
        return new ApiListPosts(posts);
    }
}
