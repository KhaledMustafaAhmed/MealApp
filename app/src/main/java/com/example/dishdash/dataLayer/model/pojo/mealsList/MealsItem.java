package com.example.dishdash.dataLayer.model.pojo.mealsList;

import android.util.Pair;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsItem{
	private String idMeal;
	private String strMeal;
	private Object strDrinkAlternate;
	private String strCategory;
	private String strArea;
	private String strInstructions;
	private String strMealThumb;
	private String strTags;
	private String strYoutube;
	private String strIngredient1;
	private String strIngredient2;
	private String strIngredient3;
	private String strIngredient4;
	private String strIngredient5;
	private String strIngredient6;
	private String strIngredient7;
	private String strIngredient8;
	private String strIngredient9;
	private String strIngredient10;
	private String strIngredient11;
	private String strIngredient12;
	private String strIngredient13;
	private String strIngredient14;
	private String strIngredient15;
	private String strIngredient16;
	private String strIngredient17;
	private String strIngredient18;
	private String strIngredient19;
	private String strIngredient20;
	private String strMeasure1;
	private String strMeasure2;
	private String strMeasure3;
	private String strMeasure4;
	private String strMeasure5;
	private String strMeasure6;
	private String strMeasure7;
	private String strMeasure8;
	private String strMeasure9;
	private String strMeasure10;
	private String strMeasure11;
	private String strMeasure12;
	private String strMeasure13;
	private String strMeasure14;
	private String strMeasure15;
	private String strMeasure16;
	private String strMeasure17;
	private String strMeasure18;
	private String strMeasure19;
	private String strMeasure20;
	private String strSource;
	private Object strImageSource;

	private Object strCreativeCommonsConfirmed;

	private Object dateModified;

	public Object getStrImageSource(){
		return strImageSource;
	}

	public String getStrIngredient10(){
		return strIngredient10;
	}

	public String getStrIngredient12(){
		return strIngredient12;
	}

	public String getStrIngredient11(){
		return strIngredient11;
	}

	public String getStrIngredient14(){
		return strIngredient14;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public String getStrIngredient13(){
		return strIngredient13;
	}

	public String getStrIngredient16(){
		return strIngredient16;
	}

	public String getStrIngredient15(){
		return strIngredient15;
	}

	public String getStrIngredient18(){
		return strIngredient18;
	}

	public String getStrIngredient17(){
		return strIngredient17;
	}

	public String getStrArea(){
		return strArea;
	}

	public Object getStrCreativeCommonsConfirmed(){
		return strCreativeCommonsConfirmed;
	}

	public String getStrIngredient19(){
		return strIngredient19;
	}

	public String getStrTags(){
		return strTags;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public String getStrInstructions(){
		return strInstructions;
	}

	public String getStrIngredient1(){
		return strIngredient1;
	}

	public String getStrIngredient3(){
		return strIngredient3;
	}

	public String getStrIngredient2(){
		return strIngredient2;
	}

	public String getStrIngredient20(){
		return strIngredient20;
	}

	public String getStrIngredient5(){
		return strIngredient5;
	}

	public String getStrIngredient4(){
		return strIngredient4;
	}

	public String getStrIngredient7(){
		return strIngredient7;
	}

	public String getStrIngredient6(){
		return strIngredient6;
	}

	public String getStrIngredient9(){
		return strIngredient9;
	}

	public String getStrIngredient8(){
		return strIngredient8;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public String getStrMeasure20(){
		return strMeasure20;
	}

	public String getStrYoutube(){
		return strYoutube;
	}

	public String getStrMeal(){
		return strMeal;
	}

	public String getStrMeasure12(){
		return strMeasure12;
	}

	public String getStrMeasure13(){
		return strMeasure13;
	}

	public String getStrMeasure10(){
		return strMeasure10;
	}

	public String getStrMeasure11(){
		return strMeasure11;
	}

	public Object getDateModified(){
		return dateModified;
	}

	public Object getStrDrinkAlternate(){
		return strDrinkAlternate;
	}

	public String getStrSource(){
		return strSource;
	}

	public String getStrMeasure9(){
		return strMeasure9;
	}

	public String getStrMeasure7(){
		return strMeasure7;
	}

	public String getStrMeasure8(){
		return strMeasure8;
	}

	public String getStrMeasure5(){
		return strMeasure5;
	}

	public String getStrMeasure6(){
		return strMeasure6;
	}

	public String getStrMeasure3(){
		return strMeasure3;
	}

	public String getStrMeasure4(){
		return strMeasure4;
	}

	public String getStrMeasure1(){
		return strMeasure1;
	}

	public String getStrMeasure18(){
		return strMeasure18;
	}

	public String getStrMeasure2(){
		return strMeasure2;
	}

	public String getStrMeasure19(){
		return strMeasure19;
	}

	public String getStrMeasure16(){
		return strMeasure16;
	}

	public String getStrMeasure17(){
		return strMeasure17;
	}

	public String getStrMeasure14(){
		return strMeasure14;
	}

	public String getStrMeasure15(){
		return strMeasure15;
	}

	public String getIngredientByIndex(int index){
		switch (index) {
			case 1: return strIngredient1;
			case 2: return strIngredient2;
			case 3: return strIngredient3;
			case 4: return strIngredient4;
			case 5: return strIngredient5;
			case 6: return strIngredient6;
			case 7: return strIngredient7;
			case 8: return strIngredient8;
			case 9: return strIngredient9;
			case 10: return strIngredient10;
			case 11: return strIngredient11;
			case 12: return strIngredient12;
			case 13: return strIngredient13;
			case 14: return strIngredient14;
			case 15: return strIngredient15;
			case 16: return strIngredient16;
			case 17: return strIngredient17;
			case 18: return strIngredient18;
			case 19: return strIngredient19;
			case 20: return strIngredient20;
			default: return null;
		}
	}

	public String getMeasureByIndex(int index){
		switch (index) {
			case 1: return strMeasure1;
			case 2: return strMeasure2;
			case 3: return strMeasure3;
			case 4: return strMeasure4;
			case 5: return strMeasure5;
			case 6: return strMeasure6;
			case 7: return strMeasure7;
			case 8: return strMeasure8;
			case 9: return strMeasure9;
			case 10: return strMeasure10;
			case 11: return strMeasure11;
			case 12: return strMeasure12;
			case 13: return strMeasure13;
			case 14: return strMeasure14;
			case 15: return strMeasure15;
			case 16: return strMeasure16;
			case 17: return strMeasure17;
			case 18: return strMeasure18;
			case 19: return strMeasure19;
			case 20: return strMeasure20;
			default: return null;
		}
	}

	public List<Pair<String,String>> getIngredientsAndMeasures(MealsItem mealsItem){
		List<Pair<String,String>> IngredientsAndMeasures = new ArrayList<>();
		for(int i=1; i<=20; i++){
			if(!(mealsItem.getIngredientByIndex(i) == null || mealsItem.getIngredientByIndex(i).isEmpty())){
				IngredientsAndMeasures.add(new Pair<>(getIngredientByIndex(i), getMeasureByIndex(i)));
			}
		}
		return IngredientsAndMeasures;
	}
}
