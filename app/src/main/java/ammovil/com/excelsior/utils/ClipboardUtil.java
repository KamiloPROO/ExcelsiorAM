package ammovil.com.excelsior.utils;

import android.content.ClipData;
import android.content.ClipboardManager;

import androidx.appcompat.app.AppCompatActivity;

public class ClipboardUtil {

    private ClipboardManager mClipboardManager;
    private AppCompatActivity mActivity;

    public ClipboardUtil(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    public boolean copy(String text) {
        if (text == null) {
            return false;
        } else {
            try {
                if (this.mClipboardManager == null) {
                    AppCompatActivity var10002 = this.mActivity;
                    this.mClipboardManager = (ClipboardManager)this.mActivity.getSystemService("clipboard");
                }

                ClipData mClipData = ClipData.newPlainText("label", text);
                this.mClipboardManager.setPrimaryClip(mClipData);
                return true;
            } catch (Exception var3) {
                return false;
            }
        }
    }
}
