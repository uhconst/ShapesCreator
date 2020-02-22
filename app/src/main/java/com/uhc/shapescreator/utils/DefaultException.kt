package com.uhc.shapescreator.utils

class DefaultException(
    override val message: String = "Unexpected Error"
) : Exception()