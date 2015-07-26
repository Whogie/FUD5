package com.csc413.team5.fud5.settings;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.csc413.team5.appdb.dbHelper;
import com.csc413.team5.fud5.R;
import com.csc413.team5.fud5.utils.Constants;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

@Deprecated // integrated most functionality into SettingsActivity
public class ApplicationSettingsActivity extends AppCompatActivity
        implements ApplicationSettingsFragment.ApplicationSettingsConfirmListener {
    private static final String TAG = "AppSettings";

    protected CheckBox mCheckBoxUserSettings;
    protected CheckBox mCheckBoxRestaurantHistory;
    protected Button mBtnReset;
    protected Button mBtnLocationServices;
    protected ImageButton mBtnUserSettings;
    protected ImageButton mBtnRestaurantHistory;

    protected ToolTipView mTooltipUserSettings;
    protected ToolTipView mTooltipRestaurantHistory;

    protected boolean isUserSettingsChecked;
    protected boolean isRestaurantHistoryChecked;

    public static final String PREFS_FILE = "UserSettings";
    private SharedPreferences userSettings;
    private SharedPreferences.Editor userSettingsEditor;
    private dbHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_settings);

        String title = getApplicationContext().getResources()
                .getString(R.string.activity_application_settings_title);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#43428A")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#ECCD7F'>" + title + "</font>"));

        mCheckBoxUserSettings = (CheckBox) findViewById(R.id.checkBoxAppSettingsUser);

        userSettings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        userSettingsEditor = userSettings.edit();

        db = new dbHelper(this, null, null, 1);

        isUserSettingsChecked = false;
        mCheckBoxUserSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUserSettingsChecked = !isUserSettingsChecked;
                Log.i(TAG, "user settings checkbox was " +
                        (isUserSettingsChecked ? "checked" : "unchecked"));
            }
        });

        mCheckBoxRestaurantHistory = (CheckBox) findViewById(R.id.checkBoxAppSettingsRestaurant);
        isRestaurantHistoryChecked = false;
        mCheckBoxRestaurantHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRestaurantHistoryChecked = !isRestaurantHistoryChecked;
                Log.i(TAG, "restaurant history checkbox was " +
                        (isRestaurantHistoryChecked ? "checked" : "unchecked"));
            }
        });

        mBtnReset = (Button) findViewById(R.id.buttonAppSettingsReset);
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserSettingsChecked || isRestaurantHistoryChecked) {
                    DialogFragment confirmDialog = ApplicationSettingsFragment
                            .newInstance(isUserSettingsChecked, isRestaurantHistoryChecked);
                    confirmDialog.show(getFragmentManager(), "ConfirmResetSettings");
                }
            }
        });

        mBtnLocationServices = (Button) findViewById(R.id.buttonManageLocationServices);
        mBtnLocationServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings
                        .ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });

        mBtnUserSettings = (ImageButton) findViewById(R.id.imageButtonResetUserSettingsInfo);
        mBtnUserSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "user settings info button was pressed");
                if (mTooltipUserSettings != null) {
                    mTooltipUserSettings.remove();
                }

                ToolTipRelativeLayout tooltipView
                        = (ToolTipRelativeLayout) findViewById(R.id.tooltipAppSettingsUser);
                ToolTip tooltip = new ToolTip()
                        .withText(getString(R.string
                                .activity_application_settings_user_settings_caption))
                        .withColor(Color.parseColor("#ECCD7F"))
                        .withShadow();
                mTooltipUserSettings = tooltipView
                        .showToolTipForView(tooltip, findViewById(R.id.checkBoxAppSettingsUser));
            }
        });

        mBtnRestaurantHistory = (ImageButton) findViewById(R.id
                .imageButtonResetRestaurantHistoryInfo);
        mBtnRestaurantHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "restaurant history info button was pressed");

                if (mTooltipRestaurantHistory != null) {
                    mTooltipRestaurantHistory.remove();
                }

                ToolTipRelativeLayout tooltipView
                        = (ToolTipRelativeLayout) findViewById(R.id.tooltipAppSettingsRestaurant);
                ToolTip tooltip = new ToolTip()
                        .withText(getString(R.string
                                .activity_application_settings_restaurant_history_caption))
                        .withColor(Color.parseColor("#ECCD7F"))
                        .withShadow();
                mTooltipRestaurantHistory = tooltipView
                        .showToolTipForView(tooltip,
                                findViewById(R.id.checkBoxAppSettingsRestaurant));
            }
        });
    }

    public boolean isUserSettingsChecked() {
        return isUserSettingsChecked;
    }

    public boolean isRestaurantHistoryChecked() {
        return isRestaurantHistoryChecked;
    }

    public void resetAppSettings() {
        if (isUserSettingsChecked) {
            userSettingsEditor.clear().commit();

            // TODO: test this
            // Reset preferences to default

            /* DEFAULT SETTINGS
            * Has agreed to EULA: false
            * Min star: 3.5
            * Location search: 1600 Holloway Ave.
            * Radius: 5 mi.
            * Niches: Italian, Carnivore, Hole in the wall, affordable
            * App Language: Relative to device language
            * */

            final boolean hasAgreed = false;
            final float minStar = 3.50f;
            final String searchLocation = "1600 Holloway Ave.";
            final float searchRadius = 1.5f;
//            Set<String> preferredNiches;
            String appLanguage = Locale.getDefault().getDisplayLanguage();

            userSettingsEditor.putBoolean("hasAgreedToEula", hasAgreed).apply();
            userSettingsEditor.putFloat("defaultMinStar", minStar).apply();
            userSettingsEditor.putString("defaultSearchLocation", searchLocation).apply();
            userSettingsEditor.putFloat("defaultSearchRadius", searchRadius).apply();
//            userSettings.getStringSet("defaultPreferredNiches", getPreferredNiches().apply());
            userSettingsEditor.putString("appLanguage", Locale.getDefault().getDisplayLanguage()).apply();

            Log.i(TAG, "cleared user settings");
            Toast.makeText(this, "Cleared User Settings", Toast.LENGTH_SHORT).show();
        }

        if (isRestaurantHistoryChecked) {
            db.wipeRestaurantList(Constants.GREEN_LIST);
            Log.i(TAG, "wiped green list (1)");
            db.wipeRestaurantList(Constants.YELLOW_LIST);
            Log.i(TAG, "wiped yellow list (2)");
            db.wipeRestaurantList(Constants.RED_LIST);
            Log.i(TAG, "wiped red list (3)");
            Toast.makeText(this, "Cleared Restaurant History", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onApplicationSettingsConfirmPositiveClick(DialogFragment dialog) {
        resetAppSettings();
    }

    @Override
    public void onApplicationSettingsConfirmNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Settings were not changed", Toast.LENGTH_SHORT).show();
    }

    // Preferred niches = Search term in the main app
    // TODO: Implement this in the future
    private Set<String> getPreferredNiches() {

        // load preferred niches from resources
        // TODO This array must be able to be modified when Food Preferences is modified
        // ie: when user deletes a niche, update resources
        TypedArray preferred_niche = getResources().obtainTypedArray(R.array.preferred_niche_array);

        Set<String> preferredNicheSet = new TreeSet<String>();

        int preferred_niche_size = getResources().obtainTypedArray(R.array.preferred_niche_array).length();

        for (int i = 0; i < preferred_niche_size; i++) {
            preferredNicheSet.add(preferred_niche.getString(i));
        }

        return preferredNicheSet;
    }
}
