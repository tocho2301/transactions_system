import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar

import java.util.Locale
class Utils {

    fun getMonthNumber(dateString: String): Int {
        val date = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateString)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return SimpleDateFormat("MM").format(calendar.time).toInt()
    }

    fun getMonthName(month: Int) : String{
        return when(month){
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            12 -> "Diciembre"
            else -> "Uknow"
        }
    }

    fun formatCurrency(amount:Double) : String{
        var decimalFormat = DecimalFormat("#.00");
        return decimalFormat.format(amount)
    }

    fun formatPercent(amount: Double) : String{
        var decimalFormat = DecimalFormat("#.0");
        return decimalFormat.format(amount)
    }


}