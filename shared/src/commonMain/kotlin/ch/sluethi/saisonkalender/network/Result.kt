package ch.sluethi.saisonkalender.network

data class Result<out T>(
    val message: String? = null,
    val products: T? = null,
    val loading: Boolean = false
) {
    companion object {
        fun loading(): Result<Nothing> {
            return Result(loading = true)
        }

        inline fun <reified T> result(products: T): Result<T> {
            return Result(products = products)
        }

        fun error(message: String): Result<Nothing> {
            return Result(message = message)
        }
    }
}
