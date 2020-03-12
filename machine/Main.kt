package machine

import java.util.Scanner

enum class CoffeeMachineStatus(val prompt: String) {
    TAKING_ACTION("Write action (buy, fill, take, remaining, exit): > "),
    TAKING_ORDER("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: > "),
    FILLING_WATER("Write how many ml of water do you want to add: > "),
    FILLING_MILK("Write how many ml of milk do you want to add: > "),
    FILLING__COFFEE_BEANS("Write how many grams of coffee beans do you want to add: > "),
    FILLING_CUPS("Write how many disposable cups of coffee do you want to add: > "),
    EXITING("");
}

class CoffeeMachine {
    val espressoWaterPerCup = 250
    val espressoMilkPerCup = 0
    val espressoCoffeePerCup = 16
    val espressoCostPerCup = 4

    val latteWaterPerCup = 350
    val latteMilkPerCup = 75
    val latteCoffeePerCup = 20
    val latteCostPerCup = 7

    val cappuccinoWaterPerCup = 200
    val cappuccinoMilkPerCup = 100
    val cappuccinoCoffeePerCup = 12
    val cappuccinoCostPerCup = 6

    var availableWater = 400
    var availableMilk = 540
    var availableCoffee = 120
    var availableCups = 9
    var availableMoney = 550

    var status = CoffeeMachineStatus.TAKING_ACTION


    fun getInput(strInput: String) {
        if (status == CoffeeMachineStatus.TAKING_ACTION) {
            processAction(strInput)
        } else if (status == CoffeeMachineStatus.TAKING_ORDER) {
            processOrder(strInput)
        } else if (status == CoffeeMachineStatus.FILLING_WATER ||
            status == CoffeeMachineStatus.FILLING_MILK ||
            status == CoffeeMachineStatus.FILLING__COFFEE_BEANS ||
            status == CoffeeMachineStatus.FILLING_CUPS
        ) {
            processFillings(strInput)
        }
    }

    fun processAction(strAction: String) {
        when (strAction) {
            "buy" -> status = CoffeeMachineStatus.TAKING_ORDER

            "fill" -> status = CoffeeMachineStatus.FILLING_WATER

            "take" -> {
                println("I gave you $$availableMoney")
                availableMoney = 0
            }

            "remaining" -> displayStatus()

            "exit" -> status = CoffeeMachineStatus.EXITING
        }
    }

    fun processOrder(strOrder: String) {
        when (strOrder) {
            "1" -> {
                if (availableWater >= espressoWaterPerCup &&
                    availableMilk >= espressoMilkPerCup &&
                    availableCoffee >= espressoCoffeePerCup &&
                    availableCups >= 1
                ) {
                    println("I have enough resources, making you a coffee!")
                    availableWater -= espressoWaterPerCup
                    availableMilk -= espressoMilkPerCup
                    availableCoffee -= espressoCoffeePerCup
                    availableCups -= 1
                    availableMoney += espressoCostPerCup
                } else {
                    when {
                        availableWater < espressoWaterPerCup -> println("Sorry, not enough water!")
                        availableMilk < espressoMilkPerCup -> println("Sorry, not enough milk!")
                        availableCoffee < espressoCoffeePerCup -> println("Sorry, not enough coffee beans!")
                        availableCups < 1 -> println("Sorry, not enough cups!")
                    }
                }
            }
            "2" -> {
                if (availableWater >= latteWaterPerCup &&
                    availableMilk >= latteMilkPerCup &&
                    availableCoffee >= latteCoffeePerCup &&
                    availableCups >= 1
                ) {
                    println("I have enough resources, making you a coffee!")
                    availableWater -= latteWaterPerCup
                    availableMilk -= latteMilkPerCup
                    availableCoffee -= latteCoffeePerCup
                    availableCups -= 1
                    availableMoney += latteCostPerCup
                } else {
                    when {
                        availableWater < latteWaterPerCup -> println("Sorry, not enough water!")
                        availableMilk < latteMilkPerCup -> println("Sorry, not enough milk!")
                        availableCoffee < latteCoffeePerCup -> println("Sorry, not enough coffee beans!")
                        availableCups < 1 -> println("Sorry, not enough cups!")
                    }
                }
            }
            "3" -> {
                if (availableWater >= cappuccinoWaterPerCup &&
                    availableMilk >= cappuccinoMilkPerCup &&
                    availableCoffee >= cappuccinoCoffeePerCup &&
                    availableCups >= 1
                ) {
                    println("I have enough resources, making you a coffee!")
                    availableWater -= cappuccinoWaterPerCup
                    availableMilk -= cappuccinoMilkPerCup
                    availableCoffee -= cappuccinoCoffeePerCup
                    availableCups -= 1
                    availableMoney += cappuccinoCostPerCup
                } else {
                    when {
                        availableWater < cappuccinoWaterPerCup -> println("Sorry, not enough water!")
                        availableMilk < cappuccinoMilkPerCup -> println("Sorry, not enough milk!")
                        availableCoffee < cappuccinoCoffeePerCup -> println("Sorry, not enough coffee beans!")
                        availableCups < 1 -> println("Sorry, not enough cups!")
                    }
                }
            }
            "back" -> {
            }
        }
        status = CoffeeMachineStatus.TAKING_ACTION
    }

    fun processFillings(strFillingAmt: String) {
        when (status) {
            CoffeeMachineStatus.FILLING_WATER -> {
                availableWater += strFillingAmt.toInt()
                status = CoffeeMachineStatus.FILLING_MILK
            }
            CoffeeMachineStatus.FILLING_MILK -> {
                availableMilk += strFillingAmt.toInt()
                status = CoffeeMachineStatus.FILLING__COFFEE_BEANS
            }
            CoffeeMachineStatus.FILLING__COFFEE_BEANS -> {
                availableCoffee += strFillingAmt.toInt()
                status = CoffeeMachineStatus.FILLING_CUPS
            }
            CoffeeMachineStatus.FILLING_CUPS -> {
                availableCups += strFillingAmt.toInt()
                status = CoffeeMachineStatus.TAKING_ACTION
            }
        }
    }

    fun displayStatus() {

        println("The coffee machine has:")
        println("$availableWater of water")
        println("$availableMilk of milk")
        println("$availableCoffee of coffee beans")
        println("$availableCups of disposable cups")
        println("$$availableMoney of money")
    }
}

fun getInput(scanner: Scanner, prompt: String): String {
    println()
    print(prompt)
    val input = scanner.next()
    println()

    return input
}

fun main() {

    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine()

    while (coffeeMachine.status != CoffeeMachineStatus.EXITING) {
        val input = getInput(scanner, coffeeMachine.status.prompt)
        coffeeMachine.getInput(input)
    }
}
