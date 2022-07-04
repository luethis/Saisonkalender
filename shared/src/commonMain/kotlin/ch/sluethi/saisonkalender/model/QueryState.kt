package ch.sluethi.saisonkalender.model

data class QueryState(
    val message: String? = null,
    val products: List<Product>? = null,
    val loading: Boolean = false
) {
    companion object {
        fun error(message: String): QueryState {
            return QueryState(message = message)
        }

        fun result(products: List<Product>): QueryState {
            return QueryState(products = products)
        }

        fun loading(): QueryState {
            return QueryState(loading = true)
        }
    }
}