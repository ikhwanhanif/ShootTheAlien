package ikhwan.hanif.shootthealien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private EditText nameET, emailET, passwordET, confirmPasswordET;
    private Button registerBtn;
    private TextView signInTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Set UI fullscreen immersive mode
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        registerBtn = findViewById(R.id.registBtn);
        signInTV = findViewById(R.id.signInTV);

        signInTV.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });

        registerBtn.setOnClickListener(view -> {
            String name = nameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            String confirmPassword = confirmPasswordET.getText().toString().trim();

            if (validateInputs(name, email, password, confirmPassword)) {
                registerUser(name, email, password);
            }
        });
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword) {
        if (name.isEmpty()) {
            nameET.setError("Please enter your name");
            return false;
        } else {
            nameET.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError(getString(R.string.masukkan_email_yang_benar));
            return false;
        } else {
            emailET.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordET.setError(getString(R.string.password_harus_setidaknya_6_karakter));
            return false;
        } else {
            passwordET.setError(null);
        }

        if (!confirmPassword.equals(password)) {
            confirmPasswordET.setError(getString(R.string.password_dan_confirm_password_harus_sama));
            return false;
        } else {
            confirmPasswordET.setError(null);
        }
        return true;
    }

    private void registerUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Update user profile with display name
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            user.updateProfile(profileUpdates).addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    saveUserToFirestore(user.getUid(), name, email);
                                    Toast.makeText(SignUpActivity.this, getString(R.string.sukses_daftar), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, getString(R.string.gagal_daftar), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserToFirestore(String userId, String name, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);

        db.collection("users").document(userId).set(user)
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(this, "User saved to Firestore", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to save user", Toast.LENGTH_SHORT).show());
    }
}
