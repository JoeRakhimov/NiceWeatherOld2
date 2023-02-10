package com.joerakhimov.niceweather

import com.joerakhimov.niceweather.testing.Game
import com.joerakhimov.niceweather.testing.Question
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GameTest {

    @Mock
    lateinit var question: Question

    @Test
    fun whenAnswering_shouldDelegateToQuestion(){
        val game = Game(question)
        game.answer("OPTION")
        verify(question, times(1)).answer(eq("OPTION"))
    }

    @Test
    fun whenAnsweringCorrectly_shouldIncrementCurrentScore(){
        whenever(question.answer(anyString())).thenReturn(true)
        val game = Game(question)
        game.answer("OPTION")
        assertEquals(1, game.currentScore)
    }

    @Test
    fun whenAnsweringIncorrectly_shouldIncrementCurrentScore(){
        whenever(question.answer(anyString())).thenReturn(false)
        val game = Game(question)
        game.answer("OPTION")
        assertEquals(0, game.currentScore)
    }


}