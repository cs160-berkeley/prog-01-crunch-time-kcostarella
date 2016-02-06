package kcostarella.crunchtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;


public class ResultActivity extends AppCompatActivity {
    int amount;
    String name;
    TextView tv;
    ListView lv;
    float calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        amount = intent.getIntExtra("AMOUNT",0);
        name = intent.getStringExtra("EXERCISE_NAME");
        tv =  (TextView) findViewById(R.id.textView);
        lv = (ListView) findViewById(R.id.listView);
        Setup();
    }

    private void Setup() {
        CalorieSetup();
        ListSetup();
    }

    private void CalorieSetup() {
        float inverse = Data.getInstance().getInverse(name);
        calories = inverse * amount;

        tv.setText(String.format("%.1f Calories burned from %s",calories,name));
    }

    private void ListSetup() {
        String[] others = new String[Data.getInstance().allExercises.length - 1];

        int i = 0;
        for (String s : Data.getInstance().allExercises) {
            if (!s.equals(name)) {
                float rate = Data.getInstance().getRatio(s);
                float otherCal = calories * rate;
                String type = Data.getInstance().getType(s);
                others[i] = String.format("%.1f %s of %s", otherCal, type, s);
                i= i + 1;
            }
        }
        ArrayAdapter<String> exerciseAdaptor = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, others);
        lv.setAdapter(exerciseAdaptor);

    }

}
