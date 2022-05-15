package com.example.mobileapplicationdeveloper700.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import com.example.mobileapplicationdeveloper700.DBHelper;
import com.example.mobileapplicationdeveloper700.RatingHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileapplicationdeveloper700.R;
import com.example.mobileapplicationdeveloper700.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        View view = getLayoutInflater().inflate(R.layout.custom_dialog_layout,null);
        DBHelper db = new DBHelper(this.getContext());

        Button submit  = view.findViewById(R.id.submit);
        RatingBar ratingBar  = ((RatingBar)view.findViewById(R.id.ratingBar));
        float rating = ratingBar.getRating();
        submit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float rating = ratingBar.getRating();
                ((TextView)view.findViewById(R.id.txtRating)).setText(String.valueOf(rating));
            }});

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openDialog(){
        DialogViewModel sampleDialog = new DialogViewModel();
        sampleDialog.show(getActivity().getSupportFragmentManager(), "example_Dialog");

    }
}