package com.index.book;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

/**
 * book项目的主Activity，所有的Fragment都嵌入在这里。
 *
 * @author michael chu
 */
public class MainActivity extends Activity implements OnClickListener {

    /**
     * 用于展示的Fragment
     */
    private TitleFragment titleFragment;
    private CategoryFragment categoryFragment;
    private AuthorFragment authorFragment;
    private RecentFragment recentFragment;

    /**
     * 在Tab布局上显示的控件
     */
    private View titleLayout;
    private View categoryLayout;
    private View authorLayout;
    private View recentLayout;

    /**
     * 在Tab布局上显示的控件
     */
    private TextView titleText;
    private TextView categoryText;
    private TextView authorText;
    private TextView recentText;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        titleLayout = findViewById(R.id.book_title_layout);
        categoryLayout = findViewById(R.id.book_category_layout);
        authorLayout = findViewById(R.id.book_author_layout);
        recentLayout = findViewById(R.id.book_recent_layout);
        titleText = (TextView) findViewById(R.id.book_title_text);
        categoryText = (TextView) findViewById(R.id.book_category_text);
        authorText = (TextView) findViewById(R.id.book_author_text);
        recentText = (TextView) findViewById(R.id.book_recent_text);
        titleLayout.setOnClickListener(this);
        categoryLayout.setOnClickListener(this);
        authorLayout.setOnClickListener(this);
        recentLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_title_layout:
                // 当点击了Title tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.book_category_layout:
                // 当点击了Category tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.book_author_layout:
                // 当点击了Author tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.book_recent_layout:
                // 当点击了Recent tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示Title，1表示Category，2表示Author，3表示Recent。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                titleText.setTextColor(Color.WHITE);
                if (titleFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    titleFragment = new TitleFragment();
                    transaction.add(R.id.content, titleFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(titleFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                categoryText.setTextColor(Color.WHITE);
                if (categoryFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    categoryFragment = new CategoryFragment();
                    transaction.add(R.id.content, categoryFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(categoryFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                authorText.setTextColor(Color.WHITE);
                if (authorFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    authorFragment = new AuthorFragment();
                    transaction.add(R.id.content, authorFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(authorFragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                recentText.setTextColor(Color.WHITE);
                if (recentFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    recentFragment = new RecentFragment();
                    transaction.add(R.id.content, recentFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(recentFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        titleText.setTextColor(Color.parseColor("#82858b"));
        categoryText.setTextColor(Color.parseColor("#82858b"));
        authorText.setTextColor(Color.parseColor("#82858b"));
        recentText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (titleFragment != null) {
            transaction.hide(titleFragment);
        }
        if (categoryFragment != null) {
            transaction.hide(categoryFragment);
        }
        if (authorFragment != null) {
            transaction.hide(authorFragment);
        }
        if (recentFragment != null) {
            transaction.hide(recentFragment);
        }
    }
}
