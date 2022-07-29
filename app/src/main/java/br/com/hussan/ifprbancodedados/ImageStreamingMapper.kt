package br.com.hussan.ifprbancodedados

import android.content.Context

class ImageStreamingMapper(private val context: Context) {
    private val streamingImages by lazy {
        mapOf(
            context.getString(R.string.netflix) to R.drawable.netflix,
            context.getString(R.string.amazon_prime) to R.drawable.amazon_prime,
            context.getString(R.string.globoplay) to R.drawable.globoplay,
        )
    }

    fun get(string: String): Int? {
        return streamingImages[string]
    }
}