package com.satpal.customdatepickerapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(),
    DateBottomSheet.DateSelectListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerParent = findViewById<View>(R.id.datePickerParent)
        initDatePicker(
            datePickerParent, R.style.UnitNumberPickerWeight, 0L, 1618288405994,
            Calendar.getInstance(), true
        )

        val button = findViewById<View>(R.id.button)
        button.setOnClickListener()
        {
            DateBottomSheet(
                this, 0L,
                "", this
            ).showBottomSheet()

        }
        val button2: Button = findViewById<View>(R.id.button2) as Button
        button2.setOnClickListener()
        {

            val calendar: Calendar = GregorianCalendar(
                mCustomDatePicker!!.year,
                mCustomDatePicker!!.month,
                mCustomDatePicker!!.dayOfMonth
            )
            val simpleDateFormat = SimpleDateFormat("dd MM yyyy", Locale.US)
            button2.text = simpleDateFormat.format(calendar.time)
        }
    }

    private var mCustomDatePicker: CustomDatePicker? = null
    private fun initDatePicker(
        view: View, spinnerTheme: Int, minDate: Long, maxDate: Long, defaultDate: Calendar,
        isDayShown: Boolean
    ) {
        mCustomDatePicker = CustomDatePicker(
            view as ViewGroup?,
            spinnerTheme
        )
        mCustomDatePicker!!.setMinDate(minDate)
        mCustomDatePicker!!.setMaxDate(maxDate)
        mCustomDatePicker!!.init(
            defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(
                Calendar.DAY_OF_MONTH
            ), isDayShown, resources.getStringArray(R.array.months_array_hi)
        )
    }


    override fun onDOBDateSelect(selectedTimeStamp: String) {
        val button2: Button = findViewById<View>(R.id.button2) as Button
        button2.setText(selectedTimeStamp)
    }
}