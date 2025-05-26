import java.util.Scanner

fun main() {
    // Create a Scanner object to read input from the console
    val reader = Scanner(System.`in`)
    var shouldExit = false // Initialize Boolean variable

    println("Welcome to the Console Calculator!")
    print("Type 'exit' at any prompt to quit.")

    /* Main loop to control user exiting console app */
    while (shouldExit.not()) {
        println("\n ...New Calculation...")
        // Initialize first number to a nullable variable
        var number1: Double? = null

        while (number1 == null && shouldExit.not()) {
            print("Enter the first number or exit to quit: ")
            val  input = reader.next()
            if (input.equals("exit",true)) {
                shouldExit = true
                break
            }
            try {
                number1 = input.toDouble()
            } catch (e: NumberFormatException) {
                println("Invalid input: ${e.message}. Please enter a valid number.")
            }
        }
        // Break the outer loop if the inner loop is exited
        if (shouldExit) {
            break
        }

        // Get the operator
        var operator: String? = null
        val validOperators = listOf("+", "-", "*", "/")
        while (operator == null || operator !in validOperators && shouldExit.not()) {
            print("Enter the operator (+, -, *, /) or 'exit' to quit: ")
            val input = reader.next()
            if (input.equals("exit", ignoreCase = true)) {
                shouldExit = true
                break
            }

            if (input in validOperators) {
                operator = input
            } else {
                println("Invalid operator. Please use one of: +, -, *, /")
            }
        }

        if (shouldExit) {
            break
        }
        /* Get the second number*/
        var number2: Double? = null
        while (number2 == null && shouldExit.not()) {
            print("Enter the second number: ")
            val input = reader.next()
            if (input.equals("exit", ignoreCase = true)) {
                shouldExit = true
                break
            }
            try {
                number2 = input.toDouble()
            } catch (e: NumberFormatException) {
                println("Invalid input: ${e.message}. Please enter a valid number.")
            }
        }

        if (shouldExit) {
            break
        }
        /* Perform calculation and display result */
        val result = calculate(number1!!, number2!!, operator!!)
        if (result != null) {
            println("Result: $result")
        } else {
            println("Calculation failed due to an error.")
        }
    } // End of user-defined loop
    println("Thank you for using the calculator!")
    reader.close() // Close Scanner
}

/* Perform the basic calculation */
fun  calculate(number1: Double, number2: Double, operator: String) : Double? {
    return when (operator) {
        "+" -> number1 + number2
        "-" -> number1 - number2
        "*" -> number1 * number2
        "/" -> {
            if (number2 == 0.0) {
                println("Error: Division by zero in not allowed.")
                null // Return null for error
            } else {
                number1 / number2
            }
        }
        else -> {
            println("Error: Unknown operator.")
            null // Return null for error
        }
    }
}