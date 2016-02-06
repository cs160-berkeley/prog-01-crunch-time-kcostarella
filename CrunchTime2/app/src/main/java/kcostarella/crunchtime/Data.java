package kcostarella.crunchtime;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kcostarella on 2/1/16.
 */
public class Data {
    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
    }

    public void Fill() {
        reps = new HashMap<String, Float>();
        mins = new HashMap<String, Float>();

        reps.put("Pushups", 3.5f);
        reps.put("Situps", 2.0f);
        mins.put("Jogging", 0.12f);
        mins.put("Jumping Jack", 0.1f);
        reps.put("Squats", 2.25f);
        mins.put("Leg-lift", .25f);
        mins.put("Plank", .25f);
        reps.put("Pullups", 1.0f);
        mins.put("Cycling", .12f);
        mins.put("Walking", .20f);
        mins.put("Swimming", .13f);
        mins.put("Stair-Climbing", .15f);

        allExercises = new String[ mins.keySet().size()
                + reps.keySet().size()];
        int i = 0;
        for (String s : reps.keySet()) {
            allExercises[i] = s;
            i = i + 1;
        }
        for (String s : mins.keySet()) {
            allExercises[i] = s;
            i = i + 1;
        }
    }


    public Float getRatio(String key) {

        if (reps.containsKey(key)) {
            return reps.get(key);
        } else if (mins.containsKey(key)) {
            return mins.get(key);
        }
        else {
            return 0.0f;
        }

    }

    public String getType(String key) {
        if (reps.containsKey(key)) {
            return "Reps";
        } else {
            return "Minutes";
        }
    }

    public Float getInverse(String key) {
        Float ratio = getRatio(key);
        if (ratio != 0) {
            return 1 / getRatio(key);
        } else {
            return 0.0f;
        }
    }

    public static String[] allExercises;
    public static HashMap<String,Float> reps;
    //The Ratio of Mins/Calories
    public static HashMap<String,Float> mins;
    public static String name;
}
