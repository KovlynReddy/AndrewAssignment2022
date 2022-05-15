package com.example.mobileapplicationdeveloper700.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileapplicationdeveloper700.R;
import com.example.mobileapplicationdeveloper700.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        View view = getLayoutInflater().inflate(R.layout.custom_dialog_layout,null);
        Button submit  = view.findViewById(R.id.submit);
        RatingBar ratingBar  = ((RatingBar)view.findViewById(R.id.ratingBar));
        float rating = ratingBar.getRating();
        submit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper db = new DBHelper(this);
                db.rate(null,ratingBar,null,null);
                ((TextView)view.findViewById(R.id.txtRating)).setText(String.valueOf(rating));
//                ((TextView)view.findViewById(R.id.txtDish)).setText();
//                ((TextView)view.findViewById(R.id.txtResteraunt)).setText();
            }});

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}