package ce.yildiz.edu.tr.calendar.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import ce.yildiz.edu.tr.calendar.R;

public class UserSettingsFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    private CardView ringToneCardView;
    private CardView reminderTimeCardView;
    private CardView reminderFrequencyCardView;

    private TextView ringtoneTextView;
    private TextView reminderTimeTextView;
    private TextView reminderFrequencyTextView;

    private AlertDialog ringtoneAlertDialog;
    private AlertDialog reminderTimeAlertDialog;
    private AlertDialog reminderFrequencyAlertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        defineViews(view);
        initViews();
        createAlertDialogs();
        defineListeners();

        return view;
    }


    private void defineViews(View view) {
        ringToneCardView = (CardView) view.findViewById(R.id.UserSettingsFragment_CardView_RingTone);
        reminderTimeCardView = (CardView) view.findViewById(R.id.UserSettingsFragment_CardView_ReminderTime);
        reminderFrequencyCardView = (CardView) view.findViewById(R.id.UserSettingsFragment_CardView_ReminderFrequency);

        ringtoneTextView = (TextView) view.findViewById(R.id.UserSettingsFragment_TextView_DefaultRingtone);
        reminderTimeTextView = (TextView) view.findViewById(R.id.UserSettingsFragment_TextView_DefaultReminderTime);
        reminderFrequencyTextView = (TextView) view.findViewById(R.id.UserSettingsFragment_TextView_DefaultReminderFrequency);
    }

    private void initViews() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ringtoneTextView.setText(sharedPreferences.getString("ringtone", "Consequence"));
        reminderTimeTextView.setText(sharedPreferences.getString("reminder", getResources().getString(R.string.at_the_time_of_event)));
        reminderFrequencyTextView.setText(sharedPreferences.getString("frequency", "Solo una vez"));
    }

    private void createAlertDialogs() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        // Ringtone AlertDialog
        final View ringtoneDialogView = LayoutInflater.from(getContext()).inflate(R.layout.layout_alert_dialog_ringtone, null, false);
        RadioGroup ringToneRadioGroup = (RadioGroup) ringtoneDialogView.findViewById(R.id.AlertDialogLayout_RadioGroup);
        ringToneRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ringtoneTextView.setText(((RadioButton) ringtoneDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                save("ringtone", ((RadioButton) ringtoneDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                ringtoneAlertDialog.dismiss();
            }
        });
        builder.setView(ringtoneDialogView);
        ringtoneAlertDialog = builder.create();
        ((Button) ringtoneDialogView.findViewById(R.id.AlertDialogLayout_Button_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtoneAlertDialog.dismiss();
            }
        });

        // Reminder time AlertDialog
        final View reminderTimeDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_alert_dialog_notification, null, false);
        RadioGroup reminderTimeRadioGroup = (RadioGroup) reminderTimeDialogView.findViewById(R.id.AlertDialogLayout_RadioGroup);
        reminderTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reminderTimeTextView.setText(((RadioButton) reminderTimeDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                save("reminder", ((RadioButton) reminderTimeDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                reminderTimeAlertDialog.dismiss();
            }
        });
        builder.setView(reminderTimeDialogView);
        reminderTimeAlertDialog = builder.create();
        ((Button) reminderTimeDialogView.findViewById(R.id.AlertDialogLayout_Button_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderTimeAlertDialog.dismiss();
            }
        });

        // Reminder frequency Alert Dialog
        final View reminderFrequencyDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_alert_dialog_repeat, null, false);
        RadioGroup reminderFrequencyRadioGroup = (RadioGroup) reminderFrequencyDialogView.findViewById(R.id.AlertDialogLayout_RadioGroup);
        reminderFrequencyRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reminderFrequencyTextView.setText(((RadioButton) reminderFrequencyDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                save("frequency", "Repetir " + ((RadioButton) reminderFrequencyDialogView.findViewById(group.getCheckedRadioButtonId())).getText().toString());
                reminderFrequencyAlertDialog.dismiss();
            }
        });
        builder.setView(reminderFrequencyDialogView);
        reminderFrequencyAlertDialog = builder.create();
        ((Button) reminderFrequencyDialogView.findViewById(R.id.AlertDialogLayout_Button_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderFrequencyAlertDialog.dismiss();
            }
        });
    }

    private void defineListeners() {
        ringToneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtoneAlertDialog.show();
            }
        });

        reminderTimeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderTimeAlertDialog.show();
            }
        });

        reminderFrequencyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderFrequencyAlertDialog.show();
            }
        });

    }

    private void save(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private void saveFlag(String key, boolean flag) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, flag);
        editor.apply();
    }

    private String getString(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(key, "");
    }

    private void restartApp() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
