package com.financas_simples_android.utils

import java.text.NumberFormat
import java.util.*

class Format {

    companion object {
        fun formatDate(date: String): String {

            val fullDate = date.substring(0, 10)
            val month = date.substring(5, 7)
            val day = date.substring(8, 10)

            return "${day}/${month}"
        }

        fun formatValueColor(value: String): String {

            val number = value.toDouble()

            if (number < 0) {
                return "#FF0000"
            }
            return "#2DA688"
        }

        fun formatCategoryColor(category: String): String {

           when (category) {
               "Home" -> return "#F573CE"
               "Supermarket" -> return "#F0765D"
               "Health" -> return "#54D3FA"
               "Clothes" -> return "#954688"
               "SelfCare" -> return "#F0AE67"
               "Food" -> return "#FA694B"
               "Pet" -> return "#F56E73"
               "Transport" -> return "#AF97F0"
               "Investment" -> return "#4FA963"
               "Other" -> return "#7A3938"
            }

            return "#2DA688"
        }

        fun formatCurrency(value: String): String {

            val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            format.currency = Currency.getInstance("BRL")

            return format.format(value.toDouble())
        }
    }
}