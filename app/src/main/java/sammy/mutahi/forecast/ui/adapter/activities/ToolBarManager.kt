package sammy.mutahi.forecast.ui.adapter.activities

import android.widget.Toolbar
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.toast
import sammy.mutahi.forecast.R
import sammy.mutahi.forecast.extensions.slideEnter
import sammy.mutahi.forecast.extensions.slideExit
import sammy.mutahi.forecast.ui.adapter.App
import sammy.mutahi.forecast.ui.adapter.utils.ctx

interface ToolBarManager {
    val toolbar: Toolbar
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    private fun createUpDrawable() =
        with(DrawerArrowDrawable(toolbar.ctx)){
            progress = 1f
            this
        }
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })

    }

}