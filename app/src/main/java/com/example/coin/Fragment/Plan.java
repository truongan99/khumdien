package com.example.coin.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.coin.Activity.MainActivity;
import com.example.coin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Plan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Plan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout plan;

    // TODO: Rename and change types of parameters
    private String mParam1 = "";
    private String mParam2 = "";

    public Plan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Plan.
     */
    // TODO: Rename and change types and number of parameters
    public Plan newInstance(String param1, String param2) {
        Plan fragment = new Plan();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBar(R.layout.home_action_bar);

        View root = inflater.inflate(R.layout.fragment_plan, container, false);

        plan = root.findViewById(R.id.plan);
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetPlan nextFrag = new BudgetPlan();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        if (!mParam1.equals("") && !mParam2.equals("")) {
            BudgetPlan nextFrag = new BudgetPlan();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_main, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }

        return root;
    }

}