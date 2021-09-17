package educa.movel.com.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class CheckField {
    public boolean isEmpty(String value){
        boolean result = (TextUtils.isEmpty(value) || value.trim().isEmpty() );
        return result;
    }

    public boolean isEmail(String email){
        boolean result = (!isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return result;
    }

}
