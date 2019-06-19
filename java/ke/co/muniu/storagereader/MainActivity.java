package ke.co.muniu.storagereader;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button RequestPermission;

    private int STORAGE_PERMISSION_CODE= 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestPermission = (Button)findViewById(R.id.btn_permission);

        RequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReadStorageAllowed()){
                    Toast.makeText(MainActivity.this, "You already have Permission ", Toast.LENGTH_SHORT).show();
                    return;
                 }
              requestStoragePermission();
                }

        });
        }
        private boolean isReadStorageAllowed(){
        int result = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result== PackageManager.PERMISSION_GRANTED)
            return true;
        else{
            return false;
        }
        }

        private void requestStoragePermission() {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }

            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);


        }
       
         public void onRequestedPermissionRequest(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){

          if(requestCode== STORAGE_PERMISSION_CODE){
              if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                  Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_SHORT).show();

              }else {
                  Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_SHORT).show();
              }

          }
        }
    }
