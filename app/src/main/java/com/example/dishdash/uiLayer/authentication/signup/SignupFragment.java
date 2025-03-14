package com.example.dishdash.uiLayer.authentication.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.helper.Triple;

public class SignupFragment extends Fragment implements ISignup {
    private EditText emailEdit, passwordEdit, confirmPasswordEdit;
    private Button createAccountButton;
    private ProgressBar progressBar;
    private TextView loginButton;
    private SignupPresenter signupPresenter;
    private View view;

    public SignupFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUIComponent(view);

        signupPresenter = new SignupPresenter(this,new FirebaseRepository(new FirebaseRemoteDataSource()));

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                signupPresenter.validateData(new Triple<>(emailEdit.getText().toString(),
                        passwordEdit.getText().toString(), confirmPasswordEdit.getText().toString()));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Navigation.findNavController(v).navigate(R.id.action_signupFragment2_to_loginFragment);
            }
        });

    }
    private void initializeUIComponent(View view){
        this.view = view;
        emailEdit = (EditText) view.findViewById(R.id.et_signup_email);
        passwordEdit = (EditText) view.findViewById(R.id.et_signup_password);
        confirmPasswordEdit = (EditText) view.findViewById(R.id.et_signup_confirm_password);
        createAccountButton = (Button) view.findViewById(R.id.btn_signup_create_account);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_signup);
        loginButton = (TextView) view.findViewById(R.id.tv_signup_loginbtn);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        createAccountButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        createAccountButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignupSuccess() {
        Toast.makeText(getContext(), "Create Account is Done! Check your mail to verify!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_signupFragment2_to_loginFragment);
    }

    @Override
    public void onSignupFail(String errorMessage) {
        hideProgressBar();
        Toast.makeText(getContext(),errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidateSuccess(User user) {
        signupPresenter.doSignupWithFirebase(user);
    }

    @Override
    public void onValidateFail(int message) {
        hideProgressBar();
        switch (message){
            case ISignup.EMAIL:
                emailEdit.setError("Email is inValid");
                break;
            case ISignup.PASSWORD:
                passwordEdit.setError("Password length is inValid");
                break;
            case ISignup.CONFIRM_PASSWORD:
                confirmPasswordEdit.setError("Password not matched");
                break;
        }
    }
}
