package kcostarella.crunchtime;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.HashMap;
import android.widget.AdapterView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    //The Ratio of Reps/Calories


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context ctx = this;

        FillData();
        lv = (ListView) findViewById(R.id.mainList);
        ArrayAdapter<String> exerciseAdaptor = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Data.getInstance().allExercises);
        //Setting the android ListView's adapter to the newly created adapter
        lv.setAdapter(exerciseAdaptor);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;

                //Get the String value of the item where the user clicked
                String itemValue = (String) lv.getItemAtPosition(position);

                //In order to start displaying new activity we need an intent
                Intent intent = new Intent(ctx, ExerciseActivity.class);
                intent.putExtra("EXERCISE_NAME", itemValue);

                //Putting the Id of image as an extra in intent


                //Here we will pass the previously created intent as parameter
                startActivity(intent);

            }
        });
    }



    //Fills lists with initial content
    private void FillData() {
        Data.getInstance().Fill();

    }


}
