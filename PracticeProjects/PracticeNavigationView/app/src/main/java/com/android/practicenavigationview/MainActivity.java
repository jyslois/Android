package com.android.practicenavigationview;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.practicenavigationview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ActionBar DrawerToggle - DrawerLayout을 열고 닫기 위한 아이콘을 툴바 왼쪽에 제공
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();

        // 이벤트 처리
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.menu_drawer_home) {
                showToast("NavigationDrawer...home...");
            } else if (id == R.id.menu_drawer_manage) {
                showToast("NavigationDrawer...message...");
            } else if (id == R.id.menu_drawer_add) {
                showToast("NavigationDrawer...add..");
            }
            return false;
        });

    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // ActionBarDrawerToggle 아이콘이 나오기는 하지만, 실제 아이콘 클릭 시
    // DrawerLayout이 열리거나 닫히지는 않는다. 메뉴 이벤트 함수를 정의하여,
    // 다음처럼 작성해야 실제 열리거나 닫히게 된다.
    // onOptionsItemSelected() 함수는 메뉴 클릭 이벤트를 처리한다.
    // 내부적으로 ActionBar DrawaerToggle이 메뉴 이벤트로 처리되기 때문이다.
    // onOptionsitemSelected() 함수 내의 이벤트가 토글에서 발생한 거라면,
    // 반환해서 메뉴 이벤트 로직에서 벗어나게 해주어야 한다.
    // 그래야 토글 버튼에 의해서 drawer가 제어가 된다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
