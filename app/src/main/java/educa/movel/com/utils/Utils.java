package educa.movel.com.utils;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
   public static String emptyImage = "https://firebasestorage.googleapis.com/v0/b/events-18105.appspot.com/o/empty.jpg?alt=media&token=07e04111-e92b-4433-ab26-b8d91df82a58";
   public static String app_version = "1.1.5";
   public static String play_store_link = "https://play.google.com/store/apps/details?id=educa.movel.com";
   public static final int REGISTER_CODE = 20;
   public static final String api_url = "https://api-educa-movel.herokuapp.com/quiz";


   public static String getCutStr(String str , int position) {
      str = str.trim();
      if (str != null && ! str.isEmpty()){

         if (str.length() > position){
            return  str.substring(0 , position ).trim()+"..";
         }else {
            return str;
         }

      }
      return str;
   }

   public static final String getDate() {
      Date date = new Date();
      SimpleDateFormat format  =  new SimpleDateFormat("dd/MM/yyyy");
      String data = format.format(date);
      return data;
   }

   public interface AddLifecycleCallbackListener {
      void addLifeCycleCallBack(YouTubePlayerView youTubePlayerView);
   }

}