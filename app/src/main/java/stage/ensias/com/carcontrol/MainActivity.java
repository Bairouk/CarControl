package stage.ensias.com.carcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //write on firebase-------------------------------------------------------------------

        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference infoCars = fb.child("cars_info/Cars");



        //carsInfo NewCar = new carsInfo("orsinagh","47356-45-54",1,0,"ersdda sff","dfhghgfbv ertf ewr  ewre  erwer ","fdsjfidf s``of edeew fdf peorjdsafjsdf");
        //infoCars.child("47356-45-54").setValue(NewCar);




        //Firebase recall --------------------------------------------------------------------


        infoCars.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {

                // do something with data
                List<String> carIds = new ArrayList<>();

                for (DataSnapshot child : data.getChildren()) {

                    carsInfo CInfo = child.getValue(carsInfo.class);
                    if (CInfo.done==0 && CInfo.inprogress==0){
                        carIds.add(CInfo.carId);
                    }
                }

                // spinner to choose which car -------------------------------------------------------------------

                Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                ArrayAdapter<String> langAdapter2 = new ArrayAdapter<String>(MainActivity.this ,R.layout.spinner_text, carIds );
                langAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                spinner2.setAdapter(langAdapter2);

                //----------------------------------------------------------------------------------------------


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // report/log the error

            }
        });

        // Spinner to fill the ligne ------------------------------------------------------------------

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        String[] years = {"1","2","3"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(this ,R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner.setAdapter(langAdapter);

        //--------------------------------------------------------------------------------------------













    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //OnClick for the start button--------------------

    public void start_act(View view) {



        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.info_dialog, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        Log.d("MyTest","1");

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String test=""+spinner2.getSelectedItem();
        Log.d("MyTest",test);

        Log.d("MyTest",""+spinner2.getSelectedItem());

        // display the car data on the dialog
        final DatabaseReference fb2 = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference infoCars2 = fb2.child("cars_info/Cars");

        String thOne =""+spinner2.getSelectedItemId() ;


        Log.d("MyTest",thOne);


        Query query1 = infoCars2.orderByKey().equalTo(test);

        query1.addValueEventListener(new ValueEventListener() {






            //Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

            TextView nom_pro =(TextView) mView.findViewById(R.id.nom_pro);
            TextView type_cont =(TextView) mView.findViewById(R.id.type_cont);
            TextView defaut_vec =(TextView) mView.findViewById(R.id.defaut_vec);

            @Override
            public void onDataChange(DataSnapshot data) {

                // do something with data

                Log.d("MyTest","2");
                for (DataSnapshot child : data.getChildren()) {
                    Log.d("MyTest","3");




                    carsInfo CInfo = child.getValue(carsInfo.class);
                    Log.d("MyTest",""+CInfo.carId);




                    nom_pro.setText(CInfo.owner);
                    type_cont.setText(CInfo.type_controle);
                    defaut_vec.setText(CInfo.problem);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // report/log the error

            }
        });




    }

    public void cancel_btn(View view) {



        //close the dialog

        dialog.cancel();

    }

    public void next_btn(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String MyData = ""+spinner2.getSelectedItem();
        Log.d("Wcar",MyData);
        // set inprogress to 1 so the other agents can't work at the same car
        DatabaseReference progre = FirebaseDatabase.getInstance().getReference();
        DatabaseReference infoprogre = progre.child("cars_info/Cars/"+MyData);

        infoprogre.child("inprogress").setValue(1);

        // send data to next activity
        intent.putExtra("carID", MyData);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        String line= ""+spinner.getSelectedItem();
        intent.putExtra("line", line);
        startActivity(intent);
        finish();


    }
}
