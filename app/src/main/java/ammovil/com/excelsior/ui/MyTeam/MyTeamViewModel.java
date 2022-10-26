package ammovil.com.excelsior.ui.MyTeam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyTeamViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyTeamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my team fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}