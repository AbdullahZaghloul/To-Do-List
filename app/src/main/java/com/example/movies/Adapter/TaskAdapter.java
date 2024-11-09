package com.example.movies.Adapter;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.Room.TaskEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    List<TaskEntity> tasks = new ArrayList<>();


    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Log.d("taskNam", tasks.get(position).getName());
        holder.name.setText(tasks.get(position).getName());
        holder.desc.setText(tasks.get(position).getDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.desc.getVisibility() == View.GONE){
                    holder.image.setImageResource(R.drawable.bottom_arrow);
                    holder.desc.setVisibility(View.VISIBLE);
                }
                else if(holder.desc.getVisibility() == View.VISIBLE){
                    holder.image.setImageResource(R.drawable.next_arrow);
                    holder.desc.setVisibility(View.GONE);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setList(List<TaskEntity> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView name;
        TextView desc;
        CardView cardView;

        ImageView image;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskItem_name_tv);
            desc = itemView.findViewById(R.id.taskItem_desc_tv);
            image = itemView.findViewById(R.id.taskItem_nextArrow_imageView);
            cardView = itemView.findViewById(R.id.taskItem_cardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle(R.string.headerContext_text);
            contextMenu.add(this.getAdapterPosition(),121,0,R.string.DeleteContext_text);
            contextMenu.add(this.getAdapterPosition(),122,1,R.string.UpdateContext_text);

        }
    }

    public TaskEntity removeTask(int position){
        TaskEntity task = tasks.get(position);
        tasks.remove(position);
        notifyDataSetChanged();
        return  task;
    }
    public TaskEntity getTask(int position){
        return tasks.get(position);
    }

}
