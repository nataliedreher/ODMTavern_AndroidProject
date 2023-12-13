package com.example.odmtavern;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;

//import androidx.viewpager.widget.ViewPager;

import com.example.odmtavern.ChildFragment.PatronFragment;
import com.example.odmtavern.databinding.FragmentGameBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;




public class GameFragment extends Fragment {
    private FragmentGameBinding binding;
    private final List<String> patronList = new ArrayList<>();
    //private final Map<String,Map<String, String>> patronsIds = new HashMap<>();

    private final List<Integer> ppIds = new ArrayList<>();
    private final List<Integer> respIds = new ArrayList<>();
    private final List<Integer> introIds = new ArrayList<>();
    private TextView textviewInfo;
    

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        generatePatronsList();
        createIdsList();
        String title = "Day " + ((MainActivity) requireActivity()).getDay();
        ((MainActivity) requireActivity()).setActionBarTitle(title);
        Fragment childFragment = new PatronFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.childFragmentContainer, childFragment).commit();
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textviewInfo = view.findViewById(R.id.textview_info);
        String name = ((MainActivity) requireActivity()).getName();
        int money = ((MainActivity) requireActivity()).getMoney();
        String info = "Name: " + name + "\nMoney: " + money;
        textviewInfo.setText(info);
    }

    public void updateScreenMoney() {
        String name = ((MainActivity) requireActivity()).getName();
        int money = ((MainActivity) requireActivity()).getMoney();
        String info = "Name: " + name + "\nMoney: " + money;
        textviewInfo.setText(info);
    }

    public int[] getPatronIds() {
        int[] patron = new int[3];
        List<String> patList = patronList;
        Collections.shuffle(patList);
        int index = Arrays.asList(this.getResources().getStringArray(R.array.patrons_array)).indexOf(patList.get(0));
        int ppId = ppIds.get(index);
        int respId = respIds.get(index);
        int introId = introIds.get(index);
        patron[0] = ppId;
        patron[1] = respId;
        patron[2] = introId;
        ((MainActivity) requireActivity()).setCurrentPatron(patList.get(0));
        return patron;
    }


    private void generatePatronsList() {
        final String[] nameKeys = this.getResources().getStringArray(R.array.patrons_array);
        //final String[] pVals = this.getResources().getStringArray(R.array.patrons_player_ids);
        //final String[] rVals = this.getResources().getStringArray(R.array.patrons_resp_ids);
        //Toast.makeText(getActivity().getApplicationContext(), Arrays.toString(pVals), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < nameKeys.length; i++) {
            //String nameKey = nameKeys[i];
            /*
            String pVal = pVals[i];
            String rVal = rVals[i];
            Map<String, String> tempMap = new HashMap<String, String>() {{
                    put("playerId", pVal); put("respId", rVal);
                }};
            patronsIds.put(nameKey, tempMap);

             */
            patronList.add(nameKeys[i]);
        }

    }



    private void createIdsList() {
        ppIds.add(R.array.player_isolde);
        ppIds.add(R.array.player_seraphina);
        ppIds.add(R.array.player_astrid);
        ppIds.add(R.array.player_rowena);
        ppIds.add(R.array.player_elara);
        ppIds.add(R.array.player_gwendolyn);
        ppIds.add(R.array.player_thalia);
        ppIds.add(R.array.player_morrigan);
        ppIds.add(R.array.player_freyja);
        ppIds.add(R.array.player_runa);
        ppIds.add(R.array.player_elowen);
        ppIds.add(R.array.player_alaric);
        ppIds.add(R.array.player_percival);
        ppIds.add(R.array.player_dorian);
        ppIds.add(R.array.player_thaddeus);
        ppIds.add(R.array.player_magnus);
        ppIds.add(R.array.player_cedric);
        ppIds.add(R.array.player_sigurdur);
        ppIds.add(R.array.player_brolin);
        ppIds.add(R.array.player_ember);
        ppIds.add(R.array.player_tenderheart);

        respIds.add(R.array.isolde_resp);
        respIds.add(R.array.seraphina_resp);
        respIds.add(R.array.astrid_resp);
        respIds.add(R.array.rowena_resp);
        respIds.add(R.array.elara_resp);
        respIds.add(R.array.gwendolyn_resp);
        respIds.add(R.array.thalia_resp);
        respIds.add(R.array.morrigan_resp);
        respIds.add(R.array.freyja_resp);
        respIds.add(R.array.runa_resp);
        respIds.add(R.array.elowen_resp);
        respIds.add(R.array.alaric_resp);
        respIds.add(R.array.percival_resp);
        respIds.add(R.array.dorian_resp);
        respIds.add(R.array.thaddeus_resp);
        respIds.add(R.array.magnus_resp);
        respIds.add(R.array.cedric_resp);
        respIds.add(R.array.sigurdur_resp);
        respIds.add(R.array.brolin_resp);
        respIds.add(R.array.ember_resp);
        respIds.add(R.array.tenderheart_resp);

        introIds.add(R.string.isolde_intro);
        introIds.add(R.string.seraphina_intro);
        introIds.add(R.string.astrid_intro);
        introIds.add(R.string.rowena_intro);
        introIds.add(R.string.elara_intro);
        introIds.add(R.string.gwendolyn_intro);
        introIds.add(R.string.thalia_intro);
        introIds.add(R.string.morrigan_intro);
        introIds.add(R.string.freyja_intro);
        introIds.add(R.string.runa_intro);
        introIds.add(R.string.elowen_intro);
        introIds.add(R.string.alaric_intro);
        introIds.add(R.string.percival_intro);
        introIds.add(R.string.dorian_intro);
        introIds.add(R.string.thaddeus_intro);
        introIds.add(R.string.magnus_intro);
        introIds.add(R.string.cedric_intro);
        introIds.add(R.string.sigurdur_intro);
        introIds.add(R.string.brolin_intro);
        introIds.add(R.string.ember_intro);
        introIds.add(R.string.tenderheart_intro);
    }

    public void removePatron(String name) {
        patronList.remove(name);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
