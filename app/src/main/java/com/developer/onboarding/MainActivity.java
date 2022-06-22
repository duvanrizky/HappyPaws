package com.developer.onboarding;


import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private Button buttonOnBoardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.indicator_layout);
        buttonOnBoardingAction = findViewById(R.id.btn_action);

        if (restorePreData()){
            Intent mainActivity = new Intent(getApplicationContext(), Mainscreen.class);
            startActivity(mainActivity);
            finish();
        }

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.slideViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setUpOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }
                else {
                    startActivity(new Intent(getApplicationContext(),Mainscreen.class));
                    savePrefsDara();
                    finish();
                }
            }
        });

    }

    private void setupOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemBoarding1 = new OnboardingItem();
        itemBoarding1.setHeading("Happy Paws");
        itemBoarding1.setDesc("Introducing Happy Paws");
        itemBoarding1.setDesA("We will give our love for your pet");
        itemBoarding1.setImage(R.drawable.boarding1);

        OnboardingItem itemBoarding2 = new OnboardingItem();
        itemBoarding2.setHeading("Pet Care Booking");
        itemBoarding2.setDesc("We will take care of your pets with full of love");
        itemBoarding2.setImage(R.drawable.boarding2);

        OnboardingItem itemBoarding3 = new OnboardingItem();
        itemBoarding3.setHeading("Vetetinary");
        itemBoarding3.setDesc("Meet our Vet Doctor that your pet will love");
        itemBoarding3.setDesA(" ");
        itemBoarding3.setImage(R.drawable.boarding3);

        OnboardingItem itemBoarding4 = new OnboardingItem();
        itemBoarding4.setHeading("Grooming Booking");
        itemBoarding4.setDesc("Our Grooming experts will make your pets healthy and more beauty");
        itemBoarding4.setImage(R.drawable.boarding4);

        OnboardingItem itemBoarding5 = new OnboardingItem();
        itemBoarding5.setHeading("Store");
        itemBoarding5.setDesc("Spoil your pets with treats and acessories from our store");
        itemBoarding5.setDesA(" ");
        itemBoarding5.setImage(R.drawable.boarding5);

        onboardingItems.add(itemBoarding1);
        onboardingItems.add(itemBoarding2);
        onboardingItems.add(itemBoarding3);
        onboardingItems.add(itemBoarding4);
        onboardingItems.add(itemBoarding5);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setUpOnboardingIndicators () {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void  setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1) {
            buttonOnBoardingAction.setText("Lets Start");
            buttonOnBoardingAction.setBackground(getDrawable(R.drawable.solid_pink));
        } else {
            buttonOnBoardingAction.setText("Next");
            buttonOnBoardingAction.setBackground(getDrawable(R.drawable.solid_purple));
        }
    }

    private boolean restorePreData () {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = preferences.getBoolean("isIntroOpened", false);

        return isIntroActivityOpenedBefore;
    }

    private void savePrefsDara() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("isIntroOpened", true);
        editor.apply();
    }
}