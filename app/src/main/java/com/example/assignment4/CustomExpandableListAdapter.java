package com.example.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> categories;
    private HashMap<String, List<Recipe>> recipes;

    public CustomExpandableListAdapter(Context context, List<String> categories, HashMap<String, List<Recipe>> recipes) {
        this.context = context;
        this.categories = categories;
        this.recipes = recipes;
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return recipes.get(categories.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return recipes.get(categories.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String categoryName = (String) getGroup(groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categoryName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Recipe recipe = (Recipe) getChild(groupPosition, childPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_child_item, parent, false);
        }

        TextView recipeName = convertView.findViewById(R.id.recipeName);
        TextView recipeDetails = convertView.findViewById(R.id.recipeDetails);

        recipeName.setText(recipe.name);

        // Toggle visibility of details when clicked
        recipeName.setOnClickListener(v -> {
            if (recipeDetails.getVisibility() == View.GONE) {
                recipeDetails.setVisibility(View.VISIBLE);
            } else {
                recipeDetails.setVisibility(View.GONE);
            }
        });

        recipeDetails.setText(recipe.details);
        recipeDetails.setVisibility(View.GONE); // Initially hidden

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
