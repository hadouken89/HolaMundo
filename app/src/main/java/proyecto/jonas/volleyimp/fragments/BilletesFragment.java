package proyecto.jonas.volleyimp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.services.DivisasService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;
import proyecto.jonas.volleyimp.services.InfoDolarService;

public class BilletesFragment extends Fragment {

    private ListView lvBilletes;
    private Button button;
    private View mView;

    public BilletesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_billetes, container, false);

        initProperties(view);
        return view;
    }

    private void initProperties(View view) {
        lvBilletes = view.findViewById(R.id.lvBilletes);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDolar();
            }
        });


        mView = view;
        showDivisas();

    }

    private void showInfoDolar(){
        InfoDolarService infoDolarService = new InfoDolarService(mView.getContext(), new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap hmResponse) {
                String holis = "";
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });

        infoDolarService.callRequestData();
    }

   private void showDivisas() {
       DivisasService htmlDecode = new DivisasService(mView.getContext(), new IVolleyCallback() {
           @Override
           public void onSuccess(HashMap jsonString) {
               showListViewParams(jsonString);
           }

           @Override
           public void onFailure(Exception e) {
               String holis = "";
           }
       });
       htmlDecode.callRequestData();
   }

   private void showListViewParams(HashMap hmParams) {
       MonedasAdapter monedasAdapter = new MonedasAdapter(mView.getContext(),hmParams);
       lvBilletes.setAdapter(monedasAdapter);
   }

}
