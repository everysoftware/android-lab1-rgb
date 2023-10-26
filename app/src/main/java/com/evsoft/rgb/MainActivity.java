package com.evsoft.rgb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button buttonGo;
    AlertDialog colorErrorAlert;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Integer> colors = new HashMap<>();

        colors.put("red", this.getColor(R.color.red));
        colors.put("green", this.getColor(R.color.green));
        colors.put("blue", this.getColor(R.color.blue));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("Please enter a valid color.")
                .setCancelable(false)
                .setNegativeButton("OK",
                        (dialog, id) -> dialog.cancel());

        colorErrorAlert = builder.create();

        editText = findViewById(R.id.editTextText);
        buttonGo = findViewById(R.id.buttonGo);

        buttonGo.setOnClickListener(v -> {
            String color = editText.getText().toString().toLowerCase().trim();
            if (!colors.containsKey(color)) {
                colorErrorAlert.show();
                return;
            }

            Intent intent = new Intent(getApplicationContext(), ColorActivity.class);

            intent.putExtra("bgColor", colors.get(color));

            startActivity(intent);
        });
    }
}
