package com.example.coin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coin.Activity.AddBudget;
import com.example.coin.Activity.Login;
import com.example.coin.Activity.MainActivity;
import com.example.coin.Activity.SelectGroup;
import com.example.coin.Bean.Plan_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@linkFragment} subclass.
 * Use the {@linkAddBudgetPlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBudgetPlan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "param7";
    private static final String ARG_PARAM8 = "param8";

    public static final int REQUEST_GROUP = 111;
    public static final int REQUEST_AVT = 110;

    ImageView cancel_add_budget, img_select_gr_add_bud;
    TextView btn_save_bud;
    private RadioGroup rdg_type_add_bud;
    EditText edt_day_add_bud, edt_money_add_bud, edt_note_add_bud, edt_select_gr_add_bud;

    // TODO: Rename and change types of parameters
    private String money = "";
    private String selected = "";
    private String note = "";
    private String dateStart = "";
    private String dateEnd = "";
    private int type = 0;  // 1: this week, 2: this month, 3: this year, 4: custom
    int id_gr = -1;
    int avt_gr = -1;

    public AddBudgetPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBudgetPlan.
     */
    // TODO: Rename and change types and number of parameters
    public AddBudgetPlan dataTransfer(String param1, String param2, String param3, String param4, String param5, Integer param6, Integer param7, Integer param8) {
        AddBudgetPlan fragment = new AddBudgetPlan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putInt(ARG_PARAM6, param6);
        args.putInt(ARG_PARAM7, param7);
        args.putInt(ARG_PARAM8, param8);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            money = getArguments().getString(ARG_PARAM1);
            selected = getArguments().getString(ARG_PARAM2);
            note = getArguments().getString(ARG_PARAM3);
            dateStart = getArguments().getString(ARG_PARAM4);
            dateEnd = getArguments().getString(ARG_PARAM5);
            type = getArguments().getInt(ARG_PARAM6);
            id_gr = getArguments().getInt(ARG_PARAM7);
            avt_gr = getArguments().getInt(ARG_PARAM8);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AddBudget) getActivity()).setActionBar(R.layout.add_budget_actionbar);

        edt_money_add_bud = view.findViewById(R.id.edt_money_add_bud);
        if (!money.equals("")) {
            edt_money_add_bud.setText(money);
        }

        rdg_type_add_bud = view.findViewById(R.id.rdg_type_add_bud);
        img_select_gr_add_bud = view.findViewById(R.id.img_select_gr_add_bud);
        if (avt_gr != -1) {
            img_select_gr_add_bud.setImageResource(avt_gr);

        }

        edt_note_add_bud = view.findViewById(R.id.edt_note_add_bud);
        if (!note.equals("")) {
            edt_note_add_bud.setText(note);
        }

        edt_select_gr_add_bud = view.findViewById(R.id.edt_select_gr_add_bud);
        if (!selected.equals("")) {
            edt_select_gr_add_bud.setText(selected);
        }
        edt_select_gr_add_bud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdg_type_add_bud.getCheckedRadioButtonId() == R.id.rd_chi_add_bud) {
                    Intent intent = new Intent(((AddBudget) getActivity()).getApplicationContext(), SelectGroup.class);
                    intent.putExtra("TYPE_GR", "0");
                    startActivityForResult(intent, REQUEST_GROUP);
                }
                if (rdg_type_add_bud.getCheckedRadioButtonId() == R.id.rd_thu_add_bud) {
                    Intent intent = new Intent(((AddBudget) getActivity()).getApplicationContext(), SelectGroup.class);
                    intent.putExtra("TYPE_GR", "1");
                    startActivityForResult(intent, REQUEST_GROUP);
                }
            }
        });

        cancel_add_budget = ((AddBudget) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.cancel_add_budget);
        cancel_add_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        edt_day_add_bud = view.findViewById(R.id.edt_day_add_bud);
        if (type == 1) {
            edt_day_add_bud.setText("This week");
        }
        if (type == 2) {
            edt_day_add_bud.setText("This month");
        }
        if (type == 3) {
            edt_day_add_bud.setText("This year");
        }
        if (type == 4) {
            edt_day_add_bud.setText(dateStart + " - " + dateEnd);
        }
        edt_day_add_bud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBudgetPlanTime nextFrag = new AddBudgetPlanTime().dataTransfer(edt_money_add_bud.getText().toString(), edt_select_gr_add_bud.getText().toString(), edt_note_add_bud.getText().toString(), dateStart, dateEnd, type, id_gr, avt_gr);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_save_bud = ((AddBudget) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.btn_save_bud);
        btn_save_bud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_money_add_bud.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Money can't empty", Toast.LENGTH_LONG).show();
                }
                if (edt_day_add_bud.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Time apply can't empty", Toast.LENGTH_LONG).show();
                }
                if (edt_select_gr_add_bud.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Must select group", Toast.LENGTH_LONG).show();
                }
                if (!edt_money_add_bud.getText().toString().equals("") && !edt_day_add_bud.getText().toString().equals("") && !edt_select_gr_add_bud.getText().toString().equals("")) {
                    try {
                        AppDB db = new AppDB(getContext());
                        Plan_Entity plan = new Plan_Entity(money, note, dateStart, dateEnd, id_gr, Login.acc_login.getID());
                        db.InsertPlan(plan);
                        Toast.makeText(getContext(), "Insert complete!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("message", 1);
                        getActivity().startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_budget_plan, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_AVT && data != null) {
//            avt_gr =  data.getIntExtra("GRD_IMG",-1);
//            img_select_gr_add_bud.setImageResource(avt_gr);
//        }
        if (requestCode == REQUEST_GROUP && data != null) {
            edt_select_gr_add_bud.setText(data.getStringExtra("GR_NAME"));
            id_gr = data.getIntExtra("GR_ID", -1);
            avt_gr = data.getIntExtra("GR_IMG", -1);
            img_select_gr_add_bud.setImageResource(avt_gr);
        }
    }
}