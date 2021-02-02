package com.example.androidfundamentals.week6;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidfundamentals.R;
import com.example.androidfundamentals.week6.AboutFragment;
import com.example.androidfundamentals.week6.ContactFragment;
import com.example.androidfundamentals.week6.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "NavDrawerActivity";

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavController navController;
    private NavigationView navigationView;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        drawer = findViewById(R.id.drawer_layout);
        navController = Navigation.findNavController(this, R.id.main_content);
        // Use this if <FragmentContainerView> is used in xml instead of <fragment>
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.main_content);
//        assert navHostFragment != null;
//        navController = navHostFragment.getNavController();
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the drawer toggle (hamburger button) with animation and state listener
        toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(getTopLevelDestinationIds())
                .setOpenableLayout(drawer)
                .build();

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_home) {
                Toast.makeText(NavigationDrawerActivity.this, "Home fragment selected", Toast.LENGTH_SHORT).show();
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.getMenu().findItem(R.id.nav_share).setOnMenuItemClickListener(menuItem -> {
            Toast.makeText(NavigationDrawerActivity.this, "Share some content", Toast.LENGTH_SHORT).show();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private int[] getTopLevelDestinationIds() {
        return new int[]{R.id.nav_home, R.id.nav_about_us, R.id.nav_contact, R.id.nav_share};
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_share) {
            Toast.makeText(this, "Share some content", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Fragment homeFragment = new HomeFragment();
                show(homeFragment);
            } else if (id == R.id.nav_about_us) {
                Fragment aboutFragment = new AboutFragment();
                show(aboutFragment);
            } else if (id == R.id.nav_contact) {
                Fragment contactFragment = new ContactFragment();
                show(contactFragment);
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    private void show(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            // TODO: Change the container type to the correct type(static fragment tag is not supported) to work correctly
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        } else {
            Log.w(TAG, "show: invalid fragment to show");
        }

        drawer.closeDrawer(GravityCompat.START);
    }
}