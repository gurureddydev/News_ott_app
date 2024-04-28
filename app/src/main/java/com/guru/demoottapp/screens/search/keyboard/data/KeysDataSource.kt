package com.guru.demoottapp.screens.search.keyboard.data

import com.guru.demoottapp.screens.search.keyboard.domain.UtilityKey.*
import com.guru.demoottapp.screens.search.keyboard.domain.SpecialCharactersKey.*
import com.guru.demoottapp.screens.search.keyboard.domain.Digit.*
import com.guru.demoottapp.screens.search.keyboard.domain.Alphabets.*
import com.guru.demoottapp.screens.search.keyboard.domain.Key
import com.guru.demoottapp.screens.search.keyboard.domain.NumericUtilityKey
import com.guru.demoottapp.screens.search.keyboard.domain.SuggestionKey
import com.guru.demoottapp.screens.search.keyboard.domain.TextUtilityKey.*

object KeysDataSource {
    val normalKeys: List<Key> by lazy { constructNormalKeys() }
    val numericMiniKeys: List<Key> by lazy { constructNumericMiniKeys() }
    val numericKeys: List<Key> by lazy { constructNumericKeys() }
    val specialCharactersKeys: List<Key> by lazy { constructSpecialCharactersKeys() }
    val toggleKeys: List<Key> by lazy { createToggleButtonsList() }
    val emailSuggestions: List<Key> by lazy { createEmailSuggestions() }

    private fun createEmailSuggestions(): List<Key> = SuggestionHandler.emails.map {
        SuggestionKey(it, 3)
    }

    private fun createToggleButtonsList() = mutableListOf<Key>().apply {
        add(Uppercase)
    }

    private fun constructSpecialCharactersKeys() = mutableListOf<Key>().apply {
        // Row one
        add(Tide)
        add(Grave)
        add(Pipe)
        add(Bullet)
        add(Root)
        add(PI)
        add(Division)
        add(Multiple)
        add(Paragraph)
        add(Triangle)

        // Row two
        add(Pound)
        add(Cent)
        add(Euro)
        add(Yen)
        add(Caret)
        add(Degree)
        add(Equal)
        add(CurlyBracketLeft)
        add(CurlyBracketRight)
        add(Backlash)

        // Row three
        add(Numeric)
        add(Percent)
        add(CopyRight)
        add(RegisterTrademark)
        add(CheckMark)
        add(BoxBracketLeft)
        add(BoxBracketRight)
        add(ArrowLeft)
        add(ArrowRight)
        add(NumericUtilityKey.Backspace)

        // Row five
        add(ABC)
        add(Clear)
        add(Dot)
        add(Space)
        add(Comma)
//        add(ActionArrow)
    }

    private fun constructNumericKeys() = mutableListOf<Key>().apply {
        // Row one
        add(One)
        add(Two)
        add(Three)
        add(Four)
        add(Five)
        add(Six)
        add(Seven)
        add(Eight)
        add(Nine)
        add(Zero)

        // Row two
        add(Ampersat)
        add(Hash)
        add(Dollar)
        add(Underscore)
        add(And)
        add(Dash)
        add(Plus)
        add(ParenthesesBracketsLeft)
        add(ParenthesesBracketsRight)
        add(BackSlash)

        // Row three
//        add(SpecialCharacters)
        add(Asterisk)
        add(Quotes)
        add(SingleQuotes)
        add(Colon)
        add(Semicolon)
        add(Exclamation)
        add(Question)
        add(Percent)
        add(Backspace)

        // Row five
        add(ABC)
        add(NumericUtilityKey.Space)
        add(Clear)
//        add(Underscore)
//        add(Dash)
//        add(ActionArrow)
    }


    private fun constructNumericMiniKeys() = mutableListOf<Key>().apply {
        // Row one
        add(One)
        add(Two)
        add(Three)

        // Row two
        add(Four)
        add(Five)
        add(Six)
        add(NumericUtilityKey.Space)

        // Row three
        add(Seven)
        add(Eight)
        add(Nine)
        add(NumericUtilityKey.Backspace)

        // Row four
        add(Dot)
        add(Zero)
        add(Comma)
        add(Clear)
    }

    private fun constructNormalKeys() = mutableListOf<Key>().apply {

        // Add keys in alphabetical order

        // Row one
        add(A)
        add(B)
        add(C)
        add(D)
        add(E)
        add(F)
        add(G)
        add(H)
        add(I)
        add(J)

        // Row two
        add(K)
        add(L)
        add(M)
        add(N)
        add(O)
        add(P)
        add(Q)
        add(R)
        add(S)
        add(T)

        // Row three
        add(Numeric)
        add(U)
        add(V)
        add(W)
        add(X)
        add(Y)
        add(Z)

        // Row four
        add(Backspace)

        // Row five
//        add(Uppercase)
//        add(Numeric)
        add(Space)
        add(Clear)
    }
}