package proyecto.jonas.volleyimp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import proyecto.jonas.volleyimp.fragments.BilletesFragment;
import proyecto.jonas.volleyimp.fragments.CotizacionFragment;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    private int mNumberOfTabs;

    private static final int COTIZACION_POS = 1;
    private static final int BILLETES_POS = 0;

    public MyPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case BILLETES_POS:
                return new BilletesFragment();
            case COTIZACION_POS:
                return new CotizacionFragment();
            default:
                return new BilletesFragment();
        }
    }

    @Override
    public int getCount() {
        return this.mNumberOfTabs;
    }
}
