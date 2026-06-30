package com.codealpha.fitnesstracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FitnessActivity> activityList;
    private TextView tvSummary;
    private Button btnLogActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityList = new ArrayList<>();
        tvSummary = findViewById(R.id.tvSummary);
        btnLogActivity = findViewById(R.id.btnLogActivity);

        updateSummary();

        btnLogActivity.setOnClickListener(v -> showLogDialog());
    }

    private void updateSummary() {
        int totalSteps = 0;
        int totalCalories = 0;
        for (FitnessActivity activity : activityList) {
            totalSteps += activity.getSteps();
            totalCalories += activity.getCalories();
        }

        String summary = "Total Activities: " + activityList.size() +
                         "\nTotal Steps: " + totalSteps +
                         "\nTotal Calories Burned: " + totalCalories;
        tvSummary.setText(summary);
    }

    private void showLogDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_log_activity, null);
        EditText etType = view.findViewById(R.id.etType);
        EditText etSteps = view.findViewById(R.id.etSteps);
        EditText etCalories = view.findViewById(R.id.etCalories);

        builder.setTitle("Log Fitness Activity")
                .setView(view)
                .setPositiveButton("Add", (dialog, which) -> {
                    String type = etType.getText().toString();
                    String stepsStr = etSteps.getText().toString();
                    String caloriesStr = etCalories.getText().toString();

                    if (!type.isEmpty() && !stepsStr.isEmpty() && !caloriesStr.isEmpty()) {
                        int steps = Integer.parseInt(stepsStr);
                        int calories = Integer.parseInt(caloriesStr);
                        activityList.add(new FitnessActivity(type, steps, calories));
                        updateSummary();
                        Toast.makeText(this, "Activity Logged", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
