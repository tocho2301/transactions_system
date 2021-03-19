import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.*

class ReportRepository {

    private val gson = Gson()
    private val utils = Utils()

    private fun getAllTransactions() : List<Transaction>{
        val arrayTutorialType = object : TypeToken<List<Transaction>>() {}.type
        return gson.fromJson(getJsonFromFile(), arrayTutorialType)
    }

    fun getReports() : Hashtable<Int,MonthlyReport>{
        val reports = Hashtable<Int, MonthlyReport>()
        getAllTransactions().forEach { transaction ->
            if (reports.containsKey(utils.getMonthNumber(transaction.creation_date))){
                val report = reports[utils.getMonthNumber(transaction.creation_date)]!!
                addTransactionToReport(report,transaction)
            }else{
                val report = MonthlyReport(utils.getMonthName(utils.getMonthNumber(transaction.creation_date)))
                addTransactionToReport(report,transaction)
                reports[utils.getMonthNumber(transaction.creation_date)] = report
            }
        }
        return reports
    }

    private fun addTransactionToReport(report: MonthlyReport, transaction: Transaction){
        report.transactions.add(transaction)
        when(transaction.status){
            "pending" -> report.pendingTransactions++
            "rejected" -> report.blockecTransactions++
            "done" -> {
                report.doneTransactions++
                if (transaction.operation == "in") report.income+=transaction.amount
                if (transaction.operation == "out") {
                    report.outgoing+=transaction.amount
                    addOutgoingToCategory(report.outgoingsByCategory,transaction)
                }
            }
        }
    }

    private fun addOutgoingToCategory(categories : Hashtable<String,Double>, transaction: Transaction) {
        if (categories.containsKey(transaction.category)){
            categories[transaction.category] = categories[transaction.category]!! + transaction.amount
        }else{
            categories[transaction.category] = transaction.amount
        }
    }

    private fun getJsonFromFile() : String{
        return File("./src/main/resources/transactions.json").readText(Charsets.UTF_8)
    }
}