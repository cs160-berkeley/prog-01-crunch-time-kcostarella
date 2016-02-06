package kcostarella.crunchtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseActivity extends AppCompatActivity {

    String name;
    String type;
    int amount = 0;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context ctx = this;

        final TextView editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = editText.getText().toString();
                if (!val.equals("")) {
                    amount = Integer.parseInt(editText.getText().toString());
                    if (amount == 0) {
                        Snackbar.make(view, String.format("Enter a number >0 above"), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    } else {
                        Intent intent = new Intent(ctx, ResultActivity.class);
                        intent.putExtra("AMOUNT", amount);
                        intent.putExtra("EXERCISE_NAME", name);
                        startActivity(intent);
                    }
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        name = intent.getStringExtra("EXERCISE_NAME");
        Data.getInstance().name = name;

        if (Data.reps.containsKey(name)) {
            type = "Reps";
        } else {
            type = "Minutes";
        }

        tv =  (TextView) findViewById(R.id.textView);

        tv.setText(String.format("Select amount of %s", name + " " + type));

        setTitle(String.format("%s", type));


    }

}
