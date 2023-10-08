package nay.kirill.glassOfWater.res

fun buildStingsResources(
    languageCode: String?
): Map<Int, String> {
    val strs = mutableMapOf<Int, String>()
    val rs = Res.string

    when (languageCode) {
        "ru" -> {}
        "en" -> {}
    }
    strs[rs.minus] = "-"
    strs[rs.plus] = "+"
    strs[rs.appName] = "Glass Of Water"

    return strs
}