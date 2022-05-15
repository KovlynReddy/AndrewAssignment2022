package com.example.mobileapplicationdeveloper700.ui.home;

import android.os.Bundle;
import android.os.VibrationAttributes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileapplicationdeveloper700.R;
import com.example.mobileapplicationdeveloper700.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private final int VatPerc = 5;
    EditText Labour = (EditText) getView().findViewById(R.id.edtLabourCost);
    EditText Material = (EditText) getView().findViewById(R.id.edtMaterialCost3);
    TextView VatOutput = (TextView) getView().findViewById(R.id.txtTax);
    TextView SubTotalOutput = (TextView) getView().findViewById(R.id.txtSubTotal);
    TextView TotalOutPut = (TextView) getView().findViewById(R.id.txtToal);

    Button Calculate = (Button) getView().findViewById(R.id.btnCalculate);



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        Calculate.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnCalculate:
                        double subtotal = Double.parseDouble(Labour.getText().toString()) + Double.parseDouble(Material.getText().toString());
                        SubTotalOutput.setText(Double.toString(subtotal));
                        double vat = ((subtotal / 100) * VatPerc);
                        VatOutput.setText(Double.toString(vat));
                        double Total = (vat + subtotal);
                        TotalOutPut.setText(Double.toString(Total));

                        break;
                }
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}