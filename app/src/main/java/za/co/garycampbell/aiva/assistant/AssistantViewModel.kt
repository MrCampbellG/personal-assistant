package za.co.garycampbell.aiva.assistant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import za.co.garycampbell.aiva.data.Assistant
import za.co.garycampbell.aiva.data.AssistantDao

class AssistantViewModel(val database: AssistantDao, application: Application): AndroidViewModel(application) {
    private var viewModeJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModeJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModeJob)
    private var currentmessage = MutableLiveData<Assistant?>()
    val messages = database.getAllMessages()

    init {
        initializeCurrentMessage()
    }

    private fun initializeCurrentMessage() {
        uiScope.launch { currentmessage.value = getCurrentMessageFromDatabase() }
    }

    private suspend fun getCurrentMessageFromDatabase(): Assistant? {
        return withContext(Dispatchers.IO){
            var message = database.getCurrentMessage()
            if (message?.assistant_message == "DEFAULT_MESSAGE" || message?.human_message == "DEFAULT_MESSAGE"){
                message = null
            }
            message
        }
    }

    fun sendMessageToDatabase(assistantMessage : String, humanMessage : String){
        uiScope.launch {
            val newAssistant = Assistant()
            newAssistant.assistant_message = assistantMessage
            newAssistant.human_message = humanMessage
            insert (newAssistant)
            currentmessage.value = getCurrentMessageFromDatabase()
        }
    }

    private suspend fun insert(message : Assistant){
        withContext(Dispatchers.IO){
            database.Insert(message)
        }
    }

    private suspend fun update(message : Assistant){
        withContext(Dispatchers.IO){
            database.Update(message)
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            currentmessage.value = null
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }
}