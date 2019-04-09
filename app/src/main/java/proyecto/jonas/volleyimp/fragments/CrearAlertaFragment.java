package proyecto.jonas.volleyimp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.models.Moneda;

public class CrearAlertaFragment extends Fragment {
    private static final String MONEDA_NAME = "";
    private static final String MONEDA_COMPRA = "";
    private static final String MONEDA_VENTA = "";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CrearAlertaFragment() {
        // Required empty public constructor
    }

    public static CrearAlertaFragment newInstance(Moneda monedaHolder) {
        CrearAlertaFragment fragment = new CrearAlertaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_alerta, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
