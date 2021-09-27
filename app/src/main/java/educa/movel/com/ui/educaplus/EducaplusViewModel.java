package educa.movel.com.ui.educaplus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EducaplusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EducaplusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}