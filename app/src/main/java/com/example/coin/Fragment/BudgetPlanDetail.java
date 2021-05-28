package com.example.coin.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.coin.Activity.AddBudget;
import com.example.coin.Activity.MainActivity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetPlanDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetPlanDetail extends Fragment {

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

    ImageView cancel_budget_detail, fix_budget_detail, delete_budget_detail, img_detail_plan;
    TextView name_detail_plan, money_detail_plan, txt_use_get, money_use_get, money_left, time_plan, time_left;
    Button spend_list_detail_plan;
    ProgressBar progress_bar_detail_plan;

    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    LimitLine ll1, ll2;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat checkformat = new SimpleDateFormat("dd/MM/yyyy");
    Date today = calendar.getTime();

    HashMap<String, String> date = new HashMap<String, String>();
    ArrayList lineEntries;
    ArrayList labels;

    // TODO: Rename and change types of parameters
    private Integer ID;
    private String money;
    private String note;
    private String dateStart;
    private String dateEnd;
    private Integer id_gr;
    private Integer id_account;
    private Integer type; // 0: ex | 1: re

    public BudgetPlanDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BudgetPlanDetail.
     * @paramparam1 Parameter 1.
     * @paramparam2 Parameter 2.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetPlanDetail newInstance(Integer ID, String money, String note, String dateStart, String dateEnd, Integer id_gr, Integer id_account, Integer type) {
        BudgetPlanDetail fragment = new BudgetPlanDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, ID);
        args.putString(ARG_PARAM2, money);
        args.putString(ARG_PARAM3, note);
        args.putString(ARG_PARAM4, dateStart);
        args.putString(ARG_PARAM5, dateEnd);
        args.putInt(ARG_PARAM6, id_gr);
        args.putInt(ARG_PARAM7, id_account);
        args.putInt(ARG_PARAM8, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ID = getArguments().getInt(ARG_PARAM1);
            money = getArguments().getString(ARG_PARAM2);
            note = getArguments().getString(ARG_PARAM3);
            dateStart = getArguments().getString(ARG_PARAM4);
            dateEnd = getArguments().getString(ARG_PARAM5);
            id_gr = getArguments().getInt(ARG_PARAM6);
            id_account = getArguments().getInt(ARG_PARAM7);
            type = getArguments().getInt(ARG_PARAM8);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setActionBar(R.layout.plan_detail_actionbar);

        cancel_budget_detail = ((MainActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.cancel_budget_detail);
        cancel_budget_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("message", 1);
                getActivity().startActivity(intent);
            }
        });

        fix_budget_detail = ((MainActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.fix_budget_detail);
        fix_budget_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddBudget.class);
                intent.putExtra("message", 1);
                intent.putExtra("ID", ID);
                intent.putExtra("money", money);
                intent.putExtra("note", note);
                intent.putExtra("dateStart", dateStart);
                intent.putExtra("dateEnd", dateEnd);
                intent.putExtra("id_gr", id_gr);
                getActivity().startActivity(intent);
            }
        });

        delete_budget_detail = ((MainActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.delete_budget_detail);
        delete_budget_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setMessage("Do you agree to delete this budget?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new AppDB(getContext()).DeletePlan(ID);
                        dialog.cancel();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("message", 1);
                        getActivity().startActivity(intent);
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });

        img_detail_plan = view.findViewById(R.id.img_detail_plan);
        img_detail_plan.setImageResource(new AppDB(getContext()).selectImg(id_gr));

        name_detail_plan = view.findViewById(R.id.name_detail_plan);
        name_detail_plan.setText(new AppDB(getContext()).selectName(id_gr));

        money_detail_plan = view.findViewById(R.id.money_detail_plan);
        String symbol = new AppDB(getContext()).select_Symbol(id_account);
        money_detail_plan.setText(money + " " + symbol);

        txt_use_get = view.findViewById(R.id.txt_use_get);
        if (type == 1) {
            txt_use_get.setText("Get");
        }

        money_use_get = view.findViewById(R.id.money_use_get);
        int moneyuse = new AppDB(getContext()).sumMoney(id_gr, id_account, dateStart, dateEnd);
        money_use_get.setText(moneyuse + " " + symbol);

        money_left = view.findViewById(R.id.money_left);
        money_left.setText((Integer.parseInt(money) - moneyuse) + " " + symbol);

        progress_bar_detail_plan = view.findViewById(R.id.progress_bar_detail_plan);

        float percent = ((float) moneyuse / Float.parseFloat(money)) * 100;
        if ((int) percent <= 100) {
            progress_bar_detail_plan.setProgress((int) percent);
        } else {
            progress_bar_detail_plan.setProgress((int) percent - 100);
            progress_bar_detail_plan.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }

        time_plan = view.findViewById(R.id.time_plan);
        time_plan.setText(dateStart + " - " + dateEnd);

        time_left = view.findViewById(R.id.time_left);
        try {
            Date coutime = calendar.getTime();
            Date start = checkformat.parse(dateStart);
            Date end = checkformat.parse(dateEnd);

            Log.d("timetoday", today.toString());
            Log.d("timestart", start.toString());
            Log.d("timeend", end.toString());
            calendar.setTime(start);

            int count = 0;

            while (coutime.compareTo(end) != 0) {
                calendar.add(Calendar.DATE, 1);
                coutime = calendar.getTime();
                count++;
            }

            time_left.setText(count + " days left");

            if (today.after(end)) {

                Log.d("timeExpired", end.toString());
                time_left.setText("Expired");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        lineChart = view.findViewById(R.id.linechart);
        //lineChart.setVisibleYRangeMaximum(Float.parseFloat(money), YAxis.AxisDependency.LEFT);
        getEntries();

        lineDataSet = new LineDataSet(lineEntries, "");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(5);
        lineChart.getLegend().setEnabled(false);

        lineData = new LineData(lineDataSet);
        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value > 0) {
                    return super.getFormattedValue(value);
                } else {
                    return "";
                }
            }
        });

        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setEnabled(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextSize(12);
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

        if (moneyuse > Integer.parseInt(money)) {
            ll1 = new LimitLine(moneyuse, "Over target");
            ll2 = new LimitLine(Integer.parseInt(money), "Target");
            lineChart.getAxisLeft().addLimitLine(ll1);
            lineChart.getAxisLeft().addLimitLine(ll2);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            lineChart.getAxisLeft().setAxisMaxValue(moneyuse);
        } else {
            ll1 = new LimitLine(Integer.parseInt(money), "Target");
            lineChart.getAxisLeft().addLimitLine(ll1);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            lineChart.getAxisLeft().setAxisMaxValue(Float.parseFloat(money));
        }
        lineChart.getAxisLeft().setAxisMinValue(0);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(12);
        xAxis.setLabelCount(labels.size());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(12f);

        spend_list_detail_plan = view.findViewById(R.id.spend_list_detail_plan);
        spend_list_detail_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getEntries() {
        lineEntries = new ArrayList<>();
        labels = new ArrayList<>();

        date = new AppDB(getContext()).getDataChart(id_gr, id_account, dateStart, dateEnd);
        try {
            ArrayList<String> listdate = new ArrayList<String>(date.keySet());
            Date start = checkformat.parse(dateStart);
            Date end = checkformat.parse(dateEnd);


            calendar.setTime(start);
            int count = 0;

            while (start.compareTo(end) <= 0) {
                labels.add(checkformat.format(start));
                boolean check = false;
                for (int i = 0; i < listdate.size(); i++) {
                    if (start.compareTo(checkformat.parse(listdate.get(i))) == 0) {
                        check = true;
                        lineEntries.add(new Entry(count, Float.parseFloat(date.get(listdate.get(i)))));
                        break;
                    }
                }
                if (!check) {
                    lineEntries.add(new Entry(count, (float) 0));
                }

                calendar.add(Calendar.DATE, 1);
                start = calendar.getTime();
                count++;
            }

            for (int i = 1; i < labels.size() - 1; i++) {
                labels.set(i, "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_budget_plan_detail, container, false);
    }
}