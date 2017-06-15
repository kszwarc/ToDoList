package com.example.pc.projekt.Models;
import android.app.*;
import android.os.Bundle;
import android.widget.*;
import java.util.*;
import android.text.format.*;
import android.content.res.*;
/**
 * Created by pc on 2017-05-25.
 */
public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private EditText editText;

    public void setEditText(EditText editText)
    {
        this.editText = editText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        editText.setText(editText.getText() + " - " + hourOfDay + ":"	+ minute);
    }
}