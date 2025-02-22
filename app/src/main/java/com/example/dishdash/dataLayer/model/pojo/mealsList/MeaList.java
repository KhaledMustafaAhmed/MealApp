package com.example.dishdash.dataLayer.model.pojo.mealsList;

import java.util.List;

public class MeaList{
	private List<MealsItem> meals;

	public List<MealsItem> getMeals(){
		return meals;
	}

	public int getSize(){
		return meals.size();
	}
}