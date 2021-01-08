package com.example.learngerman.ui.words;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.learngerman.listner.WordsListListner;
import com.example.learngerman.model.Term;
import com.example.learngerman.R;
import com.example.learngerman.databinding.WordItemBinding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder> implements Filterable {

    private final List<Term> wordsList;
    List<Term> wordsListAll;
    private final LayoutInflater layoutInflater;
    private final WordsListListner wordsListListner;

    public WordsAdapter(List<Term> wordsList, Context context, WordsListListner wordsListListner) {
        this.wordsList = wordsList;
        layoutInflater = LayoutInflater.from(context);
        this.wordsListListner = wordsListListner;
        wordsListAll = new ArrayList<>(wordsList);
    }

    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordItemBinding wordItemBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.word_item, parent, false);
        return new WordsViewHolder(wordItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull WordsViewHolder holder, int position) {
        holder.bindWord(wordsList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public class WordsViewHolder extends RecyclerView.ViewHolder {
        WordItemBinding wordItemBinding;

        public WordsViewHolder(@NonNull WordItemBinding itemView) {
            super((itemView.getRoot()));
            wordItemBinding = itemView;
        }

        public void bindWord(Term term) {
            wordItemBinding.setTerm(term);
            wordItemBinding.executePendingBindings();
            wordItemBinding.wordTv.setOnClickListener(view -> wordsListListner.onWordClicked(term));
        }
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<Term> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(wordsListAll);
                results.values = filteredList;
            } else {
                for (Term term : wordsListAll) {
                    if (term.getBase().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(term);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            wordsList.clear();
            wordsList.addAll((Collection<? extends Term>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
