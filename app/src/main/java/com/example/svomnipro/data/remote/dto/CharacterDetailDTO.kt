package com.example.svomnipro.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDetailDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: OriginDTO,
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)

data class OriginDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class LocationDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
