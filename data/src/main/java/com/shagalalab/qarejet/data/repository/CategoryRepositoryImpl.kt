package com.shagalalab.qarejet.data.repository

import com.shagalalab.qarejet.core.utils.Category
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.DbToDomainMapper
import com.shagalalab.qarejet.data.db.DomainToDbMapper
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import io.reactivex.Completable
import io.reactivex.Single

class CategoryRepositoryImpl constructor(var database: Database) : CategoryRepository {

    override fun getAllCategories(): Single<List<Category>> {
        return database
                .categoryDao()
                .getCategories()
                .map(DbToDomainMapper::mapCategoriesList)
    }

    override fun getCategory(id: Long): Single<Category> {
        return database
                .categoryDao()
                .getCategory(id)
                .map(DbToDomainMapper::mapCategory)
    }

    override fun addCategory(category: Category): Completable {
        return Completable.fromAction {
            database
                .categoryDao()
                .insertCategory(DomainToDbMapper.mapCategory(category))
        }
    }

    override fun addCategories(categories: List<Category>): Completable {
        return Completable.fromAction {
            database
                .categoryDao()
                .insertCategories(DomainToDbMapper.mapCategoriesList(categories))
        }
    }

}