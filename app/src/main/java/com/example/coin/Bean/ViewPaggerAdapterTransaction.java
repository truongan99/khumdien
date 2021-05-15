package com.example.coin.Bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coin.Transaction;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewPaggerAdapterTransaction extends FragmentStatePagerAdapter {
    public ViewPaggerAdapterTransaction(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return new Transaction();
    }

    @Override
    public int getCount() {
        return 12;
    }
    private String[] initMonth(){
        String[] monthList = new String[12];
        Date c = Calendar.getInstance().getTime();
        Calendar d = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        monthList[10] = "Now";
        monthList[11] ="Next Month";
        for (int i = 9;i>=0;i--){
            d.add(Calendar.MONTH,-1);
            Date temp = d.getTime();
            monthList[i] = df.format(temp);
        }
        return monthList;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] monthList = initMonth();
        switch (position){
            case 0:
                return monthList[0];
            case 1:
                return monthList[1];
            case 2:
                return monthList[2];

            case 3:
                return monthList[3];

            case 4:
                return monthList[4];

            case 5:
                return monthList[5];

            case 6:
                return monthList[6];

            case 7:
                return monthList[7];

            case 8:
                return monthList[8];

            case 9:
                return monthList[9];

            case 10:
                return monthList[10];

            case 11:
                return monthList[11];
            default:return "";

        }

    }
}
