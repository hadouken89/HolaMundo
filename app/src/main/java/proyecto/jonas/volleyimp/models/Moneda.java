package proyecto.jonas.volleyimp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Moneda implements Parcelable {
    private String monedaName;
    private String compraValue;
    private String ventaValue;

    public Moneda(String monedaName, String compraValue, String ventaValue) {
        this.monedaName = monedaName;
        this.compraValue = compraValue;
        this.ventaValue = ventaValue;
    }

    public Moneda() {}

    public Moneda(Parcel in){
        this.monedaName = in.readString();
        this.compraValue = in.readString();
        this.ventaValue = in.readString();
    }

    public String getMonedaName() {
        return monedaName;
    }

    public void setMonedaName(String monedaName) {
        this.monedaName = monedaName;
    }

    public String getCompraValue() {
        return compraValue;
    }

    public void setCompraValue(String compraValue) {
        this.compraValue = compraValue;
    }

    public String getVentaValue() {
        return ventaValue;
    }

    public void setVentaValue(String ventaValue) {
        this.ventaValue = ventaValue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.monedaName);
        dest.writeString(this.compraValue);
        dest.writeString(this.ventaValue);
    }

    public static final Creator<Moneda> CREATOR = new Creator<Moneda>() {
        @Override
        public Moneda createFromParcel(Parcel in) {
            return new Moneda(in);
        }

        @Override
        public Moneda[] newArray(int size) {
            return new Moneda[size];
        }
    };
}
