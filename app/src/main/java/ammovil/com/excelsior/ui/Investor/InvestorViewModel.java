package ammovil.com.excelsior.ui.Investor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InvestorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InvestorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is investor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}