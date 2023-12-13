package com.example.odmtavern;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

//import android.view.View;

//import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.odmtavern.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    final int[] pronoun = {0};
    final int[] age = {0};
    final int[] day = {1};
    String name = "";
    final int[] money = {0};
    public final String[] currentPatron = {""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        com.example.odmtavern.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void updateName(String nm) {
        name = nm;
    }

    public String getName() {
        return name;
    }

    public void updatePronoun(int pn) {
        switch (pn) {
            case 0:
                pronoun[0] = 0;
                break;
            case 1:
                pronoun[0] = 1;
                break;
            case 2:
                pronoun[0] = 2;
                break;
        }
    }

    public int getPronoun() {
        return pronoun[0];
    }

    public void updateAge(int a) {
        switch (a) {
            case 0:
                age[0] = 0;
                break;
            case 1:
                age[0] = 1;
                break;
            case 2:
                age[0] = 2;
                break;
            case 3:
                age[0] = 3;
                break;
            case 4:
                age[0] = 4;
                break;
        }
    }

    public int getAge() {
        return age[0];
    }

    public void updateMoney(int tip) {
        money[0] += tip;
    }

    public int getMoney() {
        return money[0];
    }

    public void setActionBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
    public int getDay() {
        return day[0];
    }
    public void updateDay(boolean done) {
        if (done) {
            day[0] = 1;
        } else {
            day[0]++;

        }
    }
    public void setCurrentPatron(String name) {
        currentPatron[0] = name;
    }
    public String getCurrentPatron() {
        return currentPatron[0];
    }
}