package com.sablania.googleimages.pojos

import com.google.gson.annotations.SerializedName

data class GoogleImage(
    @SerializedName("htmlTitle") val htmlTitle: String?,
    @SerializedName("htmlSnippet") val htmlSnippet: String?,
    @SerializedName("pagemap") val pagemap: Pagemap?,
    @SerializedName("link") val link: String
)

class Pagemap(
    @SerializedName("cse_thumbnail") val cse_thumbnail: List<Thumbnail>?,
    @SerializedName("metatags") val metatags: List<Metatags>?
)

class Metatags(
    @SerializedName("og:image") val ogImage: String

)

data class Thumbnail(
    @SerializedName("src") val src: String

)

data class GoogleImageResp(
    @SerializedName("items") val items: List<GoogleImage>
)
