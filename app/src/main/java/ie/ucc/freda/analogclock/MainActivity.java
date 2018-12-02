package ie.ucc.freda.analogclock;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;

import ie.ucc.freda.analogclock.AlarmActivity;
import ie.ucc.freda.analogclock.MySurfaceView;

public class MainActivity extends Activity {
    MySurfaceView mySurfaceView=null;
    SurfaceView surfaceView1;
    SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSharedPreferences("myPref", MODE_PRIVATE) != null)    {
            sp = getSharedPreferences("myPref", MODE_PRIVATE);
        }
        super.onCreate(savedInstanceState);
        mySurfaceView= new MySurfaceView(this,300, sp.getString("theme", null));
        setContentView(mySurfaceView);
        surfaceView1=(SurfaceView)findViewById(R.id.surfaceView1);
    }

    protected void onResume(){
        super.onResume();
        mySurfaceView.onResumeMySurfaceView();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mySurfaceView.onPauseMySurfaceView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Intent intent = new Intent(getApplicationContext(),
                    AlarmActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.item2) {
            SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
            if(sp.getString("theme", null) == null
                    || sp.getString("theme", null).equalsIgnoreCase("theme2")) {
                editor.putString("theme", "theme1");
            }
            else if(sp.getString("theme", null).equalsIgnoreCase("theme1"))  {
                editor.putString("theme", "theme2");
            }
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;

    }

}
