package com.example.odmtavern;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.odmtavern.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup radioGroup2;
        final int[] pronoun = {0};
        radioGroup2 = view.findViewById(R.id.radio_group2);
        if (radioGroup2 != null) {
            radioGroup2.setOnCheckedChangeListener((radioGroup21, checkedButtonId) -> {
                if (checkedButtonId == R.id.she) {
                    ((MainActivity) requireActivity()).updatePronoun(0);
                } else if (checkedButtonId == R.id.he) {
                    ((MainActivity) requireActivity()).updatePronoun(1);
              } else {
                    ((MainActivity) requireActivity()).updatePronoun(2);
             }
            });
        }
        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}