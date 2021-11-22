package com.developnetwork.meshlwahdk.ui.main.reminder

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.model.Reminder
import com.developnetwork.meshlwahdk.data.repository.DoseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReminderViewModel(private val doseRepo: DoseRepo) : BaseViewModel() {
    fun getReminders() = handleRequestLiveData<List<Reminder>> {
        val result = withContext(Dispatchers.IO) {
            doseRepo.getDoses()
        }
        val reminders = ArrayList<Reminder>()

        for (dose in result) {
            var time=12
            for(i in 1 .. dose.frequency){
                reminders.add(Reminder("$time:00",dose.product.name,dose.product.logo))
                time+=24.div(dose.frequency)

                if(time>23)
                    time -= 24
            }
        }
        emit(reminders)
    }
}