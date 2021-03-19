import java.util.*
import kotlin.collections.ArrayList

data class MonthlyReport(
    var month: String,
    var pendingTransactions: Int = 0,
    var blockecTransactions: Int = 0,
    var doneTransactions: Int = 0,
    var income: Double = 0.0,
    var outgoing: Double = 0.0,
    var transactions : MutableList<Transaction> = ArrayList(),
    var outgoingsByCategory: Hashtable<String,Double> = Hashtable<String,Double>()
)
