package proyecto.jonas.volleyimp.services;

import java.util.HashMap;

public interface IVolleyCallback {
    void onSuccess(HashMap hmResponse);
    void onFailure(Exception e);
}
