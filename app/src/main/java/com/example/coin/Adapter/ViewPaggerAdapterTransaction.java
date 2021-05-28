package com.example.coin.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coin.Fragment.Transaction;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewPaggerAdapterTransaction extends FragmentStatePagerAdapter {
    SimpleDateFormat df = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
    public ViewPaggerAdapterTransaction( @NotNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        String[] str ={""};
        if(position==10){
            Date ngayhientai = Calendar.getInstance().getTime();
            String now = df.format(ngayhientai);
            str = now.split("/");
            return Transaction.newInstance(str[0],str[1]);
        }
        if(position==11){
            Calendar nextmonth = Calendar.getInstance();
            nextmonth.add(Calendar.MONTH,1);
            String next = df.format(nextmonth.getTime());
            str = next.split("/");
            return Transaction.newInstance(str[0],str[1]);
        }else {
            str=getPageTitle(position).toString().split("/");
            return Transaction.newInstance(str[0],str[1]);
        }
    }

    @Override
    public int getCount() {
        return initMonth().length;
    }
    private String[] initMonth(){
        String[] monthList = new String[12];
        Date c = Calendar.getInstance().getTime();
        Calendar d = Calendar.getInstance();

        monthList[10] = "Now";
        monthList[11] ="Next Month";
        for (int i = 9;i>=0;i--){
            d.add(Calendar.MONTH,-1);
            Date temp = d.getTime();
            monthList[i] = df.format(temp);
        }
        return monthList;
    }
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
