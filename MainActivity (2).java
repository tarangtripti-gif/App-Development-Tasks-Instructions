package com.codealpha.flashcardapp;

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

    private List<Flashcard> flashcards;
    private int currentIndex = 0;

    private TextView tvQuestion, tvAnswer;
    private Button btnShowAnswer, btnNext, btnPrevious, btnAdd, btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcards = new ArrayList<>();
        // Sample data
        flashcards.add(new Flashcard("What is the capital of France?", "Paris"));
        flashcards.add(new Flashcard("What is 5 + 7?", "12"));

        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnShowAnswer = findViewById(R.id.btnShowAnswer);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        updateUI();

        btnShowAnswer.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                tvAnswer.setVisibility(View.VISIBLE);
                tvAnswer.setText(flashcards.get(currentIndex).getAnswer());
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < flashcards.size() - 1) {
                currentIndex++;
                updateUI();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        btnAdd.setOnClickListener(v -> showFlashcardDialog(null, -1));

        btnEdit.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                showFlashcardDialog(flashcards.get(currentIndex), currentIndex);
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                flashcards.remove(currentIndex);
                if (currentIndex >= flashcards.size() && currentIndex > 0) {
                    currentIndex--;
                }
                updateUI();
                Toast.makeText(this, "Flashcard deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (flashcards.isEmpty()) {
            tvQuestion.setText("No flashcards available. Add one!");
            tvAnswer.setVisibility(View.GONE);
            btnShowAnswer.setEnabled(false);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        } else {
            tvQuestion.setText(flashcards.get(currentIndex).getQuestion());
            tvAnswer.setVisibility(View.GONE);
            btnShowAnswer.setEnabled(true);
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }

    private void showFlashcardDialog(Flashcard flashcard, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_flashcard, null);
        EditText etQuestion = view.findViewById(R.id.etQuestion);
        EditText etAnswer = view.findViewById(R.id.etAnswer);

        if (flashcard != null) {
            etQuestion.setText(flashcard.getQuestion());
            etAnswer.setText(flashcard.getAnswer());
            builder.setTitle("Edit Flashcard");
        } else {
            builder.setTitle("Add Flashcard");
        }

        builder.setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    String q = etQuestion.getText().toString();
                    String a = etAnswer.getText().toString();
                    if (!q.isEmpty() && !a.isEmpty()) {
                        if (flashcard != null) {
                            flashcards.set(index, new Flashcard(q, a));
                        } else {
                            flashcards.add(new Flashcard(q, a));
                            currentIndex = flashcards.size() - 1;
                        }
                        updateUI();
                    } else {
                        Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
