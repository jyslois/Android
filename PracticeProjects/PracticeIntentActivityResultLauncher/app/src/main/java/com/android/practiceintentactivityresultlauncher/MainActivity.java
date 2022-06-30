package com.android.practiceintentactivityresultlauncher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.practiceintentactivityresultlauncher.databinding.ActivityMainBinding;
import com.android.practiceintentactivityresultlauncher.databinding.ItemMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> datas;
    ActivityResultLauncher<Intent> resultLauncher;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // MainActivity에서 화면에 항목을 출력하고, 출력된 항목 중 하나를 사용자가 클릭했을 때 DetailActivity를
        // 인텐트로 실행한다. 실행 시 항목 정보를 DetailActivity에 Extra 데이터로 넘겨주며, 결과를 되돌려받기 위해
        // ActivityResultLauncher로 인텐트를 발생시켜 준다.

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        String txt = category + " " + intent.getStringExtra("result");
                        Toast.makeText(MainActivity.this, txt, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // 데이터는 DBHelper.java에서 가상 데이터로 데이터베이스에 삽입되었다
        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select location from tb_data where category='0'", null);

        // ArrayList datas에 데이터베이스의 첫번째 콜룸 값들 담기
        datas = new ArrayList<>();
        while (cursor.moveToNext()){
            datas.add(cursor.getString(0));
        }
        db.close();

        // RecyclerView 설정
        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation()));
        binding.mainRecyclerView.setAdapter(new MyMainAdapter(datas));
    }

    // RecyclerView의 필수 구성 요소에는 ViewHolder(각 항목 구성 뷰), Adaptor (항목 구성), LayoutManager(항목이 배치) 있다.


    private class MyMainAdapter extends RecyclerView.Adapter<MyMainViewHolder> {
        private List<String> list;

        public MyMainAdapter(List<String> list) {
            this.list = list;
        }

        // ViewHolder 객체 반환
        @Override
        public MyMainViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ItemMainBinding binding = ItemMainBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new MyMainViewHolder(binding);
        }

        // 각 항목을 구성하기 위해서 호출
        @Override
        public void onBindViewHolder(MyMainViewHolder viewHolder, int position) {
            String text = list.get(position);
            // 항목 뷰의 text을 설정
            viewHolder.binding.itemMainView.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    // ViewHolder의 역활은 항목을 구성하기 위한 뷰들을 findViewById 해주는 역할을 한다.
    private class MyMainViewHolder extends RecyclerView.ViewHolder {
        // 항목을 구성하기 위한 뷰 (TextView)
        ItemMainBinding binding;

        // 생성자
        public MyMainViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // 항목의 뷰를 사용자가 클릭했을 때 이벤트: DetailActivity를 인텐트로 실행한다.
            // 실행 시 항목 정보를 DetailActivity에 Extra 데이터로 넘겨준다.
            binding.getRoot().setOnClickListener(view -> {
                // 뷰의 텍스트로 지정된 데이터를 String으로 변환해 category에 저장
                category = binding.itemMainView.getText().toString();
                // 인벤트 생성
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                // 항목 정보인 category를 Extra Data로 DetailActivity에 넘겨주기
                intent.putExtra("category", category);
                // ActivityResultLauncher로 인텐트를 발생시킨다.
                resultLauncher.launch(intent);
            });

        }
    }


}