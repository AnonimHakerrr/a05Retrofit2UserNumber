package edu.itstep.a05retrofit2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.itstep.a05retrofit2.R;
import edu.itstep.a05retrofit2.api.UserApi;
import edu.itstep.a05retrofit2.model.User;
import edu.itstep.a05retrofit2.service.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvUserName;
    private TextView tvEmail;

    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvName = findViewById(R.id.tvName);
        tvUserName = findViewById(R.id.tvUserName);
        tvEmail = findViewById(R.id.tvEmail);

        userApi = NetworkService.getInstance().getUserApi();

        int userId = getIntent().getIntExtra(ListPostsActivity.USER_ID, 0);

        if (userId != 0) {
            userApi.getUserById(userId)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            tvName.setText(user.getName());
                            tvUserName.setText(user.getUserName());
                            tvEmail.setText(user.getEmail());
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            tvName.setText(t.getMessage());
                            tvUserName.setText(t.getMessage());
                            tvEmail.setText(t.getMessage());
                        }
                    });
        }
    }
}