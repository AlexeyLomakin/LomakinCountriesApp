
import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimberFileLog: Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val logPriority = when (priority) {
            Log.ERROR -> "E"
            Log.WARN -> "W"
            Log.INFO -> "I"
            else -> "D"
        }

        val logMessage = "${dateFormat.format(Date())} $logPriority/$tag: $message\n"

        try {
            val logFile = File("errors.log")
            val fos = FileOutputStream(logFile, true)
            fos.write(logMessage.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    companion object {
        private val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS", Locale.ENGLISH)
    }
}