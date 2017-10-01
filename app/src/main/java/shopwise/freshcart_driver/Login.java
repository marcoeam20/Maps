package shopwise.freshcart_driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText log_txtEmail;
    private EditText log_txtPassword;
    private Button log_btnSubmit;

    private FirebaseAuth mAuth;

    private ProgressDialog LogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //id referencing
        log_txtEmail = (EditText) findViewById(R.id.log_txtEmail);
        log_txtPassword = (EditText) findViewById(R.id.log_txtPassword);
        log_btnSubmit = (Button) findViewById(R.id.log_btnSubmit);

        LogProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();



        log_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String email = log_txtEmail.getEditableText().toString();
                String password  = log_txtPassword.getEditableText().toString();



                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    LogProgress.setTitle("Login User");
                    LogProgress.setMessage("Please wait while we verify your credentials.");
                    LogProgress.setCanceledOnTouchOutside(false);
                    LogProgress.show();

                    login_user(email,password);

                }

            }
        });
    }

    private void login_user(String email, String password) {


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    LogProgress.dismiss();

                    Intent LoginIntent = new Intent(Login.this, Home.class);
                    LoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(LoginIntent);
                    finish();
                } else {

                    LogProgress.hide();

                    Toast.makeText(getApplicationContext(), "There is an error with logging in to your account", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
