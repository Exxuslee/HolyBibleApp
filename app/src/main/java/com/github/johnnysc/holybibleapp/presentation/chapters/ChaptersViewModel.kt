package com.github.johnnysc.holybibleapp.presentation.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 13.07.2021
 **/
class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int, String>>
) : ViewModel() {

    fun observeChapters(owner: LifecycleOwner, observer: Observer<List<ChapterUi>>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.fetchChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersUi.map(chaptersCommunication)
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    fun getBookName() = bookCache.read().second
}