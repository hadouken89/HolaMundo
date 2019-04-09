package proyecto.jonas.volleyimp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.activities.MonedaNotification;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.services.BilletesService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;
import proyecto.jonas.volleyimp.services.InfoDolarService;

public class BilletesFragment extends Fragment {

    private ListView lvBilletes;
    private Button button;
    private View mView;
    private HashMap mHmMonedas;

    public BilletesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_billetes, container, false);

        initProperties(view);
        return view;
    }

    private void initProperties(View view) {
        lvBilletes = view.findViewById(R.id.lvBilletes);

        mView = view;
        showDivisas();
    }

  // private void showInfoDolar(){
  //     InfoDolarService infoDolarService = new InfoDolarService(mView.getContext(), new IVolleyCallback() {
  //         @Override
  //         public void onSuccess(HashMap hmResponse) {
  //             String holis = "";
  //         }

  //         @Override
  //         public void onFailure(Exception e) {
  //             String holis = "";
  //         }
  //     });

  //     infoDolarService.callRequestData();
  // }

   private void showDivisas() {
       BilletesService htmlDecode = new BilletesService(mView.getContext(), new IVolleyCallback() {
           @Override
           public void onSuccess(HashMap hmResponse) {
               mHmMonedas = hmResponse;
               setMonedaNotificationListener();
               showListViewParams(hmResponse);
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
       monedasAdapter.setmOnItemClickListener(new MonedasAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(Moneda moneda) {
               Intent intent = new Intent( getContext(), MonedaNotification.class);
               intent.putExtra(MonedasConstant.ITEM_MONEDA, moneda );
               startActivity(intent);
           }
       });
       lvBilletes.setAdapter(monedasAdapter);

   }

   private void setMonedaNotificationListener(){
       lvBilletes.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Moneda monedaItem = (Moneda) mHmMonedas.get(mHmMonedas.keySet().toArray()[position]);
               Intent myIntent = new Intent(getActivity(), MonedaNotification.class);
               myIntent.putExtra(MonedasConstant.ITEM_MONEDA, monedaItem);
               startActivity(myIntent);
           }
       });
   }

}
