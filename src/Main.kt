var ticket = 0
var frontHalf = 0
var lastHalf = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    
    val array2d = Array(rows) {
        Array(seats) { 'S' }
    }
    
    val totalSeats = rows * seats
    
    
    val totalIncome = if (totalSeats <= 60) totalSeats * 10
    else if (rows % 2 == 0){
        ((totalSeats / 2) * 10) + ((totalSeats / 2) * 8)
    } else {
        (((rows -1) /2 * seats) * 10) + (((rows + 1) / 2 * seats) * 8)
    }
    
    
    println()
    println(
        """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
        """.trimIndent()
    )
    
    while (true) {
        
        val input = readln().toInt()
        if (input == 0) break
        
        menu(
            rows = rows,
            seats = seats,
            array2d = array2d,
            input = input,
            ticket = ticket,
            totalSeats = totalSeats,
            totalIncome = totalIncome
        )
        
        println()
        println(
            """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
        """.trimIndent()
        )
        
    }
}


fun menu(
    rows: Int, seats: Int, array2d: Array<Array<Char>>, input: Int,
    ticket: Int, totalSeats: Int, totalIncome: Int
) {
    
    when (input) {
        1 -> showTheSeats(seats, array2d)
        2 -> buyTicket(array2d, rows, seats)
        3 -> statistics(ticket, totalSeats, totalIncome)
        0 -> return
    }
}


fun statistics(ticket: Int, totalSeats: Int, totalIncome: Int) {
    
    val ticketInPercent = "%.2f".format((ticket / totalSeats.toDouble()) * 100)
    val currentIncome = (frontHalf * 10) + (lastHalf * 8)
    
    println(
        """
        Number of purchased tickets: $ticket
        Percentage: $ticketInPercent%
        Current income: $$currentIncome
        Total income: $$totalIncome
    """.trimIndent()
    )
}


fun showTheSeats(seats: Int, array2d: Array<Array<Char>>) {
    println("\nCinema:")
    for (i in 0..seats) {
        if (i == 0) print("  ")
        else print("${i} ")
    }
    println()
    
    for (i in array2d.indices) {
        print("${i + 1} ")
        for (j in array2d[i].indices) {
            
            print("${array2d[i][j]} ")
        }
        println()
    }
}


fun buyTicket(array2d: Array<Array<Char>>, rows: Int, seats: Int) {
    
    println("\nEnter a row number:")
    val rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNumber = readln().toInt()
    
    try {
        if (array2d[rowNumber - 1][seatNumber - 1] == 'B') {
            println("That ticket has already been purchased!")
            buyTicket(array2d, rows, seats)
        } else {
            array2d[rowNumber - 1][seatNumber - 1] = 'B'
            ticket += 1
            if (rows % 2 == 0) {
                if (rowNumber <= rows / 2) frontHalf+=1 else lastHalf+=1
            } else {
                if (rowNumber <= (rows - 1) / 2) frontHalf+=1 else lastHalf+=1
            }
        }
    } catch (_: Exception) {
        println("Wrong input!")
        buyTicket(array2d, rows, seats)
    }
    
    val ticketPrice = if (rows * seats <= 60) {
        10
    } else if (rows % 2 == 0) {
        if (rowNumber <= rows / 2) 10 else 8
    } else {
        if (rowNumber <= (rows - 1) / 2) 10 else 8
    }
    
    println("Ticket price: $$ticketPrice")
}