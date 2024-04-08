 package com.guru.player_library

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource

 @UnstableApi
class ExoPlayer(private val context: Context, private val videoUrl: String) : Player {
    private val exoPlayer: SimpleExoPlayer by lazy {
        SimpleExoPlayer.Builder(context).build().apply {
            val dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, context.packageName))
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            val mediaSource = buildMediaSource(mediaItem, dataSourceFactory)
            prepare(mediaSource)
        }
    }

    private fun buildMediaSource(mediaItem: MediaItem, dataSourceFactory: DefaultDataSourceFactory): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)
    }

    override fun play() {
        exoPlayer.playWhenReady = true
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
    }
}
