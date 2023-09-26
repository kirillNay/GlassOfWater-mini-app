package nay.kirill.glassOfWater.res

fun buildStingsResources(): Map<Int, String> {
    val strs = mutableMapOf<Int, String>()
    val rs = Res.string

    strs[rs.minus] = "-"
    strs[rs.plus] = "+"
    strs[rs.appName] = "Glass Of Water"

    return strs
}