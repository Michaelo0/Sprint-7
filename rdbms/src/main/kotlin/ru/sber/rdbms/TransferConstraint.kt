package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferConstraint {
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/db",
            "postgres",
            "postgres"
        )

        connection.use { it ->
            try {
                it.prepareStatement("alter table account1 add constraint chk_amount check (amount>=0)").execute()
                it.prepareStatement("update account1 set amount = amount - $amount where id = $accountId1").executeUpdate()
                it.prepareStatement("update account1 set amount = amount + $amount where id = $accountId2").executeUpdate()
            } catch (exception: SQLException) {
                println(exception.message)
            }
        }
    }
}
