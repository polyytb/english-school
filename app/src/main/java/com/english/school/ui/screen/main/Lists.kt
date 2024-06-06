package com.english.school.ui.screen.main

import com.english.school.data.storage.models.Words
import com.english.school.ui.screen.rules.Rule

val loadWordList = listOf(
    Words(
        word = "fly",
        transcriptions = "flaɪ",
        translate = "летать",
        variants = "[бороться, летать, освещать, будить]",
        status = ""
    ),
    Words(
        word = "fight",
        transcriptions = "faɪt",
        translate = "бороться",
        variants = "[рассказать, трясти, бороться, рассказать]",
        status = ""
    ),
    Words(
        word = "hit",
        transcriptions = "hɪt",
        translate = "ударять",
        variants = "[ударять, летать, освещать, бороться]",
        status = ""
    ),
    Words(
        word = "light",
        transcriptions = "laɪt",
        translate = "освещать",
        variants = "[обманывать, ударять, освещать, освещать]",
        status = ""
    ),
    Words(
        word = "sell",
        transcriptions = "продавать",
        translate = "sɛl",
        variants = "[бороться, летать, продавать, будить]",
        status = ""
    ),
    Words(
        word = "shake",
        transcriptions = "ʃeɪk",
        translate = "трясти",
        variants = "[ударять, продавать, освещать, трясти]",
        status = ""
    ),
    Words(
        word = "tell",
        transcriptions = "tɛl",
        translate = "рассказать",
        variants = "[освещать, летать, освещать, рассказать]",
        status = ""
    ),
    Words(
        word = "wind",
        transcriptions = "waɪnd",
        translate = "обманывать",
        variants = "[обманывать, продавать, освещать, ударять]",
        status = ""
    ),
    Words(
        word = "wake",
        transcriptions = "weɪk",
        translate = "будить",
        variants = "[трясти, рассказать, будить, бороться]",
        status = ""
    ),
    Words(
        word = "run",
        transcriptions = "rʌn",
        translate = "бежать",
        variants = "[бежать, освещать, прыгать, говорить]",
        status = ""
    ),
    Words(
        word = "read",
        transcriptions = "riːd",
        translate = "читать",
        variants = "[читать, слушать, освещать, смеяться]",
        status = ""
    ),
    Words(
        word = "write",
        transcriptions = "raɪt",
        translate = "писать",
        variants = "[писать, рассказывать, продавать, ударять]",
        status = ""
    ),
    Words(
        word = "jump",
        transcriptions = "dʒʌmp",
        translate = "прыгать",
        variants = "[прыгать, бежать, продавать, смотреть]",
        status = ""
    ),
    Words(
        word = "eat",
        transcriptions = "iːt",
        translate = "есть",
        variants = "[есть, освещать, трясти, смеяться]",
        status = ""
    ),
    Words(
        word = "speak",
        transcriptions = "spiːk",
        translate = "говорить",
        variants = "[говорить, рассказывать, слушать, бороться]",
        status = ""
    ),
    Words(
        word = "sleep",
        transcriptions = "sliːp",
        translate = "спать",
        variants = "[спать, бежать, летать, смеяться]",
        status = ""
    ),
    Words(
        word = "fly",
        transcriptions = "flaɪ",
        translate = "летать",
        variants = "[летать, прыгать, слушать, продавать]",
        status = ""
    ),
    Words(
        word = "sell",
        transcriptions = "sɛl",
        translate = "продавать",
        variants = "[продавать, освещать, говорить, смотреть]",
        status = ""
    ),
    Words(
        word = "listen",
        transcriptions = "ˈlɪsən",
        translate = "слушать",
        variants = "[слушать, читать, летать, прыгать]",
        status = ""
    )
)

val ruleList = listOf(
    Rule(
        "Использование артиклей (a, an, the)",
        "A и an используются перед неисчисляемыми и исчисляемыми существительными в единственном числе, когда они упоминаются впервые или являются неопределенными. A используется перед словами, начинающимися с согласного звука, а an перед словами, начинающимися с гласного звука (например, a book, an apple).\n" +
                "The используется перед конкретными существительными, которые уже известны или упомянуты ранее (например, the book we talked about)."
    ),
    Rule(
        "Порядок слов в предложении (Subject-Verb-Object)",
        "В английском языке стандартный порядок слов в утвердительном предложении – это подлежащее (subject), сказуемое (verb) и дополнение (object). Например: She (subject) reads (verb) a book (object)."
    ),
    Rule(
        "Использование времен (Present Simple, Present Continuous)",
        "Present Simple используется для выражения привычных действий, общеизвестных фактов и расписаний. Формула: подлежащее + основной глагол (в третьем лице единственного числа добавляется -s). Например: I eat breakfast at 7 AM.\n" +
                "Present Continuous используется для описания действий, происходящих в данный момент или временных ситуаций. Формула: подлежащее + am/is/are + глагол с окончанием -ing. Например: She is reading a book."
    ),
    Rule(
        "Использование вспомогательных глаголов (do, does, did)",
        "Вспомогательные глаголы do, does и did используются для образования вопросов и отрицаний в Present Simple и Past Simple.\n" + "Например: Do you like pizza? (вопрос в Present Simple), He doesn’t (does not) play football (отрицание в Present Simple), Did you see the movie? (вопрос в Past Simple)."
    ),
    Rule(
        "Местоимения и притяжательные местоимения (I, you, he, she, it, we, they)",
        "Личные местоимения используются для замены существительных, чтобы избежать повторения. Например: John is my friend. He is a doctor.\n" +
                "Притяжательные местоимения указывают на принадлежность. Например: This is my book."
    )
)