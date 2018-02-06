package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Observable
import org.joda.time.DateTime

class GetCategoriesWithAmountUseCase(private val transactionRepository: TransactionRepository)
    : SingleUseCaseWithParameters<Pair<DateTime, Int>, List<CategoryWithAmount>> {

    override fun execute(parameter: Pair<DateTime, Int>) =
        transactionRepository
            .getAllTransactions()
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .filter { DateTime(it.date.time).monthOfYear() == parameter.first.monthOfYear() && DateTime(it.date.time).year() == parameter.first.year() }
            .filter { it.type == parameter.second }
            .toList()
            .map {
                val categoriesList = hashMapOf<Long, CategoryWithAmount>()

                for (t in it) {
                    if (categoriesList.containsKey<Long>(t.id)) {
                        categoriesList[t.id]?.increaseAmount(t.amount)
                    } else {
                        categoriesList[t.id] = CategoryWithAmount(t.type, t.category, t.amount)
                    }
                }

                categoriesList.values.toList()
            }
}
