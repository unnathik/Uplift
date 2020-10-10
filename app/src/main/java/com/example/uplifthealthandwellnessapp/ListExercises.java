package com.example.uplifthealthandwellnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uplifthealthandwellnessapp.Adapter.RecyclerViewAdapter;
import com.example.uplifthealthandwellnessapp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        exerciseList.add(new Exercise(R.drawable.bow_pose, "Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.bridge_pose, "Bridge Pose"));
        exerciseList.add(new Exercise(R.drawable.camel_pose, "Camel Pose"));
        exerciseList.add(new Exercise(R.drawable.catcow_stretches, "Cat-Cow Stretches"));
        exerciseList.add(new Exercise(R.drawable.childs_pose, "Child's Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downwardfacingdog_split, "Downward-Facing Dog - Split"));
        exerciseList.add(new Exercise(R.drawable.downwardfacingdogpose, "Downward-Facing Dog Pose"));
        exerciseList.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.extendedsideangle_utthitaparvakonasana, "Extended Side Angle Pose"));
        exerciseList.add(new Exercise(R.drawable.garland_pose, "Garland Pose"));
        exerciseList.add(new Exercise(R.drawable.half_moon_yoga_pose, "Half Moon Pose"));
        exerciseList.add(new Exercise(R.drawable.low_lunge, "Low Lunge Pose"));
        exerciseList.add(new Exercise(R.drawable.pelvic_tilts, "Pelvic Tilts"));
        exerciseList.add(new Exercise(R.drawable.pyramid_pose, "Pyramid Pose"));
        exerciseList.add(new Exercise(R.drawable.plankpose, "Plank Pose"));
        exerciseList.add(new Exercise(R.drawable.raised_hands_pose, "Raised Hands Pose"));
        exerciseList.add(new Exercise(R.drawable.straight_leg_lunge, "Straight Leg Lunge"));
        exerciseList.add(new Exercise(R.drawable.triangle_pose, "Triangle Pose"));
        exerciseList.add(new Exercise(R.drawable.warriori_virabhadrasanai, "Warrior I Pose"));
        exerciseList.add(new Exercise(R.drawable.warriorii_virabhadrasanaii, "Warrior II Pose"));

    }
}