package com.doseyenc.main.data.model


import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("dates")
    val dates: com.doseyenc.main.data.model.NowPlayingResponse.Dates?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<_root_ide_package_.com.doseyenc.main.data.model.Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) {
    data class Dates(
        @SerializedName("maximum")
        val maximum: String?,
        @SerializedName("minimum")
        val minimum: String?
    )
}