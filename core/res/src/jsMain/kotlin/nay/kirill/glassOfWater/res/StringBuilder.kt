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
            strs[rs.commonError] = "Что-то пошло не так..."
            strs[rs.interfaceSettings] = "Интерфейс"
            strs[rs.applicationSettings] = "Цель стаканов на день"
            strs[rs.themeLight] = "Светлая тема"
            strs[rs.themeDark] = "Темная тема"
            strs[rs.themeSystem] = "Системная тема"
            strs[rs.themeAdaptive] = "Адаптивная тема"
        }
        else -> {
            strs[rs.appName] = "Glasses Of Water"
            strs[rs.noStats] = "Statistics is unavailable"
            strs[rs.settings] = "Settings"
            strs[rs.stats] = "Statistics"
            strs[rs.commonError] = "Something went wrong..."
            strs[rs.interfaceSettings] = "Interface"
            strs[rs.applicationSettings] = "Daily goal of glasses"
            strs[rs.themeLight] = "Light theme"
            strs[rs.themeDark] = "Dark theme"
            strs[rs.themeSystem] = "System theme"
            strs[rs.themeAdaptive] = "Adaptive theme"
        }
    }
    strs[rs.minus] = "-"
    strs[rs.plus] = "+"

    return strs
}