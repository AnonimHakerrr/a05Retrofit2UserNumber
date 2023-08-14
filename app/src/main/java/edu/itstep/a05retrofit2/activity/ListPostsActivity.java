package edu.itstep.a05retrofit2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.itstep.a05retrofit2.R;
import edu.itstep.a05retrofit2.api.PostApi;
import edu.itstep.a05retrofit2.model.Post;
import edu.itstep.a05retrofit2.service.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPostsActivity extends AppCompatActivity {
    public static final String USER_ID = "userId";
    private ListView lvPosts;
    private ArrayAdapter<String> adapter;
    private List<Post> posts;
    private List<String> titles = new ArrayList<>();
    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);

        postApi = NetworkService.getInstance().getPostApi();

        lvPosts = findViewById(R.id.lvPosts);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lvPosts.setAdapter(adapter);

        postApi.getAllPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        posts = response.body();
                        for (Post post : posts) {
                            titles.add(post.getTitle());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });

        lvPosts.setOnItemClickListener((parent, view, position, id) -> {
            Post selectedPost = posts.get(position);
            int userId = selectedPost.getUserId();
            Intent intent = new Intent(this,UserActivity.class);
            intent.putExtra(USER_ID, userId);
            startActivity(intent);
        });
    }
}