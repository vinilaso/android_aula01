package com.example.mylauncher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listaApps = findViewById(R.id.lista_apps);

        final PackageManager manager = getPackageManager();

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        final List<ApplicationInfo> apps = manager.queryIntentActivities(mainIntent, 0)
                .stream()
                .map(x -> x.activityInfo.applicationInfo)
                .collect(Collectors.toList());

        final AppAdapter adapter = new AppAdapter(this, R.layout.item_lista, apps);

        listaApps.setAdapter(adapter);

        listaApps.setOnItemClickListener((parent, view, p, id) -> {
            final ApplicationInfo app = (ApplicationInfo) parent.getItemAtPosition(p);

            final String packageName = app.packageName;

            final Intent intent = manager.getLaunchIntentForPackage(packageName);

            if(intent != null){
                startActivity(intent);
            }else{
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_SHORT).show();
            }
        });
    }
}