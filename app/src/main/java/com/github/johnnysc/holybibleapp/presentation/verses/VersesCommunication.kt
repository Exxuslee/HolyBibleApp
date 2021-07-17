package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCommunication : Communication<List<VerseUi>> {
    class Base : Communication.Base<List<VerseUi>>(), VersesCommunication
}