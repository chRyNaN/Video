package com.chrynan.video.player

import com.chrynan.video.player.converter.PlayableConverter
import com.chrynan.video.player.exception.UnsupportedPlayableException
import com.google.android.exoplayer2.source.ConcatenatingMediaSource

class AndroidPlaylist<P : Playable>(private val converter: PlayableConverter) : Playlist<P> {

    private val internalList = mutableListOf<P>()
    private val concatenatingMediaSource = ConcatenatingMediaSource()

    override val size: Int
        get() = internalList.size

    override fun contains(element: P) = internalList.contains(element)

    override fun containsAll(elements: Collection<P>) = internalList.containsAll(elements)

    override fun get(index: Int): P = internalList[index]

    override fun indexOf(element: P) = internalList.indexOf(element)

    override fun isEmpty() = internalList.isEmpty()

    override fun iterator() = internalList.iterator()

    override fun lastIndexOf(element: P) = internalList.lastIndexOf(element)

    override fun listIterator() = internalList.listIterator()

    override fun listIterator(index: Int) = internalList.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = internalList.subList(fromIndex, toIndex)

    override fun add(element: P): Boolean {
        val convertedElement =
            converter.convert(element) ?: throw UnsupportedPlayableException(element)

        val added = internalList.add(element)

        concatenatingMediaSource.addMediaSource(convertedElement)

        return added
    }

    override fun add(index: Int, element: P) {
        val convertedElement =
            converter.convert(element) ?: throw UnsupportedPlayableException(element)

        internalList.add(index, element)

        concatenatingMediaSource.addMediaSource(index, convertedElement)
    }

    override fun addAll(index: Int, elements: Collection<P>): Boolean {
        val convertedElements =
            elements.map { converter.convert(it) ?: throw UnsupportedPlayableException(it) }

        val added = internalList.addAll(index, elements)

        concatenatingMediaSource.addMediaSources(index, convertedElements)

        return added
    }

    override fun addAll(elements: Collection<P>): Boolean {
        val convertedElements =
            elements.map { converter.convert(it) ?: throw UnsupportedPlayableException(it) }

        val added = internalList.addAll(elements)

        concatenatingMediaSource.addMediaSources(convertedElements)

        return added
    }

    override fun clear() {
        internalList.clear()
        concatenatingMediaSource.clear()
    }

    override fun remove(element: P): Boolean {
        val index = internalList.indexOf(element)

        if (index != -1) {
            internalList.removeAt(index)
            concatenatingMediaSource.removeMediaSource(index)

            return true
        }

        return false
    }

    override fun removeAll(elements: Collection<P>): Boolean {
        val modified = internalList.removeAll(elements)

        if (modified) {
            concatenatingMediaSource.clear()
            concatenatingMediaSource.addMediaSources(internalList.map { converter.convert(it) })
        }

        return modified
    }

    override fun removeAt(index: Int): P {
        val item = internalList.removeAt(index)

        concatenatingMediaSource.removeMediaSource(index)

        return item
    }

    override fun retainAll(elements: Collection<P>): Boolean {
        val modified = internalList.retainAll(elements)

        if (modified) {
            concatenatingMediaSource.clear()
            concatenatingMediaSource.addMediaSources(internalList.map { converter.convert(it) })
        }

        return modified
    }

    override fun set(index: Int, element: P): P {
        val previous = internalList[index]

        internalList[index] = element

        concatenatingMediaSource.removeMediaSource(index)
        concatenatingMediaSource.addMediaSource(index, converter.convert(element))

        return previous
    }

    override fun move(currentIndex: Int, newIndex: Int) {
        concatenatingMediaSource.moveMediaSource(currentIndex, newIndex)

        val item = internalList.removeAt(currentIndex)

        internalList.add(newIndex, item)
    }
}
