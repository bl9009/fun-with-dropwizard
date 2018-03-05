package fwd.factory.main

class Factory(private val productionRate: Int, private val friesPerPotato: Int, private val potatoSupply: Int) {

    private var friesStock = 0
    private var potatoStock = 0

    def produce(): Unit = {
        if (potatoStock < potatoesNeeded()) {
            purchasePotatoes()
        }

        potatoStock -= potatoesNeeded

        friesStock += productionRate
    }

    private def potatoesNeeded(): Int = {
        // productionRate: potatoes processed per production step
        return productionRate * friesPerPotato
    }

    private def purchasePotatoes(): Unit = {
        potatoStock += potatoSupply
    }
}
