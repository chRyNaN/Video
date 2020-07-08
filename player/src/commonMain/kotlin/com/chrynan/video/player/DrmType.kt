package com.chrynan.video.player

enum class DrmType(val typeName: String) {

    CENC(typeName = "cenc"),
    CBCS(typeName = "cbcs"),
    CBC1(typeName = "cbc1"),
    CENS(typeName = "cens"),
    CLEAR_KEY(typeName = "ClearKey"),
    PLAYREADY_SL2000(typeName = "PlayReady SL2000");

    companion object {

        fun fromTypeName(name: String): DrmType? =
            values().firstOrNull { it.typeName.toLowerCase() == name.toLowerCase() }
                ?: CLEAR_KEY.takeIf { name.toLowerCase() == "clear_key" }
                ?: CLEAR_KEY.takeIf { name.toLowerCase() == "clear key" }
                ?: PLAYREADY_SL2000.takeIf { name.toLowerCase() == "playready_sl2000" }
                ?: PLAYREADY_SL2000.takeIf { name.toLowerCase() == "play_ready" }
                ?: PLAYREADY_SL2000.takeIf { name.toLowerCase() == "playready" }
                ?: CENC.takeIf { name.toLowerCase() == "widevine" }
                ?: CENC.takeIf { name.toLowerCase() == "wide_vine" }
                ?: CENC.takeIf { name.toLowerCase() == "wide vine" }
    }
}