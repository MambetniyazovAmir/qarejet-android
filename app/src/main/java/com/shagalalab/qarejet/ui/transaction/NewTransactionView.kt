package com.shagalalab.qarejet.ui.transaction

import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category

interface NewTransactionView {
    fun updateAccounts(accounts: List<Account>)
    fun updateCategories(categories: List<Category>)
    fun finishActivity()
    fun showDateChooser()
    fun showTimeChooser()
    fun showMessage(message: String)
}