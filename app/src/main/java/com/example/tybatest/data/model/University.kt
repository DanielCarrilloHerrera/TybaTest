package com.example.tybatest.data.model



/*{
      "alpha_two_code": "US",
      "domains": [
        "marywood.edu"
      ],
      "country": "United States",
      "state-province": null,
      "web_pages": [
        "http://www.marywood.edu"
      ],
      "name": "Marywood University"
    }*/
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class University (
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String = "US",
    @SerializedName("domains")
    val domains: List<String> = listOf(),
    @SerializedName("country")
    val country: String = "",
    @SerializedName("state-province")
    val stateProvince: String = "",
    @SerializedName("web_pages")
    val webPages: List<String> = listOf(),
    @SerializedName("name")
    val name: String = "",
): Parcelable