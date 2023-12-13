package com.example.odmtavern;

//import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.odmtavern.databinding.FragmentIntroBinding;


public class IntroFragment extends Fragment {
    private FragmentIntroBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentIntroBinding.inflate(inflater, container, false);

        ((MainActivity) requireActivity()).setActionBarTitle("Intro");
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int[] clicks = {1};
        TextView textviewIntro = view.findViewById(R.id.textview_intro), textviewInfo = view.findViewById(R.id.info);
        String name = ((MainActivity) requireActivity()).getName();
        int money = ((MainActivity) requireActivity()).getMoney();
        String info = "Name: " + name + "\nMoney: " + money;
        textviewIntro.setText(getString(R.string.intro1, name));
        textviewInfo.setText(info);
        binding.buttonIntro.setOnClickListener(view1 -> {
            if (clicks[0] == 1) {
                clicks[0]++;
                textviewIntro.setText(getString(R.string.intro2));
            } else if (clicks[0] == 2) {
                clicks[0]++;
                textviewIntro.setText(getString(R.string.intro3, name));
            } else if (clicks[0] == 3) {
                clicks[0]++;
                textviewIntro.setText(getString(R.string.intro4));
            } else {
                NavHostFragment.findNavController(IntroFragment.this)
                        .navigate(R.id.action_IntroFragment_to_GameFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
