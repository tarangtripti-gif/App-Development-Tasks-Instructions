package com.codealpha.quotegenerator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote, tvAuthor;
    private Button btnNewQuote;

    private String[][] quotes = {
        {"The only way to do great work is to love what you do.", "Steve Jobs"},
        {"Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill"},
        {"Believe you can and you're halfway there.", "Theodore Roosevelt"},
        {"It does not matter how slowly you go as long as you do not stop.", "Confucius"},
        {"Everything you've ever wanted is on the other side of fear.", "George Addair"},
        {"Hardships often prepare ordinary people for an extraordinary destiny.", "C.S. Lewis"},
        {"Dream big and dare to fail.", "Norman Vaughan"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        tvAuthor = findViewById(R.id.tvAuthor);
        btnNewQuote = findViewById(R.id.btnNewQuote);

        displayRandomQuote();

        btnNewQuote.setOnClickListener(v -> displayRandomQuote());
    }

    private void displayRandomQuote() {
        int randomIndex = new Random().nextInt(quotes.length);
        tvQuote.setText("\"" + quotes[randomIndex][0] + "\"");
        tvAuthor.setText("- " + quotes[randomIndex][1]);
    }
}
