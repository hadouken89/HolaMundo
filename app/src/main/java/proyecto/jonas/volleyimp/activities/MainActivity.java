package proyecto.jonas.volleyimp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.adapters.MyPageAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProperties();
        initPageAdapter();
        initListeners();
    }

    private void initProperties() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void initPageAdapter() {
        addTabsLayout();
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager() , tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
    }

    private void addTabsLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.BILLETES)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.DIVISAS)));
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){}

            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });
    }
}
