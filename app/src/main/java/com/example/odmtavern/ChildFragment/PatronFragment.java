package com.example.odmtavern.ChildFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.myfirstapp.MainActivity;
import com.example.odmtavern.GameFragment;
import com.example.odmtavern.MainActivity;
import com.example.odmtavern.R;
import com.example.odmtavern.databinding.FragmentPatronBinding;

//import java.util.HashMap;
import java.util.LinkedHashMap;

//import java.util.Map;


public class PatronFragment extends Fragment {
    private FragmentPatronBinding binding;
    private final LinkedHashMap<Integer,String> player = new LinkedHashMap<>();
    private final LinkedHashMap<Integer,String> resp = new LinkedHashMap<>();

    private final String[] patronName = {""};
    private int[] patron = new int[3];
    private final int[] dailyTips = {0};
    final private int[] patronNum = {0};
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPatronBinding.inflate(inflater, container, false);
        Fragment parentFragment = getParentFragment();

        if(parentFragment instanceof GameFragment) {
            patron = ((GameFragment) parentFragment).getPatronIds();
        }
        patronName[0] = ((MainActivity) requireActivity()).getCurrentPatron();

        buildPatronInit(patron[0], patron[1]);

        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup radioGroupIT;
        radioGroupIT = view.findViewById(R.id.radio_group2);
        final int[] index = {0};
        final int[] tip = {0};
        final int[] choice = {0};
        final int[] iter = {0};
        //final boolean[] finished = {false};



        TextView textViewPatron = view.findViewById(R.id.textview_patron);

        textViewPatron.setText(patron[2]);
        if (radioGroupIT != null) {
            radioGroupIT.setOnCheckedChangeListener((radioGroupIT1, checkedButtonId) -> choice[0] = checkedButtonId);
        }

