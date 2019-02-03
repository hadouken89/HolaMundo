package proyecto.jonas.volleyimp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.adapters.MyPageAdapter;
import proyecto.jonas.volleyimp.services.CotizacionService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;
import proyecto.jonas.volleyimp.services.BilletesService;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ListView lvMonedas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProperties();
    }

    private void initProperties() {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Billetes"));
        tabLayout.addTab(tabLayout.newTab().setText("Divisas"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager() , tabLayout.getTabCount());

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              //  Toast.makeText(MainActivity.this, "Selected -> "+tab.getText(), Toast.LENGTH_SHORT).show();
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            //    Toast.makeText(MainActivity.this, "Unselected -> "+tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
              //  Toast.makeText(MainActivity.this, "Reselected -> "+tab.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDivisas() {
        CotizacionService htmlDecode = new CotizacionService(this, new IVolleyCallback() {
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

    private void showBilletes() {
        BilletesService htmlDecode = new BilletesService(this, new IVolleyCallback() {
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
      //  mMonedaList =  hmParams;//fixme
        MonedasAdapter monedasAdapter = new MonedasAdapter(this,hmParams);
        lvMonedas.setAdapter(monedasAdapter);
    }

}
