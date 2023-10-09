package nay.kirill.glassOfWater.res

fun buildStingsResources(
    languageCode: String?
): Map<Int, String> {
    val strs = mutableMapOf<Int, String>()
    val rs = Res.string

    when (languageCode) {
        "ru" -> {
            strs[rs.appName] = "Стаканы воды"
            strs[rs.noStats] = "Статистика не доступна"
            strs[rs.settings] = "Настройки"
            strs[rs.stats] = "Статистика"
            strs[rs.clearDataButton] = "Очистить данные"
            strs[rs.clearDataConfirmation] = "Вы уверены, что хотите очистить данные?"
            strs[rs.mockDataButton] = "Тестовые данные"
            strs[rs.mockDataConfirmation] = "Вы уверены, что хотите применить тестовые данные? Текущие данные будут потеряны!"
            strs[rs.adaptiveTheme] = "Адаптивный дизайн"
            strs[rs.commonError] = "Что-то пошло не так..."
        }
        else -> {
            strs[rs.appName] = "Glasses Of Water"
            strs[rs.noStats] = "Statistics is unavailable"
            strs[rs.settings] = "Settings"
            strs[rs.stats] = "Statistics"
            strs[rs.clearDataButton] = "Clear data"
            strs[rs.clearDataConfirmation] = "Are you sure you want to clear data?"
            strs[rs.mockDataButton] = "Mock data"
            strs[rs.mockDataConfirmation] = "Are you sure you want to mock data? All saved data will be lost!"
            strs[rs.adaptiveTheme] = "Adaptive design"
            strs[rs.commonError] = "Something went wrong..."
        }
    }
    strs[rs.minus] = "-"
    strs[rs.plus] = "+"

    return strs
}