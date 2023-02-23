package za.co.garycampbell.aiva.assistant

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import za.co.garycampbell.aiva.data.AssistantDao

class AssistantViewModelFactory (private val dataSource: AssistantDao, private val application:Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
        if (modelClass.isAssignableFrom(AssistantViewModel::class.java)){
            return AssistantViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}