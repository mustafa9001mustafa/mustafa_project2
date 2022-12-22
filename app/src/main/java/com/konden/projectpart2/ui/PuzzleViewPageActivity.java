package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;

public class PuzzleViewPageActivity extends AppCompatActivity {
    ActivityPuzzleViewPageActivtyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}