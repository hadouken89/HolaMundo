package proyecto.jonas.volleyimp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.activities.MonedaNotification;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.services.CotizacionService;
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
        CotizacionService cotizacionService = new CotizacionService(view.getContext(), new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap jsonString) {
                showListViewParams(jsonString);
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });
        cotizacionService.callRequestData();
    }

    private void showListViewParams(HashMap hmParams) {
        MonedasAdapter monedasAdapter = new MonedasAdapter(view.getContext(),hmParams);
        monedasAdapter.setmOnItemClickListener(new MonedasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Moneda moneda) {
                Intent intent = new Intent( getContext()  , MonedaNotification.class);
                intent.putExtra(MonedasConstant.ITEM_MONEDA, moneda );
                startActivity(intent);
            }
        });
        lvCotizaciones.setAdapter(monedasAdapter);
    }


}
