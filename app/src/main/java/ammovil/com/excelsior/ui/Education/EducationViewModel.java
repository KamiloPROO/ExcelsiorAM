package ammovil.com.excelsior.ui.Education;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EducationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EducationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is education fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}