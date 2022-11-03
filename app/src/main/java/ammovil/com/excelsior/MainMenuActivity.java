package ammovil.com.excelsior;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import ammovil.com.excelsior.databinding.ActivityMainMenuBinding;
import ammovil.com.excelsior.ui.Investor.InvestorFragment;
import ammovil.com.excelsior.ui.Wallet.WalletFragment;

public class MainMenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainMenuBinding binding;
    String primeraVez = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            if (getIntent().getExtras().get("Primera").toString() != null) {
                primeraVez = getIntent().getExtras().get("Primera").toString();
                Toast.makeText(this, primeraVez, Toast.LENGTH_SHORT).show();
                if (primeraVez == "Si") {
                    Intent i = new Intent(this, MainMenuActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Errpr", Toast.LENGTH_SHORT).show();
        }

        setSupportActionBar(binding.appBarMainMenu.toolbar);
        binding.appBarMainMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_finances, R.id.nav_team, R.id.nav_wallet, R.id.nav_investor, R.id.nav_education, R.id.nav_resources, R.id.nav_support)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_EXCELSIOR", MODE_PRIVATE).edit();

            editor.clear().apply();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}