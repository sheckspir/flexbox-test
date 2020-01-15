package com.example.liangtg.dev;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.COLUMN);
        recyclerView.setLayoutManager(layoutManager);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        // TODO KAY определить как layoutManager говорит что нужно скролиться или нет
        recyclerView.setAdapter(new IAdapter(point.x));
    }

    private class IAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
        int[] colors = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};
        private int screenWidth;

        public IAdapter(int screenWidth) {
            this.screenWidth = screenWidth;
        }

        @NonNull
        @Override
        public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdapterViewHolder(getLayoutInflater().inflate(R.layout.item_q, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
            FlexboxLayoutManager.LayoutParams lp = (FlexboxLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            // TODO KAY posible hot fix
            Log.d("TAG", "onBindViewHolder.position = " + position + " lp width = " + lp.width);
//            lp.width = screenWidth;
            holder.itemView.setBackgroundColor(colors[position % colors.length]);
            holder.title.setText("title" + position);
            holder.content.setText("content" + position);
            for (int i = 0; i < position + 1; i++) {
                holder.content.append("\n" + position);
            }
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }


}
