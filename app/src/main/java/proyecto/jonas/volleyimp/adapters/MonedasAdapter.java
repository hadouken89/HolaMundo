package proyecto.jonas.volleyimp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.models.Moneda;
import proyecto.jonas.volleyimp.utils.Utils;

public class MonedasAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap hmMonedas;
    private OnItemClickListener mOnItemClickListener;


    public MonedasAdapter(Context mContext, HashMap hmMonedas) {
        this.mContext = mContext;
        this.hmMonedas = hmMonedas;
    }

    @Override
    public int getCount() {
        return hmMonedas.size();
    }

    @Override
    public Object getItem(int position) {
        return hmMonedas.get(hmMonedas.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Moneda currentItem = (Moneda) this.getItem( position );
        final int mPosition = position;

        if(view == null){
            view =((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.moneda_container2,null);
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from( this.mContext );
            view = layoutInflater.inflate( R.layout.moneda_container2,null );
        }

        TextView tvCompra = view.findViewById(R.id.tvCompra);
        TextView tvVenta = view.findViewById(R.id.tvVenta);
        TextView tvMonedaName = view.findViewById(R.id.tvMonedaName);
        TextView tvUnidadMonetaria = view.findViewById(R.id.unidadMonetaria);
        ImageView monedaImg = (ImageView) view.findViewById(R.id.imageView);
        ImageButton buttonAlerta  = view.findViewById(R.id.buttonAlerta);
        monedaImg.setImageResource(Utils.getMonedaImage(currentItem.getIdMoneda()) );

        tvMonedaName.setText(currentItem.getMonedaName());
        tvCompra.setText(currentItem.getCompraValue());
        tvVenta.setText(currentItem.getVentaValue());
        tvUnidadMonetaria.setText(currentItem.getUnidadMonedaria());

        buttonAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick((Moneda) hmMonedas.get(hmMonedas.keySet().toArray()[mPosition]));
                }
            }
        });
        return view;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Moneda moneda);
    }

}
