package com.lanxiang.icyy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.comlib.Const
import com.lanxiang.comlib.Const.Home.FIND_FRAG
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.module_find.FindFragment
import com.lanxiang.module_home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<TestDataBinding>() {
    private val fragments: MutableList<Fragment> = ArrayList()
    private var manager: FragmentManager? = null
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initView() {
        showDefaultTab()
        val homeFragment =
            ARouter.getInstance().build(Const.Home.HOME_HOME_FRAG).navigation() as HomeFragment
        val  AFragment =
            ARouter.getInstance().build(Const.Home.HOME_HOME_FRAG).navigation() as HomeFragment
        val BFragment =
            ARouter.getInstance().build(Const.Home.HOME_HOME_FRAG).navigation() as HomeFragment
        val CFragment =
            ARouter.getInstance().build(FIND_FRAG).navigation() as FindFragment
        val DFragment =
            ARouter.getInstance().build(Const.Home.HOME_HOME_FRAG).navigation() as HomeFragment
        fragments.add(homeFragment)
        fragments.add(AFragment)
        fragments.add(BFragment)
        fragments.add(CFragment)
        fragments.add(DFragment)
        //开启事务
        manager = supportFragmentManager
        val ft = manager!!.beginTransaction()

        //添加首页
//        ft.add(R.id.home_container, homeNewHgFragment).commit();
        if (!homeFragment!!.isAdded) {
            ft.add(R.id.home_container, homeFragment!!, 0.toString()).commitAllowingStateLoss()
        }

    }
    private fun showDefaultTab() {
        radio_button_home.setState(getString(R.string.ui_main_home),
            R.drawable.main_bottom_tab_home_focus, R.drawable.main_bottom_tab_home_normal)
        radio_button_category.setState(getString(R.string.ui_main_news),
            R.drawable.main_bottom_tab_category_focus, R.drawable.main_bottom_tab_category_normal)
        radio_button_vip.setState(getString(R.string.ui_main_friends),
            R.drawable.main_bottom_tab_vip_large_focus, R.drawable.main_bottom_tab_vip_large_normal)
        radio_button_cart.setState(getString(R.string.ui_main_find),
            R.drawable.main_bottom_tab_cart_focus, R.drawable.main_bottom_tab_cart_normal)
        radio_button_cart.stateController.setNum(null)
        radio_button_profile.setState(getString(R.string.ui_main_my),
            R.drawable.main_bottom_tab_personal_focus, R.drawable.main_bottom_tab_personal_normal)
    }
    private fun findFragment(index: Int): Fragment? {
        var fragment = supportFragmentManager.findFragmentByTag(index.toString())
        return if (fragment == null) {
            when (index) {
                0 -> fragment = HomeFragment()
                1 -> fragment = HomeFragment()
                2 -> fragment = HomeFragment()
                3 -> fragment = HomeFragment()
                4 -> fragment = HomeFragment()
            }
            fragment
        } else {
            fragment
        }
    }

    /**
     * 当前选中的tab下标
     */
    private var currentIndex = 0

    /**
     * 选中切换逻辑
     *
     * @param index
     */
    private fun setIndexSelected(index: Int) {
        if (currentIndex == index) {
            return
        }
        val ft = manager!!.beginTransaction()

        //隐藏当前fragment
        ft.hide(fragments[currentIndex])
        //判断是否添加
        if (!fragments[index].isAdded) {
            ft.add(R.id.home_container, fragments[index], index.toString()).show(fragments[index])
        } else {
            ft.show(fragments[index])
        }
        ft.commitAllowingStateLoss()
        manager!!.executePendingTransactions()
        //再次赋值
        currentIndex = index
    }


    override fun initEven() {
        radio_group_button.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_home -> {
                    setIndexSelected(0)
                }
                R.id.radio_button_category -> {
                    setIndexSelected(1)
                }
                R.id.radio_button_vip -> {
                    setIndexSelected(2)
                }
                R.id.radio_button_cart -> {
                    setIndexSelected(3)
                }
                R.id.radio_button_profile -> {
                    setIndexSelected(4)
                }
            }
        }
    }

    override fun doDataChange() {

    }

}