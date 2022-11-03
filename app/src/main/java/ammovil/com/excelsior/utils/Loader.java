package ammovil.com.excelsior.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import ammovil.com.excelsior.R;

public class Loader {
    private Boolean isLoading;
    AlertDialog.Builder builder;

    public AlertDialog showLoader(Context context) {
        final View content = LayoutInflater.from(context).inflate(R.layout.item_loading, null);
        builder = new AlertDialog.Builder(context);
        builder.setView(content.getRootView());
        return builder.create();
    }
}
