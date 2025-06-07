package com.example.svomnipro.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterResponseDTO(
    @SerializedName("info") val info: PageInfoDTO,
    @SerializedName("results") val results: List<CharacterDTO>
)

data class PageInfoDTO(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)

data class CharacterDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String
)