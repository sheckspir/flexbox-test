package com.example.liangtg.dev;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

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
        recyclerView.setAdapter(new IAdapter());
    }

    private class IAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
        int[] colors = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};

        @NonNull
        @Override
        public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdapterViewHolder(getLayoutInflater().inflate(R.layout.item_q, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
            FlexboxLayoutManager.LayoutParams lp = (FlexboxLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
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
