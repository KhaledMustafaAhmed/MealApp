package com.example.dishdash.uiLayer.plans;

import com.example.dishdash.dataLayer.model.entities.PlannedMeal;

import java.util.List;

public interface IPlansViews {
    public void showPlannedDeleted();

    public void receiveAllPlannedMeals(List<PlannedMeal> plannedMeals);
}
