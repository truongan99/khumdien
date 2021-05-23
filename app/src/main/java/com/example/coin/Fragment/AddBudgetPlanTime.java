package com.example.coin.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.coin.Activity.AddBudget;
import com.example.coin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@linkFragment} subclass.
 * Use the {@linkAddBudgetPlanTime#dataTransfer} factory method to
 * create an instance of this fragment.
 */
public class AddBudgetPlanTime extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    ImageView cancel_time_app;
    TextView edt_time_week, edt_time_month, edt_time_year, edt_time_custom;
    LinearLayout time_custom, time_week, time_month, time_year;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat checkformat = new SimpleDateFormat("dd/MM/yyyy");
    String today, endWeek, endMonth, endYear, cusStart, cusEnd;
    DatePickerDialog picker;
    boolean check = false;

    // TODO: Rename and change types of parameters
    private String money = "";
    private String selected = "";
    private String note = "";
    private String dateStart = "";
    private String dateEnd = "";
    private int type = 4;

    public AddBudgetPlanTime() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBudgetPlanTime.
     */
    // TODO: Rename and change types and number of parameters
    public AddBudgetPlanTime dataTransfer(String param1, String param2, String param3, String param4, String param5, Integer param6) {
        AddBudgetPlanTime fragment = new AddBudgetPlanTime();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putInt(ARG_PARAM6, param6);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AddBudget) getActivity()).setActionBar(R.layout.add_budget_time_actionbar);

        Log.d("tranferrrtime", "money " +money);
        Log.d("tranferrrtime", "selected "+selected);
        Log.d("tranferrrtime", "note "+note);
        Log.d("tranferrrtime", "dateStart "+dateStart);
        Log.d("tranferrrtime", "dateEnd "+dateEnd);
        Log.d("tranferrrtime", "type "+type);

        View view = inflater.inflate(R.layout.fragment_add_budget_plan_time, container, false);

        cancel_time_app = ((AddBudget) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.cancel_time_app);
        cancel_time_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBudgetPlan nextFrag = new AddBudgetPlan().dataTransfer(money, selected, note, dateStart, dateEnd, type);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        //today
        today = checkformat.format(calendar.getTime());
        //end week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DATE, 6);
        endWeek = checkformat.format(calendar.getTime());
        //end month
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        endMonth = checkformat.format(calendar.getTime());
        //end year
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.DATE, 31);
        calendar.set(Calendar.MONTH, 11);
        endYear = checkformat.format(calendar.getTime());

        edt_time_week = view.findViewById(R.id.edt_time_week);
        edt_time_week.setText(today + " - " + endWeek);

        edt_time_month = view.findViewById(R.id.edt_time_month);
        edt_time_month.setText(today + " - " + endMonth);

        edt_time_year = view.findViewById(R.id.edt_time_year);
        edt_time_year.setText(today + " - " + endYear);

        time_week = view.findViewById(R.id.time_week);
        time_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBudgetPlan nextFrag = new AddBudgetPlan().dataTransfer(money, selected, note, today, endWeek, 1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        time_month = view.findViewById(R.id.time_month);
        time_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBudgetPlan nextFrag = new AddBudgetPlan().dataTransfer(money, selected, note, today, endMonth, 2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        time_year = view.findViewById(R.id.time_year);
        time_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBudgetPlan nextFrag = new AddBudgetPlan().dataTransfer(money, selected, note, today, endYear, 3);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        time_custom = view.findViewById(R.id.time_custom);
        time_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });

        return view;
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_date_pick, null);
        final EditText from_date = (EditText) alertLayout.findViewById(R.id.from_date);
        final EditText to_date = (EditText) alertLayout.findViewById(R.id.to_date);

        from_date.setInputType(InputType.TYPE_NULL);
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                try {
                                    cusStart = checkformat.format(checkformat.parse(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                from_date.setText(cusStart);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        to_date.setInputType(InputType.TYPE_NULL);
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                try {
                                    cusEnd = checkformat.format(checkformat.parse(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Date strDate = checkformat.parse(cusStart);
                                    Date afDate = checkformat.parse(cusEnd);
                                    if (strDate.before(afDate)) {
                                        check = true;
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                to_date.setText(cusEnd);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Pick Time");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                if (check) {
                    edt_time_custom.setText(cusStart + " - " + cusEnd);
                    AddBudgetPlan nextFrag = new AddBudgetPlan().dataTransfer(money, selected, note, cusStart, cusEnd, 4);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.add_budget_frame, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(getContext(), "End day must after start day", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}