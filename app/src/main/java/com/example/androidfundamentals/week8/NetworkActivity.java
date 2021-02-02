package com.example.androidfundamentals.week8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfundamentals.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = "NetworkActivity";
    private MovieService movieService;

    @Nullable
    private ExecuteAll task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        movieService = NetworkHelper.getRetrofit().create(MovieService.class);
    }

    public void getTopRatedMovies(View view) {
        movieService.getTopRatedMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                handleMovieResp(response);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: Failed to get TopRatedMovies", t);
            }
        });
    }

    public void getUpcomingMovies(View view) {
        movieService.getUpcomingMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                handleMovieResp(response);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: Failed to get TopRatedMovies", t);
            }
        });
    }

    public void getNowPlayingMovies(View view) {
        movieService.getNowPlayingMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                handleMovieResp(response);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: Failed to get TopRatedMovies", t);
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void executeAllRequests(View view) {
        cancelTask(task);
        task = new ExecuteAll(movieService);
        task.execute(1);
    }

    @Override
    protected void onDestroy() {
        cancelTask(task);
        super.onDestroy();
    }

    @SuppressWarnings({"deprecation", "rawtypes"})
    private void cancelTask(AsyncTask task) {
        if (null != task && !task.isCancelled()) {
            //noinspection deprecation
            task.cancel(true);
        }
    }

    @SuppressWarnings("deprecation")
    private static class ExecuteAll extends AsyncTask<Integer, Void, Boolean> {

        private final MovieService movieService;

        ExecuteAll(@NonNull MovieService movieService) {
            this.movieService = movieService;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute() called");
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            Integer page = params[0];
            if (page == null) {
                page = 1;
            }

            try {
                if (isCancelled()) return false;
                handleMovieResp(movieService.getTopRatedMovies(page).execute());
                if (isCancelled()) return false;
                handleMovieResp(movieService.getUpcomingMovies(page).execute());
                if (isCancelled()) return false;
                handleMovieResp(movieService.getNowPlayingMovies(page).execute());
            } catch (IOException e) {
                Log.e(TAG, "executeAll: Failure ", e);
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute() called with: result = [" + result + "]");
        }
    }

    private static void handleMovieResp(@NonNull Response<MovieResult> resultResponse) {
        MovieResult movieResult = resultResponse.body();
        if (movieResult == null) {
            return;
        }
        List<Result> results = movieResult.getResults();
        if (results == null) {
            return;
        }
        for (Result result : results) {
            Log.d(TAG, "onResponse: result= " + result);
        }
    }
}