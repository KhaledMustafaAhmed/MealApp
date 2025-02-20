package com.example.dishdash.uiLayer.authentication.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.firebase.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.firebaseRepo.FirebaseRepository;

public class LoginFragment extends Fragment implements ILogin {
    LoginPresenter loginPresenter;
    EditText et_login_password, et_login_email;
    TextView tv_login_create_account ;
    Button btn_login;
    ProgressBar login_progress_bar;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_login_password = view.findViewById(R.id.et_login_password);
        et_login_email = view.findViewById(R.id.et_login_email);
        tv_login_create_account = (TextView) view.findViewById(R.id.tv_login_create_account);
        loginPresenter = new LoginPresenter(this, new FirebaseRepository(new FirebaseRemoteDataSource()));

        tv_login_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Navigate to signup page */
            }
        });
        btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                loginPresenter.validateData(new Pair<>(et_login_email.getText().toString(), et_login_password.getText().toString()));
            }
        });
    }

    @Override
    public void showProgressBar() {
        login_progress_bar.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        login_progress_bar.setVisibility(View.GONE);
        btn_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess() {
        hideProgressBar();
        //loginPresenter.getCurrentUser();
        /* show snakbar and navigate to home screen  */
    }

    @Override
    public void onLoginFail(String errorMessage) {
        hideProgressBar();
        /* show snackbar to display the error message */

    }
    @Override
    public void onValidateSuccess(User user) {
        loginPresenter.doLoginWithFirebase(user);
    }
    @Override
    public void onValidateFail(int message) {
        hideProgressBar();
        switch (message){
            case ILogin.EMAIL:
                /*  */
                break;
            case ILogin.PASSWORD:
                /*  */
                break;

            case ILogin.BOTH:
                /*  */
                break;
        }
    }
}