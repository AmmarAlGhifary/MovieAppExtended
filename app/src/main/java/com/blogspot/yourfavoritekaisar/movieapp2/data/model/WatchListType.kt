package com.blogspot.yourfavoritekaisar.movieapp2.data.model

sealed class WatchListType {
    object MovieType : WatchListType()

    object TvShowType : WatchListType()
}
