package co.pushfortask.Api;

import android.icu.util.TimeUnit;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;

import co.pushfortask.Api.Deserializers.GetCommentsDeserializer;
import co.pushfortask.Api.Deserializers.GetPostsDeserializer;
import co.pushfortask.Application.Application;
import co.pushfortask.Application.Constants;
import co.pushfortask.Application.Urls;
import co.pushfortask.Repository.Api.entities.ApiListCommentsForPost;
import co.pushfortask.Repository.Api.entities.ApiListPosts;
import co.pushfortask.Utils.NetworkUtilities;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Marios on 10/06/2017.
 */

public class PlaceHolderApiClient {

    private static final int CACHE_SIZE_MB = 50 * 1024 * 1024;
    private static PlaceHolderApiIntefrace sPlaceHolderService;
    private static OkHttpClient sOkHttpClient;

    private static OkHttpClient getOkHttpClient(){

        if(sOkHttpClient == null)
        {
            sOkHttpClient = new OkHttpClient();
            URL.setURLStreamHandlerFactory(new OkUrlFactory(sOkHttpClient));
            Cache cache = new Cache(Application.getContext().getCacheDir(), CACHE_SIZE_MB);
            sOkHttpClient.setCache(cache);
            sOkHttpClient.setConnectTimeout(Constants.HTTP_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS);
            sOkHttpClient.setWriteTimeout(Constants.HTTP_TIMEOUT_SECONDS,java.util.concurrent.TimeUnit.SECONDS);
            sOkHttpClient.setReadTimeout(Constants.HTTP_TIMEOUT_SECONDS,java.util.concurrent.TimeUnit.SECONDS);

            sOkHttpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();
                        Response response = null;
                        boolean responseOK = false;
                        int tryCount = 0;

                        while (!responseOK && tryCount < 3) {
                            try {
                                response = chain.proceed(request);
                                responseOK = response.isSuccessful();
                            }catch (Exception e){
                                Log.d("intercept", "Request is not successful - " + tryCount);
                            }finally{
                                tryCount++;
                            }
                        }
                        return response;

                }
            });
        }

        return sOkHttpClient;

    }

    public static PlaceHolderApiIntefrace getsPlaceHolderClient(){

        if(sPlaceHolderService == null)
        {
            Gson gsonBuilder = new GsonBuilder()
                    .registerTypeAdapter(ApiListPosts.class, new GetPostsDeserializer())
                    .registerTypeAdapter(ApiListCommentsForPost.class, new GetCommentsDeserializer())
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Urls.ENDPOINT_PLACEHOLDER_API)
                    .setClient(new OkClient(getOkHttpClient()))
                    .setConverter(new GsonConverter(gsonBuilder))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            if (NetworkUtilities.isOnline(Application.getContext())) {
                                int maxAge = 14400;
                                requestFacade.addHeader("Cache-Control", "public, max-age=" + maxAge);
                            } else {
                                int maxStale = 60 * 60 * 24 * 28;
                                requestFacade.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
                            }
                        }
                    })
                    .build();

            //TODO: ADD DEBUG LEVEL LOGS FROM RETROFIT
            //if (BuildConfig.DEBUG) {
                restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
           // }

            sPlaceHolderService = restAdapter.create(PlaceHolderApiIntefrace.class);
        }
        return sPlaceHolderService;
    }
}
