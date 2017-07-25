package com.example.dbcent91.materialdesign;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appbar);

        DrawerLayout drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout_main_activity);
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragemnt drawerFragemnt=(NavigationDrawerFragemnt) getSupportFragmentManager().findFragmentById(R.id.app_Drawer_navigation_fragment);
        drawerFragemnt.setUp(R.id.app_Drawer_navigation_fragment,drawerLayout,toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();

        if(id == R.id.next){
            //Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,SubActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
