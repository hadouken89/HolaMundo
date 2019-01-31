package proyecto.jonas.volleyimp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.services.DivisasService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;

public class CotizacionFragment extends Fragment {

    private  View view;
    private ListView lvCotizaciones;

    public CotizacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cotizacion, container, false);


        initProperties();
        return view;
    }

    private void initProperties() {
        lvCotizaciones = view.findViewById(R.id.lvCotizacion);
        showDivisas();
    }

    private void showDivisas() {
        DivisasService htmlDecode = new DivisasService(view.getContext(), new IVolleyCallback() {
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
        MonedasAdapter monedasAdapter = new MonedasAdapter(view.getContext(),hmParams);
        lvCotizaciones.setAdapter(monedasAdapter);
    }


}
