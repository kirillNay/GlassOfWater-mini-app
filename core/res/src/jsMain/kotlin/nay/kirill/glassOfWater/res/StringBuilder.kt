package nay.kirill.glassOfWater.res

fun buildStingsResources(
    languageCode: String?
): Map<Int, String> {
    val strs = mutableMapOf<Int, String>()
    val rs = Res.string

    when (languageCode) {
        "ru" -> {
            strs[rs.appName] = "Стаканы воды"
            strs[rs.noStats] = "Статистика недоступна"
            strs[rs.settings] = "Настройки"
            strs[rs.stats] = "Статистика"
            strs[rs.adaptiveTheme] = "Адаптивный дизайн"
            strs[rs.commonError] = "Что-то пошло не так..."
        }
        else -> {
            strs[rs.appName] = "Glasses Of Water"
            strs[rs.noStats] = "Statistics is unavailable"
            strs[rs.settings] = "Settings"
            strs[rs.stats] = "Statistics"
            strs[rs.adaptiveTheme] = "Adaptive design"
            strs[rs.commonError] = "Something went wrong..."
        }
    }
    strs[rs.minus] = "-"
    strs[rs.plus] = "+"

    return strs
}