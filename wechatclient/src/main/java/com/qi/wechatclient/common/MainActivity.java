package com.qi.wechatclient.common;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;


import com.qi.wechatclient.R;
import com.qi.wechatclient.fragment.BaseFragment;
import com.qi.wechatclient.fragment.ContactsFragment;
import com.qi.wechatclient.fragment.MessageFragment;
import com.qi.wechatclient.fragment.MineFragment;
import com.qi.wechatclient.view.MainTabViewGroup;


public class MainActivity extends BaseActivity implements MainTabViewGroup.OnTabViewSelectedListener, BaseFragment.OnFragmentInteractionListener {
    private FrameLayout mFragmentContainer;
    private MainTabViewGroup mMainTabViewGroup;
    private MessageFragment mMessageFragment;
    private ContactsFragment mContactsFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mMainTabViewGroup.setOnTabViewSelectedListener(this);
    }

    private void initData() {
    }

    private void initView() {
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mMainTabViewGroup = (MainTabViewGroup) findViewById(R.id.maintabviewgroup);
        mMessageFragment = MessageFragment.newInstance();
        mContactsFragment = ContactsFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mMessageFragment, MessageFragment.class.getSimpleName());
        fragmentTransaction.add(R.id.fragment_container, mContactsFragment, ContactsFragment.class.getSimpleName());
        fragmentTransaction.add(R.id.fragment_container, mMineFragment, MineFragment.class.getSimpleName());
        showFragment(mMessageFragment, fragmentTransaction);
    }

    private synchronized void showFragment(BaseFragment fragment, FragmentTransaction fragmentTransaction) {
        hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
        fragment.onVisiable(true);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mMessageFragment != null) {
            fragmentTransaction.hide(mMessageFragment);
        }
        if (mContactsFragment != null) {
            fragmentTransaction.hide(mContactsFragment);
        }
        if (mMineFragment != null) {
            fragmentTransaction.hide(mMineFragment);
        }
    }

    @Override
    public void onTabViewSelected(int positon) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (positon) {
            case MainTabViewGroup.MESSAGE:
                showFragment(mMessageFragment, fragmentTransaction);
                break;
            case MainTabViewGroup.FRIEND:
                showFragment(mContactsFragment, fragmentTransaction);
                break;
            case MainTabViewGroup.MINE:
                showFragment(mMineFragment, fragmentTransaction);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
