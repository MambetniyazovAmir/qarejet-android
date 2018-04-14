package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Completable

class AddTransactionUseCase(private val transactionRepository: TransactionRepository)
    : CompletableUseCaseWithParameters<Transaction> {

    override fun execute(parameter: Transaction): Completable = transactionRepository.addTransaction(parameter)

}