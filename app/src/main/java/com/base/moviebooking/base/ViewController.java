package com.base.moviebooking.base;


import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewController {

    private final int layoutId;
    private final FragmentManager fragmentManager;
    private final List<BaseFragment> fragmentList = new ArrayList<>();
    private final List<Class<? extends BaseFragment>> classList = new ArrayList<>();
    private BaseFragment currentFragment;

    public ViewController(FragmentManager fragmentManager, int layoutId) {
        this.fragmentManager = fragmentManager;
        this.layoutId = layoutId;
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void replaceFragment(Class<? extends BaseFragment> type, HashMap<String, Object> data) {
        if (currentFragment != null && currentFragment.getClass().equals(type)) {
            return;
        }

        currentFragment = instantiateFragment(type, data);
        if (currentFragment == null) return;

        fragmentManager.beginTransaction().replace(layoutId, currentFragment).commit();

        fragmentList.clear();
        fragmentList.add(currentFragment);
        classList.clear();
        classList.add(type);
    }

    public void addFragment(Class<? extends BaseFragment> type, HashMap<String, Object> data) {
        if (currentFragment != null && currentFragment.getClass().equals(type)) {
            return;
        }

        if (currentFragment != null) {
            fragmentManager.beginTransaction().hide(currentFragment).commit();
        }

        currentFragment = instantiateFragment(type, data);
        if (currentFragment == null) return;

        fragmentManager.beginTransaction().add(layoutId, currentFragment).commit();

        fragmentList.add(currentFragment);
        classList.add(type);
    }

    public boolean backFromAddFragment(HashMap<String, Object> data) {
        if (fragmentList.size() >= 2) {
            fragmentList.remove(fragmentList.size() - 1);
            classList.remove(classList.size() - 1);

            fragmentManager.beginTransaction().remove(currentFragment).commit();
            currentFragment = fragmentList.get(fragmentList.size() - 1);

            if (data != null) currentFragment.setData(data);
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);

            fragmentManager.beginTransaction().show(currentFragment).commit();
            currentFragment.backFromAddFragment();
            return true;
        }
        return false;
    }

    public boolean backTwoStepFromAddFragment(HashMap<String, Object> data) {
        if (fragmentList.size() >= 3) {
            fragmentList.remove(fragmentList.size() - 1);
            fragmentList.remove(fragmentList.size() - 1);
            classList.remove(classList.size() - 1);
            classList.remove(classList.size() - 1);

            fragmentManager.beginTransaction().remove(currentFragment).commit();
            currentFragment = fragmentList.get(fragmentList.size() - 1);

            if (data != null) currentFragment.setData(data);
            currentFragment.setViewController(this);
            currentFragment.setUserVisibleHint(true);

            fragmentManager.beginTransaction().show(currentFragment).commit();
            currentFragment.backFromAddFragment();
            return true;
        }
        return false;
    }

    public boolean backFromReplaceFragment(HashMap<String, Object> data) {
        if (classList.size() >= 2) {
            classList.remove(classList.size() - 1);
            Class<? extends BaseFragment> previousType = classList.get(classList.size() - 1);

            currentFragment = instantiateFragment(previousType, data);
            if (currentFragment == null) return false;

            fragmentList.clear();
            fragmentList.add(currentFragment);

            fragmentManager.beginTransaction().replace(layoutId, currentFragment).commit();
            return true;
        }
        return false;
    }

    private BaseFragment instantiateFragment(Class<? extends BaseFragment> type, HashMap<String, Object> data) {
        try {
            BaseFragment fragment = type.newInstance(); // You may use type.getDeclaredConstructor().newInstance() for Java 9+
            if (data != null) fragment.setData(data);
            fragment.setViewController(this);
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

