package proyecto.jonas.volleyimp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;

public class MonedaNotification extends AppCompatActivity {

    TextView tvCompra;
    TextView tvVenta;
    TextView tvMoneda;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneda_notification);

        initUI();

        initProperties();
    }

    private void initProperties() {
        Moneda monedaItem = (Moneda) getIntent().getExtras().get(MonedasConstant.ITEM_MONEDA);

        tvCompra.setText(monedaItem.getCompraValue());
        tvVenta.setText(monedaItem.getVentaValue());
        tvMoneda.setText(monedaItem.getMonedaName());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String holis = "";
            }
        });
    }

    private void initUI() {
        tvMoneda = findViewById(R.id.textViewMoneda);
        tvCompra = findViewById(R.id.textViewCompra);
        tvVenta  = findViewById(R.id.textViewVenta);
        tvMoneda = findViewById(R.id.textViewMoneda);
        btnAceptar = findViewById(R.id.btnAceptar);
    }
}
