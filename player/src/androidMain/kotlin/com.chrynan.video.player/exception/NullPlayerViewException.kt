package com.chrynan.video.player.exception

class NullPlayerViewException :
    RuntimeException("PlayerView property of MediaPlayerView cannot be null. Make sure the PlayerView is available before attempting to attach the MediaPlayerView.")