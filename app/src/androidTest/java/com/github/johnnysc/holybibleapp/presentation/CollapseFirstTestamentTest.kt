package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 */
class CollapseFirstTestamentTest : BaseTest() {

    @Test
    fun test() {
        BooksPage().run {
            firstBookOldTestament.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()
        }
    }
}