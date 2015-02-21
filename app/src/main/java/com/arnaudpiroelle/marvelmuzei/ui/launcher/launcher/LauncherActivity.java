package com.arnaudpiroelle.marvelmuzei.ui.launcher.launcher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.arnaudpiroelle.marvelmuzei.R;
import com.arnaudpiroelle.marvelmuzei.core.inject.Injector;
import com.arnaudpiroelle.marvelmuzei.core.utils.AppCheckerUtils;
import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import static android.content.Intent.ACTION_VIEW;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
import static android.content.pm.PackageManager.DONT_KILL_APP;
import static android.util.Log.INFO;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;
import static com.arnaudpiroelle.marvelmuzei.core.utils.Constants.APP_TAG;

public class LauncherActivity extends ActionBarActivity {

    @InjectView(R.id.install_button)
    Button installButton;

    @InjectView(R.id.remove_button)
    Button removeButton;

    @Inject
    PackageManager packageManager;

    @Inject
    AppCheckerUtils appCheckerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Injector.inject(this);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (appCheckerUtils.checkApplicationInstalled("net.nurik.roman.muzei")) {
            installButton.setVisibility(GONE);
            removeButton.setVisibility(VISIBLE);
        } else {
            installButton.setVisibility(VISIBLE);
            removeButton.setVisibility(GONE);
        }
    }

    @OnClick(R.id.install_button)
    void onInstallClick() {
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=net.nurik.roman.muzei"));
        startActivity(intent);
    }

    @OnClick(R.id.remove_button)
    void onRemoveClick() {
        Crashlytics.log(INFO, APP_TAG, "Remove Launcher Activity");

        packageManager.setComponentEnabledSetting(getComponentName(),
                COMPONENT_ENABLED_STATE_DISABLED,
                DONT_KILL_APP);

        Toast.makeText(this, R.string.already_installed, LENGTH_SHORT).show();

        this.finish();
    }
}
