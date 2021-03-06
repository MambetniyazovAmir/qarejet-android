package com.shagalalab.qarejet.data.repository

import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.DbToDomainMapper
import com.shagalalab.qarejet.data.db.DomainToDbMapper
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Completable
import io.reactivex.Single

class TransactionRepositoryImpl (private val database : Database) : TransactionRepository {

    override fun getTransaction(id: Long): Single<Transaction> {
        return database
                .transactionDao()
                .getTransaction(id)
                .map(DbToDomainMapper::mapTransaction)
    }

    override fun getAllTransactions(): Single<List<Transaction>> {
        return database
                .transactionDao()
                .getTransactions()
                .map(DbToDomainMapper::mapTransactionList)
    }

    override fun getTransactionsWithinDateByType(from: Long, to: Long, categoryType: Int): Single<List<Transaction>> {
        return database
            .transactionDao()
            .getTransactionsWithinDateByType(from, to, categoryType)
            .map(DbToDomainMapper::mapTransactionList)
    }

    override fun getTransactionsWithinDateByCategory(from: Long, to: Long, categoryId: Long): Single<List<Transaction>> {
        return database
            .transactionDao()
            .getTransactionsWithinDateByCategory(from, to, categoryId)
            .map(DbToDomainMapper::mapTransactionList)
    }

    override fun addTransaction(transaction: Transaction): Completable {
        return Completable.fromAction {
            database
                .transactionDao()
                .insertTransaction(DomainToDbMapper.mapTransaction(transaction))
        }
    }

    override fun addTransactions(transactions: List<Transaction>): Completable {
        return Completable.fromAction {
            database
                .transactionDao()
                .insertTransactions(DomainToDbMapper.mapTransactionsList(transactions))
        }
    }
}