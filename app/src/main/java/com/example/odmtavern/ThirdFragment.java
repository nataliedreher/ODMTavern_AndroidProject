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

import com.example.odmtavern.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup radioGroup3;
        final int[] age = {0};
        radioGroup3 = view.findViewById(R.id.radio_group3);
        if (radioGroup3 != null) {
            radioGroup3.setOnCheckedChangeListener((radioGroup31, checkedButtonId) -> {
                if (checkedButtonId == R.id.a1) {
                    ((MainActivity) requireActivity()).updateAge(0);
                } else if (checkedButtonId == R.id.a2) {
                    ((MainActivity) requireActivity()).updateAge(1);
                } else if (checkedButtonId == R.id.a3) {
                    ((MainActivity) requireActivity()).updateAge(2);
                } else if (checkedButtonId == R.id.a4) {
                    ((MainActivity) requireActivity()).updateAge(3);
                } else {
                    ((MainActivity) requireActivity()).updateAge(4);
                }
            });
        }
        binding.buttonThird.setOnClickListener(view1 -> NavHostFragment.findNavController(ThirdFragment.this)
                .navigate(R.id.action_ThirdFragment_to_IntroFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
