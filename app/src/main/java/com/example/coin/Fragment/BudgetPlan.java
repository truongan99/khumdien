package com.example.coin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coin.Activity.AddBudget;
import com.example.coin.Activity.Login;
import com.example.coin.Activity.MainActivity;
import com.example.coin.Adapter.PlanAdapter;
import com.example.coin.Bean.Plan_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    TextView plan_budget_back, header_list_re, header_list_ex, txt_add_plan;
    ListView lv_budget_plan_re, lv_budget_plan_ex;
    Button btn_add_plan_budget;

    ArrayList<Plan_Entity> plan_re = new ArrayList<>();
    ArrayList<Plan_Entity> plan_ex = new ArrayList<>();
    PlanAdapter adapter;

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


        txt_add_plan = view.findViewById(R.id.txt_add_plan);

        header_list_re = view.findViewById(R.id.header_list_re);
        header_list_re.setVisibility(View.INVISIBLE);

        header_list_ex = view.findViewById(R.id.header_list_ex);
        header_list_ex.setVisibility(View.INVISIBLE);

        lv_budget_plan_re = view.findViewById(R.id.lv_budget_plan_re);
        plan_re = new AppDB(getContext()).selectPlanRe();
        if (!plan_re.isEmpty()) {
            header_list_re.setVisibility(View.VISIBLE);
            txt_add_plan.setVisibility(View.INVISIBLE);

            adapter = new PlanAdapter(getContext(), R.layout.plan_items_listview, plan_re, 1, Login.acc_login.getID());
            lv_budget_plan_re.setAdapter(adapter);
        }

        lv_budget_plan_re.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BudgetPlanDetail nextFrag = new BudgetPlanDetail().newInstance(plan_re.get(position).getID(),
                        plan_re.get(position).getMoney(),
                        plan_re.get(position).getNote(),
                        plan_re.get(position).getDateStart(),
                        plan_re.get(position).getDateEnd(),
                        plan_re.get(position).getId_gr(),
                        plan_re.get(position).getId_account(), 1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        lv_budget_plan_ex = view.findViewById(R.id.lv_budget_plan_ex);
        plan_ex = new AppDB(getContext()).selectPlanEx();
        if (!plan_ex.isEmpty()) {
            header_list_ex.setVisibility(View.VISIBLE);
            txt_add_plan.setVisibility(View.INVISIBLE);

            adapter = new PlanAdapter(getContext(), R.layout.plan_items_listview, plan_ex, 0, Login.acc_login.getID());
            lv_budget_plan_ex.setAdapter(adapter);
        }

        lv_budget_plan_ex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BudgetPlanDetail nextFrag = new BudgetPlanDetail().newInstance(plan_ex.get(position).getID(),
                        plan_ex.get(position).getMoney(),
                        plan_ex.get(position).getNote(),
                        plan_ex.get(position).getDateStart(),
                        plan_ex.get(position).getDateEnd(),
                        plan_ex.get(position).getId_gr(),
                        plan_ex.get(position).getId_account(), 0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        ListUtils.setDynamicHeight(lv_budget_plan_re);
        ListUtils.setDynamicHeight(lv_budget_plan_ex);

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

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

}