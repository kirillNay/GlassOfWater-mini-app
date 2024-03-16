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
            strs[rs.mainTitle] = "Ваш счетчик воды"
            strs[rs.monday] = "Пн"
            strs[rs.tuesday] = "Вт"
            strs[rs.wednesday] = "Ср"
            strs[rs.thursday] = "Чт"
            strs[rs.friday] = "Пт"
            strs[rs.saturday] = "Сб"
            strs[rs.sunday] = "Вс"
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
            strs[rs.mainTitle] = "Your water counter"
            strs[rs.monday] = "Mon"
            strs[rs.tuesday] = "Tue"
            strs[rs.wednesday] = "Wed"
            strs[rs.thursday] = "Thu"
            strs[rs.friday] = "Fri"
            strs[rs.saturday] = "Sat"
            strs[rs.sunday] = "Sun"
        }
    }
    strs[rs.minus] = "-"
    strs[rs.plus] = "+"

    return strs
}