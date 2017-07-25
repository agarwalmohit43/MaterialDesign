package com.example.dbcent91.materialdesign;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragemnt extends Fragment implements CustomAdapter.onClickListener {


    private RecyclerView recyclerView;

    public static final String PREF_FILE_NAME="prefOne";
    public static final String KEY_DRAWER_EXISTS="drawer_exists";

    private ActionBarDrawerToggle mactionBarDrawerToggle;

    private DrawerLayout mdrawerLayout;

    private boolean mDrawerExists,mFromSavedInstanceState;

    View conView;

    private CustomAdapter customAdapter;


    public NavigationDrawerFragemnt() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mDrawerExists=Boolean.valueOf(readToPreference(getActivity(),KEY_DRAWER_EXISTS,"false"));

        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_navigation_drawer_fragemnt, container, false);
        recyclerView =(RecyclerView) layout.findViewById(R.id.recyclerViewDrawer);


        customAdapter=new CustomAdapter(getActivity(),getData());
        customAdapter.setClickListener(this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return layout;
    }

    public static List<Inforamtion> getData(){
        List<Inforamtion> data=new ArrayList<>();

        int[] icons={R.drawable.memes2,R.drawable.memes,R.drawable.memes2,R.drawable.memes2};
       // int[] icons={R.drawable.ic_drawer,R.drawable.ic_drawer,R.drawable.ic_drawer,R.drawable.ic_drawer};
        String[] names={"Smile","Laugh","Angry","Sad"};

        for(int i=0;i<100;i++)
        {
            Inforamtion obj=new Inforamtion();
            obj.iconId=icons[i%icons.length];
            obj.title=names[i%names.length];
            data.add(obj);
        }
        return data;
    }

    public void setUp(int fragemntId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        conView=getActivity().findViewById(fragemntId);
        mdrawerLayout=drawerLayout;
        mactionBarDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mDrawerExists){
                    mDrawerExists=true;
                    saveToPreference(getActivity(),KEY_DRAWER_EXISTS,mDrawerExists+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }

            }
        };


        if(!mDrawerExists && !mFromSavedInstanceState){
            mdrawerLayout.openDrawer(conView);
        }

        mdrawerLayout.setDrawerListener(mactionBarDrawerToggle);

        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mactionBarDrawerToggle.syncState();
            }
        });


    }

    public static void saveToPreference(Context context,String preferenceName,String preferenceValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();

    }


    public static String readToPreference(Context context,String preferenceName,String preferenceValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getString(preferenceName,preferenceValue);
    }

    @Override
    public void itemclicked(View view, int position) {

        switch (position){
            case 0: getActivity().startActivity(new Intent(getActivity(),SubActivity.class));
                break;
            case 1:
                Toast.makeText(getActivity(),"Clicked At pos -> "+position,Toast.LENGTH_SHORT).show();
               /* Snackbar snackbar = Snackbar
                        .make(, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

                snackbar.show();*/
                break;
            default:
                //Toast.makeText(getActivity(),"Clicked At pos -> "+position,Toast.LENGTH_SHORT).show();
                customAdapter.deleteData(position);
        }
    }
}