        final RadioButton[] radioButton1 = new RadioButton[1];
        final RadioButton[] radioButton2 = new RadioButton[1];
        final RadioButton[] radioButton3 = new RadioButton[1];
        radioButton1[0] = view.findViewById(R.id.resp1);
        radioButton2[0] = view.findViewById(R.id.resp2);
        radioButton3[0] = view.findViewById(R.id.resp3);
        radioButton1[0].setText(String.valueOf(player.get(index[0])));
        radioButton2[0].setText(String.valueOf(player.get(index[0] + 1)));
        radioButton3[0].setText(String.valueOf(player.get(index[0] + 2)));
        binding.buttonPatron.setOnClickListener(view1 -> {
            String string;
            if (patronNum[0] == -1) {
                int day = ((MainActivity) requireActivity()).getDay();
                if (day == 6) {
                    startGameOver();
                }
                switch (((MainActivity) requireActivity()).getDay()) {
                    case 2: textViewPatron.setText(getString(R.string.day2));
                    case 3: textViewPatron.setText(getString(R.string.day3));
                    case 4: textViewPatron.setText(getString(R.string.day4));
                    case 5: textViewPatron.setText(getString(R.string.day5));
                }
                patronNum[0]++;
            } else if (iter[0] < 2 && patronNum[0] < 3) {
                if (choice[0] == R.id.resp1) {
                    string = "You: " + player.get(index[0]) + "\n" + patronName[0] + ": " + resp.get(index[0]);
                    textViewPatron.setText(string);
                    tip[0] += getTip(index[0]);
                    index[0] += 3;

                } else if (choice[0] == R.id.resp2) {
                    string = "You: " + player.get(index[0] + 1) + "\n" + patronName[0] + ": " + resp.get(index[0] + 1);
                    textViewPatron.setText(string);
                    tip[0] += getTip(index[0] + 1);
                    index[0] += 6;
                } else {
                    string = "You: " + player.get(index[0] + 2) + "\n" + patronName[0] + ": " + resp.get(index[0] + 2);
                    textViewPatron.setText(string);

                    tip[0] += getTip(index[0] + 2);
                    index[0] += 9;
                }
                if (iter[0] == 0) {
                    radioButton1[0].setText(String.valueOf(player.get(index[0])));
                    radioButton2[0].setText(String.valueOf(player.get(index[0] + 1)));
                    radioButton3[0].setText(String.valueOf(player.get(index[0] + 2)));
                } else {
                    radioButton1[0].setVisibility(View.GONE);
                    radioButton2[0].setVisibility(View.GONE);
                    radioButton3[0].setVisibility(View.GONE);
                }
                iter[0]++;
            } else if (iter[0] == 2) {
                string = patronName[0] + " gives you a " + tip[0] + " gold tip then leaves.";
                textViewPatron.setText(string);
                ((MainActivity) requireActivity()).updateMoney(tip[0]);
                dailyTips[0] += tip[0];
                iter[0]++;

                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    ((GameFragment) parentFragment).updateScreenMoney();
                }
                patronNum[0]++;
                tip[0] = 0;
            } else {
                iter[0] = 0;
                index[0] = 0;
                Fragment parentFragment = getParentFragment();
                if (parentFragment instanceof GameFragment) {
                    ((GameFragment) parentFragment).removePatron(patronName[0]);
                    patron = ((GameFragment) parentFragment).getPatronIds();
                }
                patronName[0] = ((MainActivity) requireActivity()).getCurrentPatron();
                buildPatronInit(patron[0], patron[1]);
                radioButton1[0].setVisibility(View.VISIBLE);
                radioButton2[0].setVisibility(View.VISIBLE);
                radioButton3[0].setVisibility(View.VISIBLE);
                radioButton1[0].setText(String.valueOf(player.get(index[0])));
                radioButton2[0].setText(String.valueOf(player.get(index[0] + 1)));
                radioButton3[0].setText(String.valueOf(player.get(index[0] + 2)));
                textViewPatron.setText(patron[2]);
            }
            if (patronNum[0] == 3) {
                int currMoney = ((MainActivity) requireActivity()).getMoney();
                ((MainActivity) requireActivity()).updateDay(false);
                if (((MainActivity) requireActivity()).getDay() == 6 && currMoney < 500) {
                    textViewPatron.setText(getString(R.string.fail_message));
                } else {
                    String debtLeft;
                    debtLeft = (currMoney >= 500) ?
                            "have enough gold to pay your " :
                            "are " + (500 - currMoney) + " gold away from paying your ";
                    textViewPatron.setText(getString(R.string.day_end, dailyTips[0], currMoney, debtLeft));
                }
                patronNum[0] = -1;
                dailyTips[0] = 0;
            }
        });
    }

    private int getTip (int i) {
        LinkedHashMap<Integer,Integer> tips = new LinkedHashMap<>();

        int[] tvals = this.getResources().getIntArray(R.array.isolde_tip);
        for (int j = 0; j < 12; ++j) {
            tips.put(j, tvals[j]);
        }
        if (i > 11) {
            Toast.makeText(requireActivity().getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            return 0;
        } else {
            return tips.get(i);
        }
        //return 1;
    }


    private void buildPatronInit(int partonArrayId, int respArrayId) {
        final String[] pvals = this.getResources().getStringArray(partonArrayId);
        final String[] rvals = this.getResources().getStringArray(respArrayId);
        //int[] tvals = this.getResources().getIntArray(R.array.isolde_tip);
        for (int i = 0; i < 12; ++i) {
            player.put(i, pvals[i]);
            resp.put(i, rvals[i]);
            //tips.put(i, tvals[i]);
        }
    }



    private void startGameOver() {
        ((MainActivity) requireActivity()).updateMoney(0);
        ((MainActivity) requireActivity()).updateDay(true);
        ((MainActivity) requireActivity()).getMoney();

        NavHostFragment.findNavController(PatronFragment.this)
                .navigate(R.id.action_GameFragment_to_FirstFragment);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}