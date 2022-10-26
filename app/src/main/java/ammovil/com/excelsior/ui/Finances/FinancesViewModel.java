package ammovil.com.excelsior.ui.Finances;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinancesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FinancesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is finances fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}