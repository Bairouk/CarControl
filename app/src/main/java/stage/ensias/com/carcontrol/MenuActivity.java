package stage.ensias.com.carcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Intent intent = getIntent();
        String theCar = intent.getStringExtra("carID");
        String line = intent.getStringExtra("line");
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference infoCars = fb.child("cars_info/Cars/"+theCar);

        infoCars.child("line").setValue(Integer.parseInt(line));

        Log.d("recData",theCar+"///"+line);



    }

}
