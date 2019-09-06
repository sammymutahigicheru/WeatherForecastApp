package sammy.mutahi.weatherforecast.ui.utils

import android.graphics.drawable.Drawable
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.ui.App
import sammy.mutahi.weatherforecast.ui.activities.Settings
import sammy.mutahi.weatherforecast.ui.utils.extensions.ctx
import sammy.mutahi.weatherforecast.ui.utils.extensions.slideEnter
import sammy.mutahi.weatherforecast.ui.utils.extensions.slideExit

interface ToolBarManager {
    val toolbar:Toolbar
    var toolbarTitle:String
        get() = toolbar.title.toString()
        set(value){
            toolbar.title = value
        }
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<Settings>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun createUpDrawable(): Drawable? =with(DrawerArrowDrawable(toolbar.ctx)){
        progress = 1f
        this
    }
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }

        })
    }


}