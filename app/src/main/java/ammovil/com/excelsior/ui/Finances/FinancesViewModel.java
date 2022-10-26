package ammovil.com.excelsior.ui.Finances;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinancesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FinancesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("F I N A N C E S");
    }

    public LiveData<String> getText() {
        return mText;
    }
}