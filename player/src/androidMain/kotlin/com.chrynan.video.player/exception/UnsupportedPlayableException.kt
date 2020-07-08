package com.chrynan.video.player.exception

import com.chrynan.video.player.Playable

class UnsupportedPlayableException(playable: Playable? = null) :
    RuntimeException("Unsupported Playable type. playable = $playable")