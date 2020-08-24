package lightcycleconsulting.com.skipcodeexample.activities.utils

object QueryUtils {
    fun convertQueryToSearchParameter(query: String?): String {
        var result = ""
        var i = 0
        query?.split(" ")?.forEach { parameter ->
            result += "search=$parameter"
            if (i != query.split(" ").size -1) {
                result += "&"
            }
            i++
        }
        return result
    }
}