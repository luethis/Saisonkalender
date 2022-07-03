package ch.sluethi.saisonkalender

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}