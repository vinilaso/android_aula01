package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public enum ViewLoaded {
        FRAGMENT_A(FragmentA.class),
        FRAGMENT_B(FragmentA.class);

        private final Class<? extends Fragment> _fragmentClass;

        ViewLoaded(Class<? extends Fragment> fragmentClass) {
            this._fragmentClass = fragmentClass;
        }

        public Fragment getFragmentInstance() {
            try {
                return _fragmentClass.newInstance();
            } catch (Exception e){
                throw new RuntimeException("Não foi possível criar a instância do fragmento.", e);
            }
        }
    }

    private ViewLoaded _viewLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadComponents();

        loadFragment(ViewLoaded.FRAGMENT_A, null);
    }

    private void loadComponents() {
        findViewById(R.id.btn_input).setOnClickListener(button -> {
            int linha = 0;
            try {
                linha++;
                FragmentA fragmentA = (FragmentA) getSupportFragmentManager().findFragmentById(R.id.content_frame);

                linha++;
                assert fragmentA != null : "Manager veio nulo.";

                linha++;
                SharedViewModel model = fragmentA.getSharedViewModel();

                linha++;
                Bundle bundle = new Bundle();

                linha++;
                bundle.putDouble("num1", model.getFirstNumber().isInitialized() ? model.getFirstNumber().getValue() : 0);

                linha++;
                bundle.putDouble("num2", model.getSecondNumber().isInitialized() ? model.getSecondNumber().getValue() : 0);

                linha++;
                loadFragment(ViewLoaded.FRAGMENT_A, bundle);
            } catch (Exception e) {
                Toast.makeText(this, linha + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        findViewById(R.id.btn_results).setOnClickListener(button -> {
            loadFragment(ViewLoaded.FRAGMENT_B, null);
        });
    }

    public void loadFragment(ViewLoaded fragmentType, Bundle extras) {
        if (_viewLoaded == fragmentType)
            return;

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragmentInstance = fragmentType.getFragmentInstance();
        if (extras != null)
            fragmentInstance.setArguments(extras);

        Fragment loadedFragment = manager.findFragmentById(R.id.content_frame);
        if (loadedFragment == null) {
            transaction.add(R.id.content_frame, fragmentInstance);
        } else {
            transaction.replace(R.id.content_frame, fragmentInstance);
        }

        transaction.commit();
        _viewLoaded = fragmentType;
    }
}