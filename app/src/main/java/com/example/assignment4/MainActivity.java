package com.example.assignment4;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> recipeCategories;
    HashMap<String, List<Recipe>> recipes;
    CustomExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);
        prepareData();

        adapter = new CustomExpandableListAdapter(this, recipeCategories, recipes);
        expandableListView.setAdapter(adapter);
    }

    private void prepareData() {
        recipeCategories = new ArrayList<>();
        recipes = new HashMap<>();

        // Add categories
        recipeCategories.add("Breakfast");
        recipeCategories.add("Lunch");
        recipeCategories.add("Dinner");

        // Add recipes
        List<Recipe> breakfastRecipes = new ArrayList<>();
        breakfastRecipes.add(new Recipe("Pancakes", "Ingredients: Flour, Eggs, Milk\nInstructions: Mix and fry."));
        breakfastRecipes.add(new Recipe("Omelette", "Ingredients: Eggs, Cheese\nInstructions: Beat and cook."));

        List<Recipe> lunchRecipes = new ArrayList<>();
        lunchRecipes.add(new Recipe("Grilled Chicken", "Ingredients: Chicken, Spices\nInstructions: Grill the chicken."));
        lunchRecipes.add(new Recipe("Caesar Salad", "Ingredients: Lettuce, Dressing\nInstructions: Toss together."));

        List<Recipe> dinnerRecipes = new ArrayList<>();
        dinnerRecipes.add(new Recipe("Spaghetti", "Ingredients: Pasta, Sauce\nInstructions: Boil and mix."));
        dinnerRecipes.add(new Recipe("Steak", "Ingredients: Beef, Seasoning\nInstructions: Grill to desired doneness."));

        recipes.put(recipeCategories.get(0), breakfastRecipes);
        recipes.put(recipeCategories.get(1), lunchRecipes);
        recipes.put(recipeCategories.get(2), dinnerRecipes);
    }
}
