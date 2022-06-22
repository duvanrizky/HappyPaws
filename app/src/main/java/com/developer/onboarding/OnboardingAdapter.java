package com.developer.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>{

    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slider_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private TextView textTittle, Desc, DescA;
        private ImageView imageOnboarding;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTittle = itemView.findViewById(R.id.tittleBoarding);
            Desc = itemView.findViewById(R.id.descBoarding);
            DescA = itemView.findViewById(R.id.descABoarding);
            imageOnboarding = itemView.findViewById(R.id.imageBoarding);
        }

        void setOnboardingData(OnboardingItem onboardingItem) {
            textTittle.setText(onboardingItem.getHeading());
            Desc.setText(onboardingItem.getDesc());
            DescA.setText(onboardingItem.getDesA());
            imageOnboarding.setImageResource(onboardingItem.getImage());
        }
    }
}
