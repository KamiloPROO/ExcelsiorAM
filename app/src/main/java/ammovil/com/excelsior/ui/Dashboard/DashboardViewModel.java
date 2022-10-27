package ammovil.com.excelsior.ui.Dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("W E L C O M E");
    }

    public LiveData<String> getText() {
        return mText;
    }
}