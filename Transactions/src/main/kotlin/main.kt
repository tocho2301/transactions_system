val transactionsRepository = ReportRepository()
val utils = Utils()

fun main() {
    val transactionsByMonth = transactionsRepository.getReports()
    transactionsByMonth.toSortedMap().forEach{ transactionMap ->
        println("${transactionMap.value.month} :")
        println("   ${transactionMap.value.pendingTransactions} transacciones pendientes")
        println("   ${transactionMap.value.blockecTransactions} transacciones bloqueadas \n")
        println("   $ ${utils.formatCurrency(transactionMap.value.income)} ingresos \n")
        println("   $ ${utils.formatCurrency(transactionMap.value.outgoing)} gastos \n")

        transactionMap.value.outgoingsByCategory
            .toList()
            .sortedByDescending { (_, value) -> value }
            .subList(0,3)
            .toMap()
            .forEach { category ->
                val percent = getPercent(category.value,transactionMap.value.outgoing)
                println("      ${category.key}   %${utils.formatPercent(percent)}")
            }

        println("\n \n")
    }
}

fun getPercent(amount: Double ,total:Double) : Double{
    return amount * 100 / total
}