package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferOptimisticLock {
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/db",
            "postgres",
            "postgres"
        )

        var oldVersion: Int
        connection.use {
            val autoCommit = it.autoCommit
            try {
                it.autoCommit = false
                val prepareStatement1 =
                    it.prepareStatement("select * from account1 where id = $accountId1")
                prepareStatement1.executeQuery().use { statement ->
                    statement.next()
                    if (statement.getInt("amount") - amount < 0)
                        throw Exception("Недостаточно средств")
                    oldVersion = statement.getInt("version")
                }
                val prepareStatement2 =
                    it.prepareStatement("update account1 set amount = amount - $amount, version = version + 1 " +
                            "where id = $accountId1 and version = $oldVersion")
                var result = prepareStatement2.executeUpdate()
                if (result == 0)
                    throw Exception("Нарушение целостности данных")

                val prepareStatement3 =
                    it.prepareStatement("select * from account1 where id = $accountId2")
                prepareStatement3.executeQuery().use { statement ->
                    statement.next()
                    oldVersion = statement.getInt("version")
                }
                val prepareStatement4 =
                    it.prepareStatement("update account1 set amount = amount + $amount, version = version + 1 " +
                            "where id = $accountId2 and version = $oldVersion")
                result = prepareStatement4.executeUpdate()
                if (result == 0)
                    throw Exception("Нарушение целостности данных")
                it.commit()
            } catch (exception: Exception) {
                println(exception.message)
                it.rollback()
            } finally {
                it.autoCommit = autoCommit
            }
        }
    }
}
