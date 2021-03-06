package educa.movel.com;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import educa.movel.com.databinding.ActivityMainBinding;
import educa.movel.com.model.CurrentLocation;
import educa.movel.com.utils.InitFirebase;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setLocation();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

           BottomNavigationView navView = findViewById(R.id.nav_view);
           // Passing each menu ID as a set of Ids because each
           // menu should be considered as top level destinations.
           AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                   R.id.navigation_dashboard,  R.id.navigation_home,  R.id.navigation_notifications)
                   .build();
           NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
    //       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
           NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void setLocation() {
        CurrentLocation location = new CurrentLocation("Manhiça", 10, 15);
        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("location")
                .setValue(location);
    }
}