package com.example.xkcd;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface XkcdService {

        @GET("{idImg}/info.0.json")
        Call<Comic> getImg(@Path("idImg") int idImg);

}
