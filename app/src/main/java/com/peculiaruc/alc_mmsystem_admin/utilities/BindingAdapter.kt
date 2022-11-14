package com.peculiaruc.alc_mmsystem_admin.utilities

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.ChipGroup
import com.peculiaruc.alc_mmsystem_admin.R

@BindingAdapter(value = ["checkedChipButtonId"])
fun setCheckedChipId(view: ChipGroup?, id: Int) {
    if (id != view?.checkedChipId) {
        view?.check(id)
    }
}

@InverseBindingAdapter(attribute = "checkedChipButtonId", event = "checkedChipButtonId")
fun getChipId(view: ChipGroup?): Int? {
    return view?.checkedChipId
}

@BindingAdapter("checkedChipButtonId")
fun setChipsListener(view: ChipGroup?, attChange: InverseBindingListener) {
    view?.setOnCheckedStateChangeListener { group, checkedId ->
        attChange.onChange()
    }
}

@BindingAdapter("app:showIfID")
fun showIfID(view: View, id: Int?) {
    view.isVisible = id?.let {
        when (it) {
            R.id.chip_certificates,
            R.id.chip_tasks,
            R.id.chip_mentors -> { true }
            else -> { false }
        }
    } ?: false
}

@BindingAdapter("app:showTaskBar")
fun showTaskBar(view: View, id: Int?) {
    view.isVisible = id?.let {
        it == R.id.chip_tasks
    } ?: false
}

@BindingAdapter("app:showSearchIcon")
fun showSearchIcon(view: View, id: Int?) {
    view.isVisible = id?.let {
        when (it) {
            R.id.chip_tasks,
            R.id.chip_mentors -> { true }
            else -> { false }
        }
    } ?: false
}


@BindingAdapter("app:showTaskSearchBar")
fun showTaskSearchBar(view: View, id: Int?) {
    view.isVisible = id?.let {
        it == R.id.chip_tasks
    } ?: false
}

@BindingAdapter("app:showTaskStatus")
fun showTaskStatus(view: View, status: TaskStatus?) {
    if (status != null && status != TaskStatus.ASSIGN) {
        view.isClickable = false
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.mms_green_11))
    } else {
        view.isClickable = true
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
    }
}

@BindingAdapter("app:setTaskStatus")
fun setTaskStatus(view: TextView, status: TaskStatus?) {
    status?.let {
        val taskStatus = when (status) {
            TaskStatus.ASSIGN -> {
                view.setTextColor(ContextCompat.getColor(view.context, R.color.mms_pry_4))
                view.context.getString(R.string.task_assign)
            }
            TaskStatus.ASSIGNED -> {
                view.setTextColor(ContextCompat.getColor(view.context, R.color.mms_black_5))
                view.context.getString(R.string.task_assigned)
            }
            TaskStatus.COMPLETED -> {
                view.setTextColor(ContextCompat.getColor(view.context, R.color.mms_black_5))
                view.context.getString(R.string.task_completed)
            }
        }
        view.text = taskStatus
    }
}