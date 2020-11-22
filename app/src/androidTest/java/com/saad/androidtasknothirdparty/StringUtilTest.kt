package com.saad.androidtasknothirdparty

import junit.framework.Assert.*
import org.junit.Assert.assertNotEquals
import org.junit.Test

class StringUtilTest  {
    @Test
    fun testSplitTextBySpace() {
        val excpected=hashMapOf("dsd" to 1,"asd" to 2)
        val msg="dsd > asd | asd"
        val actual= StringUtil.splitTextBySpace(msg)
        assertEquals(excpected,actual)
    }
    @Test
    fun testSplitTextBySpaceSymbol() {
        val excpected=hashMapOf("dsd" to 1,"asd" to 2,">" to 1)
        val msg="dsd > asd | asd"
        val actual= StringUtil.splitTextBySpace(msg)
        assertNotEquals(excpected,actual)
    }
    @Test
    fun testSplitTextBySpaceSymbol1() {
        val excpected=hashMapOf("dsd" to 1,"asd" to 2,">" to 1,"|" to 1,"/>" to 1,"}" to 1)
        val msg="dsd > asd | asd /> . }"
        val actual= StringUtil.splitTextBySpace(msg)
        assertNotEquals(excpected,actual)
    }
    @Test
    fun testSplitTextBySpaceSymbol2() {
        val excpected=hashMapOf("d." to 1, "asd" to 2, "s/>" to 1, "f}" to 1, "a|" to 1, "dsd" to 1)
        val msg="dsd > asd a| asd s/> d. f} "
        val actual= StringUtil.splitTextBySpace(msg)
        assertEquals(excpected,actual)
    }
}