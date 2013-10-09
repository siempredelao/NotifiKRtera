package net.david.notifikrtera;


import java.io.File;

import com.google.android.maps.MapActivity;

import net.david.Presenter;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			new Presenter(this, R.raw.definicionnotifikrtera, "GUID-007", "�Crea una nueva incidencia pulsando aqu�!").run();
		} catch (Exception e) {
			e.printStackTrace();
			onDestroy();
		}
	}

	// Creamos el men� para salir de la app
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.MnuOpc1:
	        	onDestroy();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		try {
			System.runFinalizersOnExit(true);
            trimCache(this);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void trimCache(Context context) {
        try {
           File dir = context.getCacheDir();
           if (dir != null && dir.isDirectory()) {
              deleteDir(dir);
           }
        } catch (Exception e) {
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
           String[] children = dir.list();
           for (int i = 0; i < children.length; i++) {
              boolean success = deleteDir(new File(dir, children[i]));
              if (!success) {
                 return false;
              }
           }
        }
        // The directory is now empty so delete it
        return dir.delete();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
