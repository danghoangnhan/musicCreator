package code.name.monkey.retromusic.fragments.mainactivity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import code.name.monkey.retromusic.App
import code.name.monkey.retromusic.R
import code.name.monkey.retromusic.adapter.song.ShuffleButtonSongAdapter
import code.name.monkey.retromusic.adapter.song.SongAdapter
import code.name.monkey.retromusic.fragments.base.AbsLibraryPagerRecyclerViewCustomGridSizeFragment
import code.name.monkey.retromusic.model.Song
import code.name.monkey.retromusic.mvp.presenter.SongPresenter
import code.name.monkey.retromusic.mvp.presenter.SongView
import code.name.monkey.retromusic.util.PreferenceUtil
import java.util.*
import javax.inject.Inject

class SongsFragment : AbsLibraryPagerRecyclerViewCustomGridSizeFragment<SongAdapter, GridLayoutManager>(), SongView {

    @Inject
    lateinit var songPresenter: SongPresenter


    override val emptyMessage: Int
        get() = R.string.no_songs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.musicComponent.inject(this)

        songPresenter.attachView(this)
    }

    override fun createLayoutManager(): GridLayoutManager {
        return GridLayoutManager(activity, getGridSize())
    }

    override fun createAdapter(): SongAdapter {
        val itemLayoutRes = itemLayoutRes
        notifyLayoutResChanged(itemLayoutRes)
        val usePalette = loadUsePalette()

        val dataSet = if (adapter == null) ArrayList() else adapter!!.dataSet

        return if (getGridSize() <= maxGridSizeForList) {
            ShuffleButtonSongAdapter(libraryFragment.mainActivity, dataSet, itemLayoutRes, usePalette, libraryFragment)
        } else SongAdapter(libraryFragment.mainActivity, dataSet, itemLayoutRes, usePalette, libraryFragment)
    }

    override fun songs(songs: ArrayList<Song>) {
        adapter?.swapDataSet(songs)
    }

    override fun onMediaStoreChanged() {
        songPresenter.loadSongs()
    }

    override fun loadGridSize(): Int {
        return PreferenceUtil.getInstance().getSongGridSize(activity!!)
    }

    override fun saveGridSize(gridColumns: Int) {
        PreferenceUtil.getInstance().setSongGridSize(gridColumns)
    }

    override fun loadGridSizeLand(): Int {
        return PreferenceUtil.getInstance().getSongGridSizeLand(activity!!)
    }

    override fun saveGridSizeLand(gridColumns: Int) {
        PreferenceUtil.getInstance().setSongGridSizeLand(gridColumns)
    }

    public override fun saveUsePalette(usePalette: Boolean) {
        PreferenceUtil.getInstance().setSongColoredFooters(usePalette)
    }

    public override fun loadUsePalette(): Boolean {
        return PreferenceUtil.getInstance().songColoredFooters()
    }

    public override fun setUsePalette(usePalette: Boolean) {
        adapter!!.usePalette(usePalette)
    }

    override fun setGridSize(gridSize: Int) {
        layoutManager!!.spanCount = gridSize
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if (adapter!!.dataSet.isEmpty()) {
            songPresenter.loadSongs()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        songPresenter.detachView()
    }

    override fun showEmptyView() {
        adapter!!.swapDataSet(ArrayList())
    }

    override fun loadSortOrder(): String {
        return PreferenceUtil.getInstance().songSortOrder
    }

    override fun saveSortOrder(sortOrder: String) {
        PreferenceUtil.getInstance().songSortOrder = sortOrder
    }

    override fun setSortOrder(sortOrder: String) {
        songPresenter.loadSongs()
    }

    companion object {

        fun newInstance(): SongsFragment {
            val args = Bundle()
            val fragment = SongsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
