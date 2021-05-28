package com.example.coin.Fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coin.Activity.AddTransaction;
import com.example.coin.Activity.MainActivity;
import com.example.coin.Adapter.TransactionApdapter;
import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.Bean.Transaction_Entity;
import com.example.coin.Bean.WalletDetail_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Transaction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Transaction extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btn_add_trans;
    private TextView tv_thuvao;
    private TextView tv_chira;
    private ListView lv_tran;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    List<Transaction_Entity> allTraninMonth = new ArrayList<>();
    List<WalletDetail_Entity> WalletDeinMonth = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String thang;
    private String nam;
    public Transaction() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment transaction_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Transaction newInstance(String param1, String param2) {
        Transaction fragment = new Transaction();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl(view);

        btn_add_trans = view.findViewById(R.id.btn_add_tran_frg);
        btn_add_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTransaction.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            thang = getArguments().getString(ARG_PARAM1);
            nam = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_fragment, container, false);
    }
    private void initWalletinMoth(){

        AppDB db = new AppDB(getContext());
        MainActivity.allWalletDe = db.SelecAllWalletDetail(MainActivity.wallet.getId_vi());
        for(WalletDetail_Entity item : MainActivity.allWalletDe){
            WalletDeinMonth.add(item);
        }
    }
    private void initTranInMonth(){
        AppDB db = new AppDB(getContext());
        for(WalletDetail_Entity item : WalletDeinMonth){
            Transaction_Entity tran = db.selectTranbyID_CTVI(item.getId());
            if(tran!=null){
                String[] date = tran.getNgaygiaodich().split("/");
                if(date[1].equalsIgnoreCase(thang)&&date[2].equalsIgnoreCase(nam)){
                    allTraninMonth.add(tran);
                }
            }
        }
    }
    private void setControl(View view){
        tv_thuvao = view.findViewById(R.id.tv_collect_money);
        tv_chira = view.findViewById(R.id.tv_payout_money);
        lv_tran = view.findViewById(R.id.lv_all_tran_in_month);
        initWalletinMoth();
        initTranInMonth();
        setMoney();
        TransactionApdapter adapter = new TransactionApdapter(getActivity(),allTraninMonth);
        lv_tran.setAdapter(adapter);
    }
    private void setMoney(){
        int thuvao = 0;
        int chira = 0;
        for(Transaction_Entity tran : allTraninMonth){
            for (Group_Detail_Entity grd : MainActivity.allGroupDe){
                for (Group_Entity gr : MainActivity.allGroup){
                    String[] date = tran.getNgaygiaodich().split("/");
                    if(tran.getId_cr_loai() == grd.getId() && grd.getId_loai() == gr.getId() && gr.getLoai().equalsIgnoreCase("0")){
                        chira = chira + tran.getSotien();
                    }
                    else if(tran.getId_cr_loai() == grd.getId() && grd.getId_loai() == gr.getId() && gr.getLoai().equalsIgnoreCase("1")){
                        thuvao = thuvao + tran.getSotien();
                    }
                }
            }
        }
        tv_thuvao.setText(formatter.format(thuvao));
        tv_chira.setText(formatter.format(chira));
    }
}