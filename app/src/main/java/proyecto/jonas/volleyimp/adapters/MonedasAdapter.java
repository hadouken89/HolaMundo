package proyecto.jonas.volleyimp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.models.Moneda;

public class MonedasAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap mHmResponse;

    public MonedasAdapter(Context mContext, HashMap mHmResponse) {
        this.mContext = mContext;
        this.mHmResponse = mHmResponse;
    }

    @Override
    public int getCount() {
        return mHmResponse.size();
    }

    @Override
    public Object getItem(int position) {
        return mHmResponse.get(mHmResponse.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Moneda currentItem = (Moneda) this.getItem( position );

        if(view == null){
            view =((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.moneda_container,null);
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from( this.mContext );
            view = layoutInflater.inflate( R.layout.moneda_container,null );
        }

        TextView tvCompra = view.findViewById(R.id.tvCompra);
        TextView tvVenta = view.findViewById(R.id.tvVenta);
        TextView tvMonedaName = view.findViewById(R.id.tvMonedaName);


        tvMonedaName.setText(currentItem.getMonedaName());
        tvCompra.setText(currentItem.getCompraValue());
        tvVenta.setText(currentItem.getVentaValue());

        return view;
    }
}
