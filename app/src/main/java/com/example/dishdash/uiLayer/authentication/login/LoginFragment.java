package com.example.dishdash.uiLayer.authentication.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishdash.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;

public class LoginFragment extends Fragment implements ILogin {
    private LoginPresenter loginPresenter;
    private EditText et_login_password, et_login_email;
    private TextView tv_login_create_account ;
    private Button btn_login, btn_guest_option;
    private ProgressBar login_progress_bar;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

        loginPresenter = new LoginPresenter(this, new FirebaseRepository(new FirebaseRemoteDataSource()),
                new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(requireContext())));

        tv_login_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signupFragment2);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                loginPresenter.validateData(new Pair<>(et_login_email.getText().toString(), et_login_password.getText().toString()));
            }
        });

        btn_guest_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginPresenter.continueAsGuest();
               Intent intent = new Intent(getActivity(), HomeActivity.class);
               startActivity(intent);
               getActivity().finish();
            }
        });
    }

    private void initUI(View view){
        login_progress_bar = view.findViewById(R.id.login_progress_bar);
        et_login_password = view.findViewById(R.id.et_login_password);
        et_login_email = view.findViewById(R.id.et_login_email);
        tv_login_create_account = (TextView) view.findViewById(R.id.tv_login_create_account);
        btn_login = view.findViewById(R.id.btn_login);
        btn_guest_option = view.findViewById(R.id.btn_guest_option);
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
        Toast.makeText(requireContext(), "login success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onLoginFail(String errorMessage) {
        hideProgressBar();
        Toast.makeText(requireContext(), "login failed check network", Toast.LENGTH_SHORT).show();
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
                et_login_email.setError("Invalid Email!");
                break;
            case ILogin.PASSWORD:
                et_login_password.setError("Password is to short!");
                break;
            case ILogin.BOTH:
                break;
        }
    }
}