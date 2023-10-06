package nay.kirill.glassOfWater.telegram

enum class ChatType(val value: String) {

    USERS("users"),

    BOTS("bots"),

    GROUPS("groups"),

    CHANNEL("channel")

}