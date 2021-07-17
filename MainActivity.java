package com.example.getwithparam;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.getwithparam.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    public MainActivity ctx = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onClickListner();
    }

    private void onClickListner() {
        mBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.etFirstName.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ctx, "Please enter first name", Toast.LENGTH_SHORT).show();

                else if (mBinding.etLastName.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ctx, "Please enter last name", Toast.LENGTH_SHORT).show();

                else if (!Patterns.EMAIL_ADDRESS.matcher(mBinding.etEmail.getText().toString().trim()).matches() ||
                        mBinding.etEmail.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ctx, "Please enter valid email", Toast.LENGTH_SHORT).show();

                else if (mBinding.etMobile.getText().toString().trim().equalsIgnoreCase("") ||
                        mBinding.etMobile.getText().toString().trim().length() < 10)
                    Toast.makeText(ctx, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();

                else if (mBinding.etCity.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ctx, "Please enter city", Toast.LENGTH_SHORT).show();

                else if (mBinding.etPassword.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ctx, "Please enter password", Toast.LENGTH_SHORT).show();

                else GetResponse();

            }
        });
    }

    private void GetResponse() {
        APIClient.getClient().create(APIInterface.class).getStatus(mBinding.etFirstName.getText().toString(), mBinding.etLastName.getText().toString(),
                mBinding.etEmail.getText().toString(), mBinding.etMobile.getText().toString(), mBinding.etCity.getText().toString(),
                mBinding.etPassword.getText().toString()).enqueue(new Callback<ParamModel>() {
            @Override
            public void onResponse(Call<ParamModel> call, Response<ParamModel> response) {
                Log.d("SHIV", response.body().toString());
                Toast.makeText(MainActivity.this, response.body().msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ParamModel> call, Throwable t) {
                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_LONG).show();
                Log.d("ERROR", t.getMessage());
            }
        });

        // progress.dismiss();
    }
}