package shopwise.freshcart_driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.TestLooperManager;
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

public class Register extends AppCompatActivity {

    private EditText Name;
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button btnSubmit;

    private FirebaseAuth mAuth;

    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = (EditText) findViewById(R.id.txtName);
        Username = (EditText) findViewById(R.id.txtUsername);
        Email = (EditText) findViewById(R.id.txtEmail);
        Password = (EditText) findViewById(R.id.txtPassword);
        ConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        mRegProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getEditableText().toString();
                String username = Username.getEditableText().toString();
                String email = Email.getEditableText().toString();
                String password = Password.getEditableText().toString();

                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(username) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    register_driver(name, username, email, password);
                }


            }
        });
            }

    private void register_driver(String name, String username, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mRegProgress.dismiss();

                    //Toast.makeText(Register.this, "Registration SUCCESSFUL", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(Register.this, Home.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
                else
                {
                    mRegProgress.hide();
                    Toast.makeText(Register.this, "Cannot sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

