package thousand.group.healbits.global.sealed_classes

sealed class MessageStatuses {
    internal object ON_SUCCESS : MessageStatuses()
    internal object ON_ERROR : MessageStatuses()
}