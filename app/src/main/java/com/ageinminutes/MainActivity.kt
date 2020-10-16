package com.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Date Picker works", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/ $selectedYear"

                tvSelectedDate.setText(selectedDate)

                val simpledateformat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = simpledateformat.parse(selectedDate)

                val selectedDateInMinutes = (theDate!!.time / 60000) / 1440

                val currentDate = simpledateformat.parse((simpledateformat.format(System.currentTimeMillis())))

                val currentDateInMinutes = (currentDate!!.time / 60000) / 1440

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            },
            year,
            month,
            day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
    }
