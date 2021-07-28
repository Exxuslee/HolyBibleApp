package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.BuildString
import com.github.johnnysc.holybibleapp.core.ErrorType

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesDomain {

    abstract fun <T> map(mapper: VersesDomainToUiMapper<T>): T

    data class Success(
        private val list: List<VerseDomain>,
        private val title: BuildString,
        private val chapterNumber: Int
    ) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) =
            mapper.map(Triple(list, title, chapterNumber))
    }

    data class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}