package com.exalt.data.mappers

import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {

    private val dateMappers = DateMappers()

    @Test
    fun `Given a Date returns a formatted Date`() {
        val date = "2020-05-24T14:53:17.598Z"
        val actualDate = dateMappers.toDate(date)

        assertEquals("24 mai 2020", actualDate)
    }
}