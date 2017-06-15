package com.example.pc.projekt.Models;
import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import android.text.format.*;

/**
 * Created by pc on 2017-05-25.
 */

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private static DatePickerDialog dpd = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (dpd==null)
            dpd = new DatePickerDialog(getActivity(), this, year, month, day);
        return dpd;
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        dpd = null;
        showTimePicker();
    }

    public void setEditText(EditText editText)
    {
        this.editText = editText;
    }

    private void showTimePicker() {
        TimePicker picker = new TimePicker();
        picker.setEditText(editText);
        picker.show(getFragmentManager(), "timePicker");
    }
}
