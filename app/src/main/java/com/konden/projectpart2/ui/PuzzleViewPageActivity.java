package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterViewPager;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;
import com.konden.projectpart2.fragments.fragment_level.LevelChooseFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelFileTextFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelTrueOrFalseFragment;

import java.util.ArrayList;

public class PuzzleViewPageActivity extends AppCompatActivity {
    ActivityPuzzleViewPageActivtyBinding binding;
    ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list.add(LevelTrueOrFalseFragment.newInstance("ali","sdg"));
        list.add(LevelChooseFragment.newInstance("ali","sdg"));
        list.add(LevelFileTextFragment.newInstance("ali","sdg"));


        AdapterViewPager adapterViewPager = new AdapterViewPager(this,list);
        binding.viewpagerFragment.setAdapter(adapterViewPager);

    }
}