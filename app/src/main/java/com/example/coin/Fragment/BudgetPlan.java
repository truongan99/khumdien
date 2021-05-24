package com.example.coin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coin.Activity.AddBudget;
import com.example.coin.Activity.MainActivity;
import com.example.coin.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetPlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetPlan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView plan_budget_back;
    Button btn_add_plan_budget;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BudgetPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetPlan.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetPlan newInstance(String param1, String param2) {
        BudgetPlan fragment = new BudgetPlan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setActionBar(R.layout.plan_budget_actionbar);

        plan_budget_back = ((MainActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.plan_budget_back);
        plan_budget_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plan nextFrag = new Plan();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_add_plan_budget = view.findViewById(R.id.btn_add_plan_budget);
        btn_add_plan_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddBudget.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_budget_plan, container, false);

    }


}