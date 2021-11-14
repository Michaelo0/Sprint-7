package ru.sber.rdbms

import java.sql.DriverManager

class TransferPessimisticLock {
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/db",
            "postgres",
            "postgres"
        )

        connection.use {
            val autoCommit = connection.autoCommit
            try {
                connection.autoCommit = false
                val prepareStatement1 =
                    connection.prepareStatement("select * from account1 where id = $accountId1")
                prepareStatement1.executeQuery().use { statement ->
                    statement.next()
                    if (statement.getInt("amount") - amount < 0)
                        throw Exception("")
                }
                    connection.prepareStatement("select * from account1 where id in ($accountId1, $accountId2) for update").executeQuery()
                    connection.prepareStatement("update account1 set amount = amount - $amount where id = $accountId1").executeUpdate()
                    connection.prepareStatement("update account1 set amount = amount + $amount where id = $accountId2").executeUpdate()

                    connection.commit()
            } catch (exception: Exception) {
                println(exception.message)
                connection.rollback()
            } finally {
                connection.autoCommit = autoCommit
            }
        }
    }
}
