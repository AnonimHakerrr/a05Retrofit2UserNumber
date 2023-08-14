package edu.itstep.a05retrofit2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import edu.itstep.a05retrofit2.R;
import edu.itstep.a05retrofit2.api.PostApi;
import edu.itstep.a05retrofit2.model.Post;
import edu.itstep.a05retrofit2.service.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private Button btnAllPosts;

    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        btnAllPosts = findViewById(R.id.btnAllPosts);

        postApi = NetworkService.getInstance().getPostApi();

        Call<Post> call = postApi.getPostById(2);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                tvTitle.setText(post.getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvTitle.setText(t.getMessage());
            }
        });

        btnAllPosts.setOnClickListener(v -> {
            startActivity(new Intent(this, ListPostsActivity.class));
        });
    }
}