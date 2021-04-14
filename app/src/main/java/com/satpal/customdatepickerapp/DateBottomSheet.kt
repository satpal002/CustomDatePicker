package com.satpal.customdatepickerapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ViewConstructor")
class DateBottomSheet(
    context: Context,
    private val previousTimeStamp: Long,
    private val therapyStartDate: String,
    private val listener: DateSelectListener
) : FrameLayout(context), DialogInterface.OnCancelListener,
    OnDateChangedListener {

    interface DateSelectListener {
        fun onDOBDateSelect(selectedTimeStamp: String)
    }

    private val mBottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)
    private var selectedPosition: Int = 0
//    private lateinit var dateTime : DateTime

    init {
        val view = inflateLayout(context)


        setupBottomSheetBehaviour()
//        dateTime = DateTime(previousTimeStamp)
        addDataToValuePicker()
        setPreviousDate()
        setClickListener()


        val cal = Calendar.getInstance()
        cal.set(2021, 5, 11)
        initDatePicker(
            view,
            R.style.UnitNumberPickerWeight, 1582309800000, 1645468200000,
            cal, true
        )
    }

    private var mDatePicker: CustomDatePicker? = null
    private fun initDatePicker(
        view: View, spinnerTheme: Int, minDate: Long, maxDate: Long, defaultDate: Calendar,
        isDayShown: Boolean
    ) {
        val datePickerParent = view.findViewById<View>(R.id.datePickerParent)
        mDatePicker = CustomDatePicker(
            datePickerParent as ViewGroup?,
            spinnerTheme, R.drawable.bg_selector
        )
        mDatePicker!!.setMinDate(minDate)
        mDatePicker!!.setMaxDate(maxDate)
        mDatePicker!!.init(
            defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(
                Calendar.DAY_OF_MONTH
            ), isDayShown, this, resources.getStringArray(R.array.months_array_hi)
        )
    }

    private fun setPreviousDate() {

    }

    private fun setClickListener() {
        btnSave.setOnClickListener {
            // adding to month because month is index based
//            val updatedDate = dateTime.withDate(
//                datePickerDOB.year, datePickerDOB.month
//                    .plus(1), datePickerDOB.dayOfMonth
//            ).minusHours(1) // to make sure it should not clash with future date selection criteria
//            listener.onDOBDateSelect(updatedDate.millis)

            val calendar: Calendar = GregorianCalendar(
                mDatePicker!!.year,
                mDatePicker!!.month,
                mDatePicker!!.dayOfMonth
            )
            val simpleDateFormat = SimpleDateFormat("dd MM yyyy", Locale.US)
            listener.onDOBDateSelect(simpleDateFormat.format(calendar.time))
            close()
        }
    }

    lateinit var btnSave: View
    private fun addDataToValuePicker() {
//        datePickerDOB.formatDate("dmy")
//        datePickerDOB.descendantFocusability = DatePicker.FOCUS_BLOCK_DESCENDANTS
//        datePickerDOB.init(dateTime.year, dateTime.monthOfYear.minus(1), dateTime.dayOfMonth, this)
//        datePickerDOB.minDate = therapyStartDate.parseUtcDateToDateTime().minusYears(1).toLocalDate().toDate().time //DateTime.now().minusYears(10).millis
//        datePickerDOB.maxDate = DateTime.now().millis
    }

    private fun inflateLayout(context: Context): View {
        val view = inflate(context, R.layout.bottomsheet_date, this)
        btnSave = findViewById(R.id.btnSave)
        mBottomSheetDialog.setContentView(this)
        mBottomSheetDialog.window?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            ?.setBackgroundResource(android.R.color.transparent)
        mBottomSheetDialog.setOnCancelListener(this)
        return view
    }

    fun showBottomSheet() {
        mBottomSheetDialog.show()
    }

    fun close() {
        mBottomSheetDialog.dismiss()
    }

    override fun onCancel(dialog: DialogInterface?) {
    }

    fun onDateChanged(view: CustomDatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

    }

    private fun setupBottomSheetBehaviour() {
        (parent as? View)?.let { view ->
            BottomSheetBehavior.from(view).peekHeight = getScreenHeight().times(0.8f).toInt()
        }
    }

    private fun getScreenHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onDateChanged(view: View?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

    }

}