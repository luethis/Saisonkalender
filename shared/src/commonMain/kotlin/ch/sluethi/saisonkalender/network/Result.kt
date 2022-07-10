package ch.sluethi.saisonkalender.network

data class Result<out T>(
    val message: String? = null,
    val data: T? = null,
    val loading: Boolean = false
) {
    companion object {
        fun loading(): Result<Nothing> {
            return Result(loading = true)
        }

        inline fun <reified T> result(products: T): Result<T> {
            return Result(data = products)
        }

        fun error(message: String): Result<Nothing> {
            return Result(message = message)
        }
    }
}
