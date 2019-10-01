package com.example.android3.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3.R;
import com.example.android3.data.models.Repo;

import java.util.ArrayList;
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private List<Repo> repos = new ArrayList<>();

    public void setRepos(List<Repo> lr){
        repos.clear();
        repos.addAll(lr);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView url;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repo_name);
            url = itemView.findViewById(R.id.url);
            description = itemView.findViewById(R.id.description);
        }

        void bind(Repo repo){
            name.setText(repo.getName());
            description.setText(repo.getDescription());
            url.setText(repo.getUrl());
        }
    }
}
