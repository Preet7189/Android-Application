package com.example.schooladmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.schooladmin.ui.aboutUs.AboutUsFragment;
import com.example.schooladmin.ui.activity.LoginActivity;
import com.example.schooladmin.ui.contactUs.ContactUsFragment;
import com.example.schooladmin.ui.home.HomeFragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    ImageView navDrawer;
    DrawerLayout drawer;
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get the id of the all the required fields
         drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
        navDrawer = (ImageView)findViewById(R.id.navDrawer);
        navDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For open the navigation drawer on click
                drawer.openDrawer(GravityCompat.START);
            }
        });

        //Default set home fragment menu selection and home screen view
        navigationView.getMenu().getItem(0).setChecked(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
        }

    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        //handle menu change selection and select item
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
                case R.id.nav_home:
                //Set home screen title and load home fragment
                txtTitle.setText("School Admin");
                fragmentClass = HomeFragment.class;
                break;

            case R.id.nav_about_us:
                //Set about us screen title and load about us fragment
                txtTitle.setText("About Us");
                fragmentClass = AboutUsFragment.class;
                break;
            case R.id.nav_contact_us:
                //Set contact us screen title and load contact us fragment
                txtTitle.setText("Contact Us");
                fragmentClass = ContactUsFragment.class;
                break;
            case R.id.nav_share:
                //Open Share app dialog
                openShareDialog();
                break;
            case R.id.nav_logout:
                //Open logout dialog
                openLogoutDialog();
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        if(fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }

    private void openShareDialog() {
        //Share intent for sharing the text and choose the application from device
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Let me recommend you this application School Admin";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "School Admin Application");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void openLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                //remove all the intermediate activity
                finishAffinity();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }


}
