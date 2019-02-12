package proyecto.jonas.volleyimp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.constants.MonedasConstant;
import proyecto.jonas.volleyimp.models.Moneda;

public class MonedaNotification extends AppCompatActivity {

    TextView tvCompra;
    TextView tvVenta;
    TextView tvMoneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneda_notification);

        initialize();


    }

    private void initialize() {
        Moneda monedaItem = (Moneda) getIntent().getExtras().get(MonedasConstant.ITEM_MONEDA);

        tvMoneda = findViewById(R.id.textViewMoneda);
        tvVenta  = findViewById(R.id.textViewVenta);
        tvMoneda = findViewById(R.id.textViewMoneda);


    }
}
